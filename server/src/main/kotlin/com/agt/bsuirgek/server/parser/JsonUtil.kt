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

fun JsonArray.groupById() = fold(mutableMapOf<String, JsonObject>()) { init, el ->
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

var JsonObject.id
    get() = get("id").asString
    set(value) = addProperty("id", value)
val JsonObject.links
    get() = get("links").asJsonObject
val JsonObject.type
    get() = get("type").asString
val JsonObject.params
    get() = get("params").asJsonObject
val JsonObject.isLinksPassed
    get() = get("linksPassed")?.asBoolean ?: false

fun JsonObject.pass() { addProperty("linksPassed", true) }

fun JsonObject.pass(objects: Map<String,JsonObject>) {
//    println(this)
    pass()
    if (isLinksList()) {
        passLinks(objects)
    } else {
        links.keySet().forEach { key ->
            val linkObject = objects[key] ?: return@forEach
            println(linkObject.id)
            if(!linkObject.isLinksPassed)
                linkObject.passLinks(objects)
        }
    }
}

fun JsonObject.passLinks(objects: Map<String, JsonObject>) {
    links.keySet().forEach {
        val linkObject = objects[it] ?: return@forEach
        val pre = links[it].asString
        linkObject.unlink(pre)
        println("$linkObject")
        when (linkObject.type) {
            "List" -> {
//                println("LIST: $linkObject")
                linkObject.pass(objects)
            }
            "Student" -> {
//                println("STUDENT: $linkObject")
                linkObject.pass(objects)
            }
            "Teacher" -> {
//                println("TEACHER: $linkObject")
                linkObject.pass(objects)
            }
        }
    }
}

fun JsonObject.unlink(pre: String) {
    id = id.removePrefix(pre)
    links.keySet().forEach {
        val v = links[it]
        links.remove(it)
        links.add(it.removePrefix(pre),v)//.id = links[it].asJsonObject.id.removePrefix(pre)
    }
}