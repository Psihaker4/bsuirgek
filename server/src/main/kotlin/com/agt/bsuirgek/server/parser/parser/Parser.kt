package com.agt.bsuirgek.server.parser.parser

import com.agt.bsuirgek.server.utils.Node

interface Parser {
    companion object {
        fun create(name: String, pattern: Node): Parser {
            val type = pattern.name
            val arguments = pattern.children
            println("Parser_$type:${arguments.map { it.format() }}")
            return when (name) {
                "List" -> ListParser(pattern)
                "Table" -> TableParser(pattern)
                "Line" -> ParagraphParser(pattern)
                "Multi", "Cell" -> MultiParagraphParser(pattern)
                else -> TextParser(pattern)
            }
        }
    }
}