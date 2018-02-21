package com.agt.bsuirgek.server.model

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

open class ParseObject(val id: Int, val linkId: List<Int>) {
//    constructor(id: Int, linkId: Int) : this(id, listOf(linkId))

    lateinit var linkedObject: ParseObject
    override fun toString(): String {
        return "$id/$linkId - "
    }

}
abstract class ObjectWithParams(id: Int, linkId: List<Int>) : ParseObject(id, linkId) {

    protected class ParseMap : ReadOnlyProperty<ObjectWithParams, String> {
        override fun getValue(thisRef: ObjectWithParams, property: KProperty<*>) = thisRef.params[property.name] ?: ""
    }

    private val _params = mutableMapOf<String, String>()
    val params: Map<String, String>
        get() = _params

    open operator fun set(argument: String, value: String) {
        _params[argument] = value
    }

    operator fun get(argument: String) = _params[argument]

    fun isEmpty() = _params.values.fold(true) { value, param ->
        if (param.isNotEmpty()) return@fold false
        else true
    }
}

object ObjectFactory {
    fun create(type: String, id: Int = -1, linkId: List<Int> = emptyList()) = when (type) {
        "Teacher" -> Teacher(id, linkId)
        "Student" -> Student(id, linkId)
        else -> Error(type, id, linkId)
    }
}

class Error(errorObject: String, id: Int = -1, linkId: List<Int> = emptyList()) : ObjectWithParams(id,linkId) {
    val text = "NO SUCH OBJECT: $errorObject"
    override fun toString() = super.toString() + "Error('$text')"
}

class ListObject(val objects : List<ParseObject>, id: Int = -1, linkId: List<Int> = emptyList()): ParseObject(id, linkId) {
    override fun toString() = super.toString() +  objects
            .fold("LIST${objects.size}:\n") { string, nextObject -> "$string$nextObject\n" }
            .trim()
}

class CustomObject(val objectType: String, id: Int = -1, linkId: List<Int> = emptyList()) : ObjectWithParams(id, linkId)