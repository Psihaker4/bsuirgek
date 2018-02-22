package com.agt.bsuirgek.server.parser

import com.agt.bsuirgek.server.dsl.Node
import com.agt.bsuirgek.server.model.ObjectFactory
import com.agt.bsuirgek.server.model.ObjectWithParams
import com.agt.bsuirgek.server.util.correct

open class TextParser(pattern: Node) {

    val tags = pattern.attributes

    operator fun get(atr: String) = tags[atr] ?: ""

    val id = pattern["id"]
    val linkId = if (pattern["lid"].isEmpty()) emptyList() else pattern["lid"].split(",")
    private val type = pattern.name
    private val nodes = pattern.children

    fun parse(text: String): ObjectWithParams {
        var temp = text.correct()
        return nodes.foldIndexed(ObjectFactory.create(type, id, linkId).also { it.tags = tags }) { index, obj, node ->
            if (node.name == "s") temp = temp.substringAfter(node["t"])
            else obj[node.name] = if (index < nodes.size - 1) temp.substringBefore(nodes[index + 1]["t"]) else temp
            obj
        }
    }
}