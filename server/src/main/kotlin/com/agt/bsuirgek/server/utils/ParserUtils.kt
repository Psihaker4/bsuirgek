package com.agt.bsuirgek.server.utils

import com.agt.bsuirgek.server.parser.parser.Parser
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import org.apache.poi.xwpf.usermodel.XWPFTable

private val regex = "\\$\\{(.+?)}".toRegex()

fun String.findPatterns() = regex.findAll(this).map { it.groupValues[1] }.toList()

fun String.createParsers(index: Int) = findPatterns().map { index to Parser.create(it.toXML()) }

//fun String.splitInTwo(del: String, dropCount: Int = 0) = substringBefore(del) to substringAfter(del).dropLast(dropCount)

fun XWPFDocument.findPatterns() = bodyElements
        .map {
            when (it) {
                is XWPFParagraph -> it.text.findPatterns()
                is XWPFTable -> it.text.findPatterns()
                else -> emptyList()
            }
        }.flatMap { it }

fun XWPFDocument.createParsers() = bodyElements
        .mapIndexed { index, element ->
            when (element) {
                is XWPFParagraph -> element.text.createParsers(index)
                is XWPFTable -> element.text.createParsers(index)
                else -> emptyList()
            }
        }.flatMap { it }
