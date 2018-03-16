package com.agt.bsuirgek.server.parser

import com.agt.bsuirgek.server.speedTester
import com.google.gson.Gson
import com.google.gson.JsonParser
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.apache.poi.xwpf.usermodel.IBodyElement
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import org.apache.poi.xwpf.usermodel.XWPFTable
import org.w3c.dom.Element
import org.xml.sax.InputSource
import java.io.File
import java.io.FileInputStream
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory

typealias PatternWithIndex = Pair<Int, Pattern>
typealias PatternWithDoubleIndex = Pair<Pair<Int, Int>, Pattern>

fun String.asDOCX() = XWPFDocument(FileInputStream(this))
fun String.asXLSX() = XSSFWorkbook(FileInputStream(this))
fun File.asDOCX() = XWPFDocument(inputStream())

private val regex = "\\$\\{(.+?)}".toRegex()

val XWPFDocument.paragraphPatterns
    get() = paragraphs.findPatterns()

val XWPFDocument.tablePatterns
    get() = tables.findPatterns()

fun List<IBodyElement>.findPatterns() = foldIndexed(mutableListOf<PatternWithIndex>()) { index, list, element ->
    val text = when (element) {
        is XWPFParagraph -> element.text
        is XWPFTable -> element.text
        else -> throw Throwable("FAIL")
    }
    list += regex.findAll(text).map { index to it.groupValues[1].toNode()}
    list
}.toList()

fun XSSFWorkbook.patterns(sheet: Int) = getSheetAt(sheet).patterns

val XSSFSheet.patterns
    get() = mutableListOf<PatternWithDoubleIndex>().also { list ->
        forEach {
            val rowIndex = it.rowNum
            it.forEach { cell ->
                list += regex.findAll(cell.toString()).map { (rowIndex to cell.columnIndex) to it.groupValues[1].toNode() }
            }
        }
    }.toList()

fun XWPFDocument.parse(document: XWPFDocument): List<ParseData> {
    val tablesData = tablePatterns.mapNotNull {
        if (document.tables.size > it.first) it.second.parseTable(document.tables[it.first])
        else null
    }.flatMap { it }

    var shift = 0
    val paragraphs = document.paragraphs
    val paragraphsData = paragraphPatterns.mapNotNull {
        if(paragraphs.size > it.first+shift)
            when (it.second.type) {
                "List"      -> it.second.parseList(paragraphs.drop(it.first + shift)).apply { shift += size - 1 }
                "Paragraph" -> it.second.parseParagraph(paragraphs[it.first + shift])
                "Multi"     -> it.second.parseMulti(paragraphs.drop(it.first + shift))
                else        -> null
            }
        else null
    }.flatMap { it }

    return tablesData + paragraphsData
}

fun XSSFWorkbook.parse(workbook: XSSFWorkbook): List<List<ParseData>> {
    var shift = 0
    return (0 until numberOfSheets).map { sheetIndex ->
        patterns(sheetIndex).mapNotNull {
            val pos = it.first.first + shift to it.first.second
            try {
                when (it.second.type) {
                    "Table"     -> it.second.parseSheet(workbook.getSheetAt(sheetIndex), pos).apply { shift += size }
                    "Paragraph" -> it.second.parseParagraph(
                        workbook.getSheetAt(sheetIndex).getRow(pos.first).getCell(
                            pos.second
                        ).toString()
                    )
                    else        -> null
                }
            } catch (e: Exception) {
                println(e)
                null
            }
        }.flatMap { it }
    }
}

inline fun pattern(root: String, init: Pattern.() -> Unit = {}) = Pattern(root).apply(init)

fun String.toNode() = create(this).run {pattern(tagName).apply { copy(this@run)} }

val builder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
private fun create(xml: String): Element = builder.parse(InputSource(StringReader(xml))).documentElement

fun List<ParseData>.toJson(): String {
    return speedTester {

        val old = Gson().toJson(this)

        val new = JsonParser().parse(old).asJsonArray.apply {
            forEach {
                val obj = it.asJsonObject
                val its = mutableListOf<String>()
                obj.keySet().forEach {
                    when {
                        obj[it].isJsonObject -> {
                            val o = obj[it].asJsonObject
                            val remKeys = mutableListOf<String>()
                            o.keySet().forEach {
                                if (o[it].asString == "") {
                                    remKeys += it
                                }
                            }
                            remKeys.forEach { o.remove(it) }

                            if (o.size() == 0) {
                                its += it
                            }
                        }
                        obj[it].isJsonArray -> {
                            if (obj[it].asJsonArray.size() == 0)
                                its += it
                        }
                    }
                }
                its.forEach { obj.remove(it) }
                obj.remove("tags")
            }
        }.toString()
        val diff = ((1 - new.toByteArray().size / old.toByteArray().size.toDouble()) * 10000).toInt().toDouble() / 100
        println(old)
        println(new)
        print("Compression: $diff%, Time(ms): ")
        new
    }
}