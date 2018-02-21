package com.agt.bsuirgek.server.parser

import com.agt.bsuirgek.server.dsl.Node
import com.agt.bsuirgek.server.model.ListObject
import com.agt.bsuirgek.server.model.ParseObject
import com.agt.bsuirgek.server.util.Position
import com.agt.bsuirgek.server.util.simplify
import org.apache.poi.xssf.usermodel.XSSFSheet

class ExcelTableParser(pattern: Node) : ExcelParser {

    private val skippedLines: List<Int>
    private val parsers: List<ParagraphParser>

    init {
        val part = pattern.children.partition { it.name == "Skip" }
        parsers = part.second.map { ParagraphParser(it) }
        skippedLines = part.first.map { pattern.children.indexOf(it) }
    }

    fun parse(sheet: XSSFSheet, startPosition: Position): ListObject {
        val rowRange = (startPosition.row until sheet.lastRowNum)
        println(sheet.lastRowNum)
        return ListObject(rowRange
                .filter { !skippedLines.contains(it - startPosition.row) }
                .fold(mutableListOf()) { rowList, rowIndex ->
                    println(rowIndex)
                    val row = sheet.getRow(rowIndex) ?: return ListObject(rowList)
                    val cellRange = (startPosition.cell until row.lastCellNum)
                    rowList += cellRange.fold(mutableListOf<ParseObject>()) { cellList, cellIndex ->
                        val cell = row.getCell(cellIndex) ?: return@fold cellList
                        val parserIndex = cellIndex - startPosition.cell
                        if (parserIndex < parsers.size)
                            cellList += parsers[parserIndex].parse(cell.toString())
                        cellList
                    }.simplify()
                    rowList
                })
    }
}