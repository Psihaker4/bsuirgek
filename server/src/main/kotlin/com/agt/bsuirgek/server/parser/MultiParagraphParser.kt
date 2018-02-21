package com.agt.bsuirgek.server.parser

import com.agt.bsuirgek.server.model.Error
import com.agt.bsuirgek.server.model.ListObject
import com.agt.bsuirgek.server.dsl.Node
import com.agt.bsuirgek.server.model.CustomObject
import com.agt.bsuirgek.server.model.ParseObject
import com.agt.bsuirgek.server.util.simplify
import org.apache.poi.xwpf.usermodel.XWPFParagraph

class MultiParagraphParser(pattern: Node) : WordParser {

    private val parsers: List<Parser> = pattern.children.map { Parser.create<WordParser>(it) }

    fun parse(elements: List<XWPFParagraph>): ParseObject {
        val elems = mutableListOf<XWPFParagraph>()
        elems.addAll(elements)
        return parsers.map {
            when (it) {
                is ParagraphParser -> it.parse(elems[0]).apply { elems.removeAt(0) }
                is ListParser -> it.parse(elems).apply { (0 until objects.size).forEach { elems.removeAt(0) } }
                else -> Error("ASD")
            }
        }.simplify()
    }
}