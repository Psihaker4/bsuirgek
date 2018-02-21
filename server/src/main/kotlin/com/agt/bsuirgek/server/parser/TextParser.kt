package com.agt.bsuirgek.server.parser

import com.agt.bsuirgek.server.dsl.Node
import com.agt.bsuirgek.server.model.ObjectFactory
import com.agt.bsuirgek.server.model.ObjectWithParams
import com.agt.bsuirgek.server.util.correct

open class TextParser(pattern: Node) {

    val tag = pattern["t"]
    val id = if (pattern["id"].isEmpty()) -1 else pattern["id"].toInt()
    val linkId = if (pattern["lid"].isEmpty()) emptyList() else listOf(pattern["lid"].toInt())
    private val type = pattern.name
    private val nodes = pattern.children

    fun parse(text: String): ObjectWithParams {
        var temp = text.correct()
        return nodes.foldIndexed(ObjectFactory.create(type, id, linkId)) { index, obj, node ->
            if (node.name == "s") temp = temp.substringAfter(node["t"])
            else obj[node.name] = if (index < nodes.size - 1) temp.substringBefore(nodes[index + 1]["t"]) else temp
            obj
        }
    }
}