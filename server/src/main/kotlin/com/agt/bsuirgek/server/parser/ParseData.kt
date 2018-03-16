package com.agt.bsuirgek.server.parser

class ParseData(
    var id: String,
    val type: String,
    val params: Map<String, String>,
    var links: MutableMap<String, String>,
    val tags: MutableMap<String, String>
) {
    constructor(params: Map<String, String>, data: ParseData)
            : this(data.id, data.type, params, data.links, data.tags)

    constructor(params: Map<String, String>, pattern: Pattern) : this(params, pattern.data)

    override fun toString() = "$type:$id/$links --- $params"

    fun copyWithParams(params: Map<String, String>) = ParseData(params, this)

}