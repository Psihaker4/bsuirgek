package com.agt.bsuirgek.server.parser

import com.agt.bsuirgek.server.dsl.Node
import com.agt.bsuirgek.server.util.simplify
import org.apache.poi.xwpf.usermodel.XWPFParagraph

class ParagraphParser(pattern: Node) : WordParser, ExcelParser {

    val data = ObjectData(pattern)
    val parsers = pattern.children.map(::ObjectParser)

    fun parse(paragraph: XWPFParagraph) = parse(paragraph.text)

    fun parse(text: String): ParseObject {
        var temp = text
        return parsers.foldIndexed(mutableListOf<MapObject>()) { index, list, parser ->
            if (parser.data.tag("t").isNotEmpty()) temp = temp.substringAfter(parser.data.tag("t"))
            else {
                val txt = if (index < parsers.size - 1)
                    temp.substringBefore(parsers[index + 1].data.tag("t"))
                else temp
                list += parser.parse(txt)
            }
            list
        }.simplify()
    }

}