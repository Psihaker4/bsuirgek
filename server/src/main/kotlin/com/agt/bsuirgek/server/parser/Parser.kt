package com.agt.bsuirgek.server.parser

import com.agt.bsuirgek.server.dsl.Node
import com.agt.bsuirgek.server.util.Position
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xwpf.usermodel.IBodyElement
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import org.apache.poi.xwpf.usermodel.XWPFTable

interface Parser {

    companion object {
        inline fun <reified T:Parser> create(pattern: Node): T {
            println("Parser_${pattern.name}:${pattern.children.map { it.format() }}")
            return when (pattern.name) {
                "List" -> ListParser(pattern)
                "Table" -> {
                    if (T::class == WordParser::class) WordTableParser(pattern)
                    else ExcelTableParser(pattern)
                }
                "Multi", "Cell" -> MultiParagraphParser(pattern)
                "Paragraph" -> ParagraphParser(pattern)
                else -> throw Throwable("NO PARSER")
            } as T
        }
    }
}

interface WordParser : Parser {
    fun run(elements: List<IBodyElement>, shift: Int) = when (this) {
        is ParagraphParser -> {
            parse(elements[shift] as XWPFParagraph)
        }
        is ListParser -> {
            parse(elements.drop(shift) as List<XWPFParagraph>)
        }
        is WordTableParser -> {
            parse(elements[shift] as XWPFTable)
        }
        else -> throw Throwable("PARSE ERROR")
    }
}

interface ExcelParser : Parser {
    fun run(sheet: XSSFSheet, shift: Position) = when (this) {
        is ParagraphParser -> {
            println(shift)
            parse(sheet.getRow(shift.row).getCell(shift.cell).toString())
        }
        is ExcelTableParser -> {
            parse(sheet, shift)
        }
        else -> throw Throwable("PARSE ERROR")
    }
}