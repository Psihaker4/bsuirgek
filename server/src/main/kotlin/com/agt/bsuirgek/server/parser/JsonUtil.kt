package com.agt.bsuirgek.server.parser

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser

val gson by lazy { Gson() }
val jsonParser by lazy { JsonParser() }

fun JsonObject.toMap() = gson.fromJson<Map<String, String>>(this, Map::class.java)

fun String.asJsonArray() = jsonParser.parse(this).asJsonArray

fun JsonArray.heads(): List<JsonObject> {
    val linkIds = mutableListOf<String>()
    val heads = mutableListOf<String>()
    forEach {
        val obj = it.asJsonObject
        heads += obj.id
        linkIds += obj.links.toMap().keys
    }
    heads.removeAll(linkIds)
    return filter { heads.contains(it.asJsonObject.id) }.map { it.asJsonObject }
}

fun JsonArray.map() = fold(mutableMapOf<String, JsonObject>()) { init, el ->
    init[el.asJsonObject.id] = el.asJsonObject
    init
}

fun JsonObject.isLinksList() = links.isList()

fun JsonObject.isList(): Boolean {
    var isList = true
    keySet().forEach {
        val field = get(it).asString
        if (field.toIntOrNull() == null) {
            isList = false
            return@forEach
        }
    }
    return isList
}

val JsonObject.id
    get() = get("id").asString
val JsonObject.links
    get() = get("links").asJsonObject
val JsonObject.type
    get() = get("type").asString
val JsonObject.params
    get() = get("params").asJsonObject