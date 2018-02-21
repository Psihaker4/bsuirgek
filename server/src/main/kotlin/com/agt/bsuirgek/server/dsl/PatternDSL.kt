package com.agt.bsuirgek.server.dsl

import com.agt.bsuirgek.server.dsl.ParseElement.*

enum class RootParseElement { List, Table, Paragraph }

enum class ParseElement {
    Cell, Skip, Space,
    Teacher, Student, Custom
}

fun pattern(root: RootParseElement, init: Node.() -> Unit): Node {
    return Node(root.name).apply(init)
}

fun Node.teacher(init: Node.() -> Unit) = Teacher.name(init = init)

fun Node.student(init: Node.() -> Unit) = Student.name(init = init)

fun Node.custom(init: Node.() -> Unit) = Custom.name(init = init)

fun Node.cell(init: Node.() -> Unit) = Cell.name(init = init)

fun Node.skip() = Skip.name()

fun Node.s(t: String = "") = Space.name("t" to t)
