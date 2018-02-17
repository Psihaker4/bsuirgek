package com.agt.bsuirgek.server.parser.parser

import com.agt.bsuirgek.server.parser.obj.CustomObject
import com.agt.bsuirgek.server.utils.Node

open class TextParser(pattern: Node) : Parser {

    val tag = pattern["t"]
    private val objectType = pattern.name
    private val nodes = pattern.children

    fun parse(text: String) = run {
        var temp = text
        nodes.foldIndexed(CustomObject.create(objectType)) { index, obj, node ->
            if (node.name == "s") temp = temp.substringAfter(node["t"])
            else obj[node.name] = if (index < nodes.size) temp.substringBefore(nodes[index + 1]["t"]) else temp
            obj
        }
    }
}