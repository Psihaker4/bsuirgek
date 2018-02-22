package com.agt.bsuirgek.server.parser

import com.agt.bsuirgek.server.model.ListObject
import com.agt.bsuirgek.server.dsl.Node
import com.agt.bsuirgek.server.util.simplify
import org.apache.poi.xwpf.usermodel.XWPFParagraph

class ListParser(pattern: Node) : WordParser {

    val id = pattern["id"]
    val linkId = if (pattern["lid"].isEmpty()) emptyList() else pattern["lid"].split(",")
    val parser = ParagraphParser(pattern)

    fun parse(elements: List<XWPFParagraph>): ListObject {
        var listEnd = false
        val list = elements.filter { if (it.numLevelText == null) listEnd = true; !listEnd }
                .map { parser.parse(it) }

        return ListObject(list, id, linkId)
    }
}