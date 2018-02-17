package com.agt.bsuirgek.server.parser.parser

import com.agt.bsuirgek.server.parser.obj.CustomObject
import com.agt.bsuirgek.server.parser.obj.ListObject
import com.agt.bsuirgek.server.utils.Node
import org.apache.poi.xwpf.usermodel.XWPFParagraph

class ParagraphParser(pattern: Node) : Parser {

    private val parsers = pattern.children.map { TextParser(it) }

    fun parse(paragraph: XWPFParagraph) = ListObject(
            run {
                var temp = paragraph.text
                parsers.foldIndexed(mutableListOf<CustomObject>()) { index, list, parser ->
                    if (parser.tag.isEmpty()) temp = temp.substringAfter(parser.tag)
                    else {
                        val end = if (index < parsers.size) temp.substringBefore(parsers[index + 1].tag) else temp
                        list += parser.parse(end)
                    }
                    list
                }
            }
    )
}