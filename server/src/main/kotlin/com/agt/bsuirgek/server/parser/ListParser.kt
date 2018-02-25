package com.agt.bsuirgek.server.parser

import com.agt.bsuirgek.server.dsl.Node
import org.apache.poi.xwpf.usermodel.XWPFParagraph

class ListParser(pattern: Node) : WordParser {

    val data = ObjectData(pattern)
    val parser = ParagraphParser(pattern)

    fun parse(paragraphs: List<XWPFParagraph>): ListObject {
        var listEnd = false
        val list = paragraphs
                .filter { if (it.numLevelText == null) listEnd = true; !listEnd }
                .map { parser.parse(it) }

        return ListObject(list, data)
    }
}