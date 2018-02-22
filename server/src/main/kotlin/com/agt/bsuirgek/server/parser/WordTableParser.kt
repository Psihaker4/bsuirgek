package com.agt.bsuirgek.server.parser

import com.agt.bsuirgek.server.model.ListObject
import com.agt.bsuirgek.server.dsl.Node
import com.agt.bsuirgek.server.model.ParseObject
import com.agt.bsuirgek.server.util.simplify
import org.apache.poi.xwpf.usermodel.XWPFTable

class WordTableParser(pattern: Node) : WordParser {

    val id = pattern["id"]
    val linkId = if (pattern["lid"].isEmpty()) emptyList() else pattern["lid"].split(",")

    private val skippedLines: List<Int>
    private val parsers: List<MultiParagraphParser>

    init {
        val part = pattern.children.partition { it.name == "Skip" }
        parsers = part.second.map { MultiParagraphParser(it) }
        skippedLines = part.first.map { pattern.children.indexOf(it) }
    }

    fun parse(table: XWPFTable): ListObject {
        val obj = table.rows
                .filterIndexed { index, _ -> !skippedLines.contains(index) }
                .fold(mutableListOf<ParseObject>()) { list, row ->
                    list += row.tableCells.foldIndexed(mutableListOf<ParseObject>()) { index, cells, cell ->
                        cells += parsers[index].parse(cell.paragraphs)
                        cells
                    }.simplify()
                    list
                }.simplify()

        return if (obj is ListObject) obj
        else ListObject(listOf(obj), id, linkId)
    }
}