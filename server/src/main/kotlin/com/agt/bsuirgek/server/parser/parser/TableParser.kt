package com.agt.bsuirgek.server.parser.parser

import com.agt.bsuirgek.server.parser.obj.ListObject
import com.agt.bsuirgek.server.parser.obj.TableObject
import com.agt.bsuirgek.server.utils.Node
import org.apache.poi.xwpf.usermodel.XWPFTable

class TableParser(pattern: Node) : Parser {

    private val skippedLines: List<Int>
    private val parsers: List<MultiParagraphParser>

    init {
        val part = pattern.children.partition { it.name == "Skip" }
        parsers = part.second.map { MultiParagraphParser(it) }
        skippedLines = part.first.map { pattern.children.indexOf(it) }
    }

    fun parse(table: XWPFTable) = TableObject(
            table.rows
                    .filterIndexed { index, _ -> !skippedLines.contains(index) }
                    .fold(mutableListOf()) { list, row ->
                        list += ListObject(
                                row.tableCells.foldIndexed(mutableListOf()) { index, cells, cell ->
                                    cells += parsers[index].parse(cell.paragraphs)
                                    cells
                                })
                        list
                    }
    )
}