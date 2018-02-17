package com.agt.bsuirgek.server.utils

import org.w3c.dom.Element
import org.w3c.dom.NamedNodeMap
import org.xml.sax.InputSource
import java.io.StringReader
import java.util.*
import javax.xml.parsers.DocumentBuilderFactory

import org.w3c.dom.Node.ELEMENT_NODE
import org.w3c.dom.Node as W3CNode

private object XMLCreator {
    private val builder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
    fun create(xml: String): Element = builder.parse(InputSource(StringReader(xml))).documentElement
}

fun String.toXML() = XMLCreator.create(this).run { xml(tagName).apply { copy(this@run)} }

inline fun xml(root: String, init: Node.() -> Unit = {}) = Node(root).apply(init)

class Node(val name: String) {

    val attributes = LinkedHashMap<String, String>()

    operator fun get(attributeName: String) = attributes[attributeName] ?: ""

    operator fun set(attributeName: String, value: String?) {
        if (value == null) attributes.remove(attributeName)
        else attributes[attributeName] = value
    }

    fun attribute(attribute: Pair<String, String>) {
        attributes[attribute.first] = attribute.second
    }

    fun attributes(vararg attrs: Pair<String, String>) {
        attrs.forEach { attribute(it) }
    }

    fun attributes(attrs: List<Pair<String, String>>) {
        attrs.forEach { attribute(it) }
    }


    private val _children = mutableListOf<Node>()

    val children: List<Node>
        get() = _children

    val childCount : Int
        get() = _children.size

    fun add(node: Node) {
        _children.add(node)
    }

    fun addAll(nodes: List<Node>) {
        _children.addAll(nodes)
    }


    inline operator fun String.invoke(vararg attributes: Pair<String, String>, init: Node.() -> Unit = {}) = Node(this).also { add(it) }.apply { attributes(*attributes); init() }

    override fun toString(): String = format(true)

    fun format(prettyFormat: Boolean = false) = StringBuilder().apply { render(this, prettyFormat) }.toString().trim()

    private fun render(builder: StringBuilder, prettyFormat: Boolean = true, tab: String = "") {
        val lineEnd = if (prettyFormat) "\n" else ""

        val attrs = if (attributes.isEmpty()) "" else " " + attributes.map { "${it.key}=\"${it.value}\"" }.joinToString(" ")

        builder.apply {
            append("$tab<$name$attrs")
            if (_children.isEmpty()) append("/>$lineEnd")
            else {
                append(">$lineEnd")
                _children.forEach { it.render(this, prettyFormat, if (!prettyFormat) "" else "$tab\t") }
                append("$tab</$name>$lineEnd")
            }
        }
    }

    fun copy(source: W3CNode) {
        source.attributes?.apply { attributes((0 until length).map { item(it).run { nodeName to nodeValue } }) }
        source.childNodes?.apply { (0 until length).forEach { item(it).apply { if (nodeType == ELEMENT_NODE) nodeName().copy(this) } } }
    }

}