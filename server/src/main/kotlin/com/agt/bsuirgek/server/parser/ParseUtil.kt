package com.agt.bsuirgek.server.parser

import com.google.gson.Gson
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
    list += regex.findAll(text).map { index to it.groupValues[1].toNode() }
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
    val tablesData = tablePatterns.map {
        it.second.parseTable(document.tables[it.first])
    }.flatMap { it }

    var shift = 0
    val paragraphs = document.paragraphs
    val paragraphsData = paragraphPatterns.map {
        when (it.second.type) {
            "List" -> it.second.parseList(paragraphs).drop(it.first + shift).apply { shift += size - 1 }
            "Paragraph" -> it.second.parseParagraph(paragraphs[it.first + shift])
            "Multi" -> it.second.parseMulti(paragraphs).drop(it.first + shift)
            else -> throw Throwable("Faild DOCX")
        }
    }.flatMap { it }

    return tablesData + paragraphsData
}

fun XSSFWorkbook.parse(workbook: XSSFWorkbook): List<List<ParseData>> {
    var shift = 0
    return (0 until numberOfSheets).map { sheetIndex ->
        patterns(sheetIndex).map {
            val pos = it.first.first + shift to it.first.second
            when (it.second.type) {
                "Table" -> it.second.parseSheet(workbook.getSheetAt(sheetIndex), pos).apply { shift += size }
                "Paragraph" -> it.second.parseParagraph(workbook.getSheetAt(sheetIndex).getRow(pos.first).getCell(pos.second).toString())
                else -> throw Throwable("Faild XLSX")
            }
        }.flatMap { it }
    }
}

inline fun pattern(root: String, init: Pattern.() -> Unit = {}) = Pattern(root).apply(init)

fun String.toNode() = create(this).run { pattern(tagName).apply { copy(this@run)} }

private val builder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
private fun create(xml: String): Element = builder.parse(InputSource(StringReader(xml))).documentElement

fun List<ParseData>.toJson(): String {

    return Gson().toJson(this)

}
