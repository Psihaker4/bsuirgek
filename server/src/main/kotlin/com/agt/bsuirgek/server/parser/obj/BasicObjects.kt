package com.agt.bsuirgek.server.parser.obj

interface ParseObject

open class CustomObject : ParseObject {
    private val params: MutableMap<String, String> = hashMapOf()
    open operator fun set(argument: String, value: String) {
        params[argument] = value
    }

    companion object {
        fun create(type: String) = when (type) {
            "Teacher" -> Teacher()
            "Student" -> Student()
            else -> Error(type)
        }
    }
}

class Error(errorObject: String) : CustomObject() {
    val text = "NO SUCH OBJECT: $errorObject"
    override fun toString() = "Error('$text')"
}

class ListObject(val objects : List<ParseObject>): ParseObject {
    override fun toString() = objects
            .fold("LIST${objects.size}:\n")
            { string, nextObject ->
                "$string$nextObject\n"
            }
            .trim()
}

class TableObject(val objects : List<ListObject>): ParseObject {
    override fun toString() = objects
            .fold("TABLE${objects.size}:\n")
            { string, nextObject ->
                "$string$nextObject\n"
            }
            .trim()
}

