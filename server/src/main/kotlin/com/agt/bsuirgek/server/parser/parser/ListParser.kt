package com.agt.bsuirgek.server.parser.parser

import com.agt.bsuirgek.server.parser.obj.ListObject
import com.agt.bsuirgek.server.utils.Node
import org.apache.poi.xwpf.usermodel.IBodyElement
import org.apache.poi.xwpf.usermodel.XWPFParagraph

class ListParser(pattern: Node) : Parser {

    val parser = Parser.create("Line", pattern) as ParagraphParser

    fun parse(elements: List<XWPFParagraph>): ListObject {
        var listEnd = false
        return ListObject(elements
                .filter {
                    if (it.numLevelText != null)
                        !listEnd
                    else {
                        listEnd = true
                        false
                    }
                }
                .map { parser.parse(it) })
    }
}
