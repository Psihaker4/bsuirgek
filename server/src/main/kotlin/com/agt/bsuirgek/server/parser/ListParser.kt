package com.agt.bsuirgek.server.parser

import com.agt.bsuirgek.server.model.ListObject
import com.agt.bsuirgek.server.dsl.Node
import org.apache.poi.xwpf.usermodel.XWPFParagraph

class ListParser(pattern: Node) : WordParser {

    val id = if (pattern["id"].isEmpty()) -1 else pattern["id"].toInt()
    val linkId = if (pattern["lid"].isEmpty()) emptyList() else listOf(pattern["lid"].toInt())
    val parser = ParagraphParser(pattern)

    fun parse(elements: List<XWPFParagraph>): ListObject {
        var listEnd = false
        return ListObject(
                elements.filter { if (it.numLevelText == null) listEnd = true; !listEnd }
                        .map { parser.parse(it) },
                id,
                linkId
        )
    }
}