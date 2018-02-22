package com.agt.bsuirgek.server.model

import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

abstract class ParseObject(val id: String, val linkId: List<String>) {
    var linkedObject: ParseObject? = null
    override fun toString(): String {
        return "$id/$linkId($linkedObject) - "
    }
}

abstract class ObjectWithParams(id: String, linkId: List<String>) : ParseObject(id, linkId) {

    protected class ParseMap : ReadOnlyProperty<ObjectWithParams, String> {
        override fun getValue(thisRef: ObjectWithParams, property: KProperty<*>) = thisRef.params[property.name] ?: ""
    }

    lateinit var tags: Map<String,String>

    private val _params = mutableMapOf<String, String>()
    val params: Map<String, String>
        get() = _params

    open operator fun set(argument: String, value: String) {
        _params[argument] = value
    }

    operator fun get(argument: String) = _params[argument]

    val type by ParseMap()

    fun isParamsEmpty(): Boolean {
        _params.values.forEach {
            if (it != "") return false
        }
        return true
    }
}

object ObjectFactory {
    fun create(type: String, id: String, linkId: List<String>) = when (type) {
        "Teacher" -> Teacher(id, linkId)
        "Student" -> Student(id, linkId)
        "Calendar" -> CalendarObject(id, linkId)
        else -> Error(type, id, linkId)
    }
}

class Error(errorObject: String, id: String, linkId: List<String>) : ObjectWithParams(id,linkId) {
    val text = "NO SUCH OBJECT: $errorObject"
    override fun toString() = super.toString() + "Error('$text')"
}

class ListObject(val objects : List<ParseObject>, id: String = "", linkId: List<String> = emptyList()): ParseObject(id, linkId) {
    override fun toString() = super.toString() +  objects
            .fold("LIST${objects.size}:\n") { string, nextObject -> "$string$nextObject\n" }
            .trim()
}

class CalendarObject(id: String, linkId: List<String>): ObjectWithParams(id, linkId) {
    val time by ParseMap()
    val date by ParseMap()

    fun getTime() = Calendar.getInstance().also { it.time = SimpleDateFormat(tags["tf"]).parse(time) }
    fun getDate() = Calendar.getInstance().also { it.time = SimpleDateFormat(tags["df"]).parse(date) }
    override fun toString(): String {
        return super.toString() + "Calendar($time // $date)"
    }
}

class CustomObject(val objectType: String, id: String, linkId: List<String>) : ObjectWithParams(id, linkId)