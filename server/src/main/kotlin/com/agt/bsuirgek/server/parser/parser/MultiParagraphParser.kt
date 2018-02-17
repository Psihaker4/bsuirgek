package com.agt.bsuirgek.server.parser.parser

import com.agt.bsuirgek.server.parser.obj.Error
import com.agt.bsuirgek.server.parser.obj.ListObject
import com.agt.bsuirgek.server.parser.obj.ParseObject
import com.agt.bsuirgek.server.utils.Node
import org.apache.poi.xwpf.usermodel.IBodyElement
import org.apache.poi.xwpf.usermodel.XWPFParagraph

class MultiParagraphParser(pattern: Node) : Parser {

    val parsers: List<Parser> = run {
        var i = 0
        pattern.children.groupBy { if (it.name == "n") i++; i }.map {
            MultiParagraphParser(Node("Multi").apply { addAll(it.value) })
        }
    }

    fun parse(elements: List<XWPFParagraph>): ListObject {
        val elems = mutableListOf<XWPFParagraph>()
        elems.addAll(elements)
        return ListObject(parsers.map {
            when (it) {
                is ParagraphParser -> it.parse(elems[0]).apply { elems.removeAt(0) }
                is ListParser -> it.parse(elems).apply {
                    (0 until objects.size).forEach { elems.removeAt(it) }
                }
                else -> ListObject(listOf(Error("ASD")))
            }
        })
    }
}