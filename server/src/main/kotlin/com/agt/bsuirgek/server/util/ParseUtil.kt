package com.agt.bsuirgek.server.util

import com.agt.bsuirgek.server.dsl.toXML
import com.agt.bsuirgek.server.model.*
import com.agt.bsuirgek.server.parser.ExcelParser
import com.agt.bsuirgek.server.parser.Parser
import com.agt.bsuirgek.server.parser.WordParser
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import org.apache.poi.xwpf.usermodel.XWPFTable

fun String.correct(): String {
    var startSpace = true
    val text = StringBuilder().also { s ->
        forEachIndexed { index, c ->
            if (startSpace && c != ' ') startSpace = false
            if (!startSpace) s.append(c)
            if (
                    c == '.' &&
                    index + 1 < length &&
                    !get(index + 1).isWhitespace() &&
                    !get(index + 1).isDigit()
            ) s.append(" ")
        }
    }.toString()

    return text
}

private val regex = "\\$\\{(.+?)}".toRegex()

fun String.findPatterns() = regex.findAll(this).map { it.groupValues[1] }.toList()

fun String.createIndexedParsers(index: Int) = findPatterns().map { index to Parser.create<WordParser>(it.toXML()) }

fun String.createPositionedParsers(pos: Position) = findPatterns().map { pos to Parser.create<ExcelParser>(it.toXML()) }

fun XWPFDocument.findPatterns() = bodyElements
        .map {
            when (it) {
                is XWPFParagraph -> it.text.findPatterns()
                is XWPFTable -> it.text.findPatterns()
                else -> emptyList()
            }
        }.flatMap { it }


fun XWPFDocument.indexedParsers() = bodyElements
        .mapIndexed { index, element ->
            when (element) {
                is XWPFParagraph -> element.text.createIndexedParsers(index)//.apply { println(element.text) }
                is XWPFTable -> element.text.createIndexedParsers(index)//.apply { println(element.text) }
                else -> emptyList()
            }
        }.flatMap { it }

fun XSSFWorkbook.positionedParsers(): List<List<Pair<Position, ExcelParser>>> {
    val sheets = mutableListOf<List<Pair<Position, ExcelParser>>>()
    sheetIterator().forEach { sheet ->
        val list = mutableListOf<List<Pair<Position, ExcelParser>>>()
        sheet.rowIterator().forEach { row ->
            row.cellIterator().forEach {
                list.add(it.toString().createPositionedParsers(Position(row.rowNum, it.columnIndex)))
            }
        }
        sheets.add(list.flatMap { it })
    }
    return sheets
}

class Position(var row: Int, var cell: Int) {
    override fun toString() = "($row, $cell)"
    operator fun plus(pos: Position) = Position(row + pos.row, cell + pos.cell)
}

fun List<ParseObject>.simplify(): ParseObject {
    if (size == 1) return get(0)

    val result = groupBy { it.id }
            .flatMap {
                val list = it.value
                val type = list[0]::class.simpleName ?: "Error Class"

                val links = list.flatMap { it.linkId }.toSet().filter { it.isNotEmpty() }

                val tempObj = ObjectFactory.create(type, it.key, links)
                if (tempObj is Error) return@flatMap list

                val objs = list as List<ObjectWithParams>

                listOf(objs.fold(tempObj) { obj, addObj ->
                    addObj.params.forEach { if (obj[it.key] == null) obj[it.key] = it.value else return@flatMap list }
                    obj
                })
            }
            .filter {
                when (it) {
                    is ListObject -> it.objects.isNotEmpty()
                    is ObjectWithParams -> !it.isParamsEmpty()
                    else -> true
                }
            }
            .run {
                fold(toMutableList()) { newList, obj ->
                    val list = obj.linkId.mapNotNull { id -> firstOrNull { it.id == id } }

                    if (list.size == 1) {
                        obj.linkedObject = list[0]
                        newList.remove(list[0])
                    } else if (list.size > 1) {
                        obj.linkedObject = ListObject(list)
                        newList.removeAll(list)
                    }
                    newList
                }
            }

    return when (result.size) {
        0 -> ListObject(this)
        1 -> result[0]
        else -> ListObject(result)
    }
}