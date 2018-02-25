package com.agt.bsuirgek.server.parser

import com.agt.bsuirgek.server.dsl.Node
import com.agt.bsuirgek.server.util.Position
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xwpf.usermodel.IBodyElement
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import org.apache.poi.xwpf.usermodel.XWPFTable

interface Parser

fun Node.toWordParser() = when (name) {
    "List" -> ListParser(this)
    "Multi" -> MultiParagraphParser(this)
    "Table" -> WordTableParser(this)
    "Paragraph" -> ParagraphParser(this)
    else -> throw Throwable("No such Word Parser")
}

fun Node.toExcelParser() = when (name) {
    "Table" -> ExcelTableParser(this)
    "Paragraph" -> ParagraphParser(this)
    else -> throw Throwable("No such Excel Parser")
}

interface WordParser : Parser {
    fun run(elements: List<IBodyElement>, shift: Position) = when (this) {
        is ParagraphParser -> parse(elements[shift.row] as XWPFParagraph)
        is ListParser -> parse(elements.drop(shift.row) as List<XWPFParagraph>).apply { shift.add(Position(objects.size - 1, 0)) }
        is WordTableParser -> parse(elements[shift.row] as XWPFTable)
        else -> throw Throwable("PARSE ERROR")
    }
}

interface ExcelParser : Parser {
    fun run(sheet: XSSFSheet, shift: Position) = when (this) {
        is ParagraphParser -> parse(sheet.getRow(shift.row).getCell(shift.cell).toString())
        is ExcelTableParser -> parse(sheet, shift)
        else -> throw Throwable("PARSE ERROR")
    }
}