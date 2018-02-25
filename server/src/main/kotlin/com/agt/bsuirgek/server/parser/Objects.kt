package com.agt.bsuirgek.server.parser

import com.agt.bsuirgek.server.dsl.Node

class ObjectData(val type: String,
                 val id:String,
                 val linkIds: List<String>,
                 val tags: Map<String, String>) {
    constructor(pattern: Node) : this(
            pattern.name,
            pattern["id"],
            pattern["lid"].split(','),
            pattern.attributes
    )

    fun tag(atr: String) = tags[atr] ?: ""
}

abstract class ParseObject(val data: ObjectData) {
    var links: List<ParseObject>? = null
    override fun toString(): String {
        return "id=${data.id};\tlid=${data.linkIds};\tlinks=$links;\tOBJ = "
    }
}

class MapObject(val params: Map<String,String>, data: ObjectData) : ParseObject(data) {
    operator fun get(argument: String) = params[argument]
    override fun toString(): String {
        return super.toString() + params
    }
}

class ListObject(val objects : List<ParseObject>, data: ObjectData = ObjectData("List","", emptyList(), emptyMap())): ParseObject(data) {
    override fun toString() = super.toString() +
            objects
                    .fold("LIST${objects.size}:\n") { string, nextObject -> "$string\t$nextObject\n" }
                    .trim()
}




//class CalendarObject(id: String, linkIds: List<String>): MapObject(id, linkIds) {
//    val time by ParseMap()
//    val date by ParseMap()
//
//    fun getTime() = Calendar.getInstance().also { it.time = SimpleDateFormat(tags["tf"]).parse(time) }
//    fun getDate() = Calendar.getInstance().also { it.time = SimpleDateFormat(tags["df"]).parse(date) }
//    override fun toString(): String {
//        return super.toString() + "Calendar($time // $date)"
//    }
//}
//
//class CustomObject(val objectType: String, id: String, linkIds: List<String>) : MapObject(id, linkIds)