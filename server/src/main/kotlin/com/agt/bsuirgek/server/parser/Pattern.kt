package com.agt.bsuirgek.server.parser

import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import org.apache.poi.xwpf.usermodel.XWPFTable
import java.util.*

import org.w3c.dom.Node.ELEMENT_NODE
import org.w3c.dom.Node as W3CNode

class Pattern(val type: String) {

    private val isSpace = type == "s"

    var id: String
        get() {
            val id = get("id")
            return if (id == "") type
            else id
        }
        set(value) {
            set("id", value)
        }
    val links
        get() = get("links").split(',').filter { it != "" }.toMutableList()
    val skips
        get() = get("skip").split(",").filter { it != "" }.map { it.toInt() }
    private val text
        get() = get("t")
    val tags = LinkedHashMap<String, String>()
    val patterns = mutableListOf<Pattern>()

    fun tags(vararg tags: Pair<String, String>) {
        tags.forEach { set(it.first, it.second) }
    }

    operator fun get(tag: String) = tags[tag] ?: ""

    operator fun set(tag: String, value: String?) {
        if (value == null) tags.remove(tag) else tags[tag] = value
    }

    inline operator fun String.invoke(vararg tags: Pair<String, String>, init: Pattern.() -> Unit = {}) = Pattern(this).also { patterns.add(it) }.apply { tags(*tags); init() }


    override fun toString(): String = format(true)

    fun format(prettyFormat: Boolean = false) = StringBuilder().apply { render(this, prettyFormat) }.toString().trim()

    private fun render(builder: StringBuilder, prettyFormat: Boolean = true, tab: String = "") {
        val lineEnd = if (prettyFormat) "\n" else ""

        val tags = if (tags.isEmpty()) "" else " " + tags.map { "${it.key}=\"${it.value}\"" }.joinToString(" ")

        builder.apply {
            append("$tab<$type$tags")
            if (patterns.isEmpty()) append("/>$lineEnd")
            else {
                append(">$lineEnd")
                patterns.forEach { it.render(this, prettyFormat, if (!prettyFormat) "" else "$tab\t") }
                append("$tab</$type>$lineEnd")
            }
        }
    }


    fun copy(source: W3CNode) {
        source.attributes?.apply {
            (0 until length).forEach {
                item(it).run { set(nodeName, nodeValue) }
            }
        }
        source.childNodes?.apply {
            (0 until length).forEach {
                item(it).takeIf { it.nodeType == ELEMENT_NODE }?.run { nodeName().copy(this) }
            }
        }
    }


    private fun String.correct(): String {
        var startSpace = true
        val text = StringBuilder().also { s ->
            forEachIndexed { index, c ->
                if (startSpace && c != ' ') startSpace = false
                if (!startSpace) s.append(c)
                if (
                        c == '.' &&
                        index + 1 < length &&
                        !get(index + 1).isWhitespace() &&
                        !get(index + 1).isDigit()
                ) s.append(" ")
            }
        }.toString()

        return text
    }


    fun parseText(text: String): ParseData {
        var temp = text.correct()
        val params = patterns.foldIndexed(mutableMapOf<String, String>()) { index, params, pattern ->
            val (txt, tmp) = nextBlock(pattern, temp, index)
            if (txt != null) params[pattern.type] = txt
            temp = tmp
            params
        }
        return ParseData(params, this)
    }

    fun parseParagraph(paragraph: XWPFParagraph) = parseParagraph(paragraph.text)

    fun parseParagraph(paragraph: String): List<ParseData> {
        var temp = paragraph
        return patterns.foldIndexed(mutableListOf<ParseData>()) { index, list, pattern ->
            val (txt, tmp) = nextBlock(pattern, temp, index)
            if (txt != null) list += pattern.parseText(txt)
            temp = tmp
            list
        }.collapse()
    }

    fun parseList(paragraphs: List<XWPFParagraph>): List<ParseData> {
        var listEnd = false
        return paragraphs.filter { if (it.numLevelText == null) listEnd = true; !listEnd }
                .mapIndexed { index, child -> parseParagraph(child).linkList(index) }
                .flatMap { it }
                .collapse()
    }

    fun parseMulti(paragraphs: List<XWPFParagraph>): List<ParseData> {
        val elements = paragraphs.toMutableList()
        return patterns.map {
            when (it.type) {
                "Paragraph" -> it.parseParagraph(elements[0]).apply { elements.removeAt(0) }
                "List" -> it.parseList(elements).apply { (0 until size).forEach { elements.removeAt(0) } }
                else -> throw Throwable("Multi Parse Error")
            }
        }
                .flatMap { it }
                .collapse()
    }

    fun parseTable(table: XWPFTable) = table.rows.foldIndexed(mutableListOf<ParseData>()) { i, list, row ->
        if (skips.contains(i)) return@foldIndexed list
        list += row.tableCells.foldIndexed(mutableListOf<ParseData>()) { index, cells, cell ->
            cells += patterns[index].parseMulti(cell.paragraphs).linkTable(i)
            cells
        }
        list
    }.collapse()

    fun parseSheet(sheet: XSSFSheet, shift: Pair<Int, Int>) = (shift.first..sheet.lastRowNum).fold(mutableListOf<ParseData>()) { rowList, rowIndex ->
        val row = sheet.getRow(rowIndex) ?: return rowList.collapse()
        val realRowIndex = rowIndex - shift.first
        if (skips.contains(realRowIndex)) return@fold rowList

        val ll = (shift.second until row.lastCellNum).fold(mutableListOf<ParseData>()) cells@{ cellList, cellIndex ->
            val cell = row.getCell(cellIndex) ?: return@cells cellList
            val i = cellIndex - shift.second
            if (i >= patterns.size) return@cells cellList

            cellList += patterns[i].parseParagraph(cell.toString()).linkTable(realRowIndex)
            cellList
        }

        rowList += ll

        rowList
    }.collapse()

    private fun nextBlock(pattern: Pattern, temp: String, index: Int) = if (pattern.isSpace) null to temp.substringAfter(pattern.text) else (if (index < patterns.size - 1) temp.substringBefore(patterns[index + 1].text) else temp) to temp


    private fun List<ParseData>.linkList(index: Int) = apply {
        forEach {
            it.id = "$id$index${it.id}"
            it.links.replaceAll { "$id$index$it" }
        }
    }

    private fun List<ParseData>.linkTable(index: Int) = apply {
        forEach {
            it.id = "$index${it.id}"
            it.links.replaceAll { "$index$it" }
        }
    }


    private inline fun List<ParseData>.connect(map: ParseData.() -> Map<String, String>) = fold(mutableMapOf<String, String>()) { params, data ->
        data.map().forEach { key, value ->
            if (params[key] == null) params[key] = value
            else params[key] = "${params[key]}/$value"
        }
        params
    }

    private fun List<ParseData>.collapse(): List<ParseData> = groupBy { it.id }.map {
        it.value.run {
            if (size == 1) get(0)
            ParseData(connect { params }, get(0).type, it.key, flatMap { it.links }.toMutableList(), connect { tags })
        }
    }

}


//}
//    Teacher, Student, Custom
//    Cell, Skip, Space,
//enum class ParseElement {
//

//enum class RootParseElement { List, Table, Paragraph }

//fun pattern(root: RootParseElement, init: Pattern.() -> Unit): Pattern {
//    return Pattern(root.name).apply(init)
//}
//
//fun Pattern.teacher(init: Pattern.() -> Unit) = Teacher.name(init = init)
//
//fun Pattern.student(init: Pattern.() -> Unit) = Student.name(init = init)
//
//fun Pattern.custom(init: Pattern.() -> Unit) = Custom.name(init = init)
//
//fun Pattern.cell(init: Pattern.() -> Unit) = Cell.name(init = init)
//
//fun Pattern.skip() = Skip.name()
//
//fun Pattern.s(t: String = "") = Space.name("text" to t)
