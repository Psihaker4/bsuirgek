package com.agt.bsuirgek.server.parser

import com.agt.bsuirgek.server.dsl.Node
import com.agt.bsuirgek.server.util.correct

class ObjectParser(pattern: Node): Parser {

    val nodes = pattern.children
    val data = ObjectData(pattern)

    fun parse(text: String): MapObject {
        var temp = text.correct()
        val params = nodes.foldIndexed(mutableMapOf<String, String>()) { index, map, node ->
            if (node.name == "s") temp = temp.substringAfter(node["t"])
            else map[node.name] = if (index < nodes.size - 1) temp.substringBefore(nodes[index + 1]["t"]) else temp
            map
        }

        return MapObject(params, data)
    }
}