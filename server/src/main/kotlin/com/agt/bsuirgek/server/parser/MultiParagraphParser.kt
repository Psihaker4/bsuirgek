package com.agt.bsuirgek.server.parser

import com.agt.bsuirgek.server.dsl.Node
import com.agt.bsuirgek.server.util.simplify
import org.apache.poi.xwpf.usermodel.XWPFParagraph

class MultiParagraphParser(pattern: Node) : WordParser {

    val data = ObjectData(pattern)
    val parsers: List<Parser> = pattern.children.map(Node::toWordParser)

    fun parse(paragraphs: List<XWPFParagraph>): ParseObject {
        val elements = paragraphs.toMutableList()
        return parsers.map {
            when (it) {
                is ParagraphParser -> it.parse(elements[0]).apply { elements.removeAt(0) }
                is ListParser -> it.parse(elements).apply { (0 until objects.size).forEach { elements.removeAt(0) } }
                else -> throw Throwable("Multi Parse Error")
            }
        }.simplify()
    }
}