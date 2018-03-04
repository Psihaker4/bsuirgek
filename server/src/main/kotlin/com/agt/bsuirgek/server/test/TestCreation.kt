package com.agt.bsuirgek.server.test

import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.apache.poi.xwpf.usermodel.*
import java.io.FileInputStream
import java.text.SimpleDateFormat
import java.util.*

fun main2(args: Array<String>) {
    println("Fucked up Server")

    val inPath = "D:/parseTestDOCX.xlsx"
    val outPath = "D:/testOUT.docx"

    val inputDateFormat = SimpleDateFormat("dd.MM.yyyy")
    val outputDateFormat = SimpleDateFormat("EEEE dd MMMM yyyy", Locale("ru"))
    val startDate = Calendar.getInstance().apply {
        time = inputDateFormat.parse("14.6.2017")
        firstDayOfWeek = Calendar.MONDAY
    }


    val doc = XSSFWorkbook(FileInputStream(inPath))
    val sheet = doc.getSheetAt(0)


//    val studs = (3..sheet.lastRowNum).map {
//        val row = sheet.getRow(it)
//        row.getCell(0)
//        val names = row.getCell(2).stringCellValue.removePrefix(" ").split(' ')
//        val average = row.getCell(3).numericCellValue.toFloat()
//        val group = row.getCell(5).stringCellValue
//        val payment = row.getCell(4).stringCellValue.removeSuffix("%").removePrefix(" ")
//        val percent = payment.toIntOrNull() ?: 0
//        Student("", emptyList())
//    }
//
//
//    val days = studs
//            .sortedWith(Comparator { s1, s2 ->
//                var i = s1.group.compareTo(s2.group)
//                if (i != 0) return@Comparator i
//
//                i = s2.average.compareTo(s1.average)
//                if (i != 0) return@Comparator i
//
//                s1.paymentPercent.compareTo(s2.paymentPercent)
//            })
//            .chunked(12)
//            .map {
//                if (startDate[DAY_OF_WEEK] == Calendar.SUNDAY) startDate.add(DAY_OF_MONTH, 1)
//                val date = outputDateFormat.format(startDate.time).run {
//                    substringBefore(' ').toUpperCase() to substringAfter(' ')
//                }
//                startDate.add(DAY_OF_MONTH, 1)
//                date to it.sortedByDescending { it.average }
//            }
//
//
//
//    wrightDoc(outPath) {
//
//        val center = createParagraphStyles {
//            alignment = ParagraphAlignment.CENTER
//            spacingAfter = 0
//        }
//        val font16 = createBlockStyles {
//            fontSize = 16
//        }
//
//        defaultFont("Times New Roman")
//
//        paragraph(center).block("РАСПИСАНИЕ", font16) { isBold = true }
//
//        paragraph(center).block("работы Государственной Экзаменационной Комиссии", font16)
//        paragraph(center).block("по специальности 1-53 01 07", font16)
//        paragraph(center).block("\"Информационные технологии и управление в технических системах\"", font16)
//
//        for ((date, students) in days) {
//
//            emptyParagraph(font16)
//            emptyParagraph(font16)
//
//            paragraph {
//                block("${date.first}, ", font16) { isBold = true; isItalic = true }
//                block(date.second, font16)
//            }
//
//            emptyParagraph(font16)
//            emptyParagraph(font16)
//
//            table {
//                removeBorders()
//                students.forEachIndexed { index, student ->
//                    row {
//                        height(0.24)
//                        cell(0) {
//                            width(0.37)
//                            paragraph().block((index + 1).toString()) { fontSize = 14 }
//                        }
//                        cell(1) {
//                            verticalAlignment = CENTER
//                            width(4.17)
//                            paragraph().block(student.fullName) { fontSize = 11 }
//                        }
//                        cell(2) {
//                            verticalAlignment = CENTER
//                            width(1.62)
//                            paragraph().block(student.group) { fontSize = 11 }
//                        }
//                    }
//                }
//            }
//        }
//    }
}

fun main3(args: Array<String>) {
    val path = "D:/parseTestDOCX.docx"

    val doc = XWPFDocument(FileInputStream(path))

    val list = mutableListOf<String>()

    doc.bodyElements.forEach {
        when (it) {
            is XWPFParagraph -> {
                if (it.numLevelText == "%1.")
                    list.add(it.text)
//                println("PARAGRAPH ${list.size}")
            }
            is XWPFTable -> {
//                println("TABLE\n${it.text}")
            }
        }
    }
    //${name3 - data}
    list.forEach {
        println(it.substringBefore('–'))
    }

}

