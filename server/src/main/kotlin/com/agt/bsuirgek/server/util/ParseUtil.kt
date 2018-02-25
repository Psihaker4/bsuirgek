package com.agt.bsuirgek.server.util

import com.agt.bsuirgek.server.dsl.toXML
import com.agt.bsuirgek.server.parser.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import org.apache.poi.xwpf.usermodel.XWPFTable
import java.io.FileInputStream

fun String.asDOCX() = XWPFDocument(FileInputStream(this))

fun String.asXLSX() = XSSFWorkbook(FileInputStream(this))

fun String.openAndParseDOCX(temp: String) = asDOCX().parseAs(temp.asDOCX())
fun String.openAndParseXLSX(temp: String) = asXLSX().parseAs(temp.asXLSX())

fun XSSFWorkbook.parseAs(template: XSSFWorkbook) = template.parsers.map { sheet ->
    var shift = Position(0, 0)
    sheet.map {
        it.second.run(getSheetAt(0), shift + it.first).apply { if (this is ListObject) shift += Position(objects.size, 0) }
    }
}

fun XWPFDocument.parseAs(template: XWPFDocument): List<ParseObject> {
    val shift = Position(0,0)
    return template.parsers.map {
        it.second.run(bodyElements, shift + Position(1, 0))
    }
}

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

fun String.createWordParsers(index: Int) = findPatterns().map { index to it.toXML().toWordParser() }

fun String.createExcelParsers(pos: Position) = findPatterns().map { pos to it.toXML().toExcelParser() }

val XWPFDocument.parsers: List<Pair<Int, WordParser>>
get() = bodyElements
        .foldIndexed(mutableListOf()) { index, list, element ->
            val text = when (element) {
                is XWPFParagraph -> element.text
                is XWPFTable -> element.text
                else -> throw Throwable("Failed Word Parser")
            }
            list += text.createWordParsers(index)
            list
        }

val XSSFWorkbook.parsers: List<List<Pair<Position, ExcelParser>>>
get() {
    val sheets = mutableListOf<List<Pair<Position, ExcelParser>>>()
    sheetIterator().forEach { sheet ->
        val list = mutableListOf<Pair<Position, ExcelParser>>()
        sheet.rowIterator().forEach {
            val rowIndex = it.rowNum
            it.cellIterator().forEach {
                list += it.toString().createExcelParsers(Position(rowIndex, it.columnIndex))
            }
        }
        sheets += list
    }
    return sheets
}

class Position(var row: Int, var cell: Int) {
    override fun toString() = "($row, $cell)"
    fun add(pos: Position) {
        row+=pos.row
        cell+=pos.cell
    }
    operator fun plus(pos: Position) = Position(row + pos.row, cell + pos.cell)
}

fun List<ParseObject>.simplify(): ParseObject {
    if (size == 1) return get(0)

    val result = groupBy { it.data.id }
            .map {
                val list = it.value
                val type = list[0].data.type

                val id = it.key
                val linkId = list.flatMap { it.data.linkIds }.toSet().filter { it.isNotEmpty() }
                val tags = list.fold(mutableMapOf<String, String>()) { map, obj ->
                    obj.data.tags.forEach { key, value ->
                        if (map[key] == null) map[key] = value
                        else map[key] = "${map[key]}/$value"
                    }
                    map
                }

                val data = ObjectData(type, id, linkId, tags)

                if (list[0] is MapObject) {
                    val params = (list as List<MapObject>).fold(mutableMapOf<String, String>()) { map, obj ->
                        obj.params.forEach { key, value ->
                            if (map[key] == null) map[key] = value
                            else map[key] = "${map[key]}/$value"
                        }
                        map
                    }
                    MapObject(params, data)
                } else {
                    val objects = (list as List<ListObject>).flatMap { it.objects }
                    ListObject(objects, data)
                }
            }
            .filter {
                when (it) {
                    is ListObject -> it.objects.isNotEmpty()
                    is MapObject -> it.params.isNotEmpty()
                    else -> throw Throwable("Never happens")
                }
            }
            .run {
                fold(toMutableList()) { newList, obj ->
                    val list = obj.data.linkIds.mapNotNull { id -> firstOrNull { it.data.id == id } }
                    if (list.isNotEmpty()) {
                        obj.links = list
                        newList -= list
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

