package com.agt.bsuirgek.server.parser

import com.agt.bsuirgek.server.dsl.Node
import com.agt.bsuirgek.server.model.ObjectWithParams
import com.agt.bsuirgek.server.model.ParseObject
import com.agt.bsuirgek.server.util.simplify
import org.apache.poi.xwpf.usermodel.XWPFParagraph

class ParagraphParser(pattern: Node) : WordParser, ExcelParser {

    private val parsers = pattern.children.map { TextParser(it) }

    fun parse(paragraph: XWPFParagraph) = parse(paragraph.text)

    fun parse(text: String) : ParseObject {
        var temp = text
        return parsers.foldIndexed(mutableListOf<ObjectWithParams>()) { index, list, parser ->
            if (parser.tag.isNotEmpty()) temp = temp.substringAfter(parser.tag)
            else {
                val txt = if (index < parsers.size - 1) temp.substringBefore(parsers[index + 1].tag) else temp
                list += parser.parse(txt)
            }
            list
        }.simplify()
    }

}