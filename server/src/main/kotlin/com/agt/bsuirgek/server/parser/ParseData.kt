package com.agt.bsuirgek.server.parser

class ParseData(
    val params: Map<String, String>,
    val type: String,
    var id: String,
    val links: MutableList<String>,
    val tags: MutableMap<String, String>
) {
    constructor(params: Map<String, String>, pattern: Pattern)
            : this(params, pattern.type, pattern.id, pattern.links, pattern.tags)

    override fun toString() = "$type:$id/$links --- $params"
}
