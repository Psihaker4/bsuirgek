import model.Student
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.apache.poi.xwpf.usermodel.*
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign.*
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
import java.io.FileInputStream
import java.io.FileOutputStream
import java.math.BigInteger
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.*
import java.util.Calendar.getInstance

fun main(args: Array<String>) {
    println("Fucked up Server")

    val inPath = "D:/test.xlsx"
    val outPath = "D:/testOUT.docx"

    val inputDateFormat = SimpleDateFormat("dd.MM.yyyy")
    val outputDateFormat = SimpleDateFormat("EEEE dd MMMM yyyy", Locale("ru"))
    val startDate = getInstance().apply {
        time = inputDateFormat.parse("14.6.2017")
        firstDayOfWeek = Calendar.MONDAY
    }


    val doc = XSSFWorkbook(FileInputStream(inPath))
    val sheet = doc.getSheetAt(0)


    val studs = (3..sheet.lastRowNum).map {
        val row = sheet.getRow(it)
        val names = row.getCell(2).stringCellValue.removePrefix(" ").split(' ')
        val average = row.getCell(3).numericCellValue.toFloat()
        val group = row.getCell(5).stringCellValue
        val payment = row.getCell(4).stringCellValue.removeSuffix("%").removePrefix(" ")
        val percent = payment.toIntOrNull() ?: 0
        Student(names[0], names[1], names[2], average, percent, group)
    }


    val days = studs
            .sortedWith(Comparator { s1, s2 ->
                var i = s1.group.compareTo(s2.group)
                if (i != 0) return@Comparator i

                i = s2.average.compareTo(s1.average)
                if (i != 0) return@Comparator i

                s1.paymentPercent.compareTo(s2.paymentPercent)
            })
            .chunked(12)
            .map {
                if (startDate[DAY_OF_WEEK] == Calendar.SUNDAY) startDate.add(DAY_OF_MONTH, 1)
                val date = outputDateFormat.format(startDate.time).run {
                    substringBefore(' ').toUpperCase() to substringAfter(' ')
                }
                startDate.add(DAY_OF_MONTH, 1)
                date to it.sortedByDescending { it.average }
            }



    wrightDoc(outPath) {

        val center = createParagraphStyles {
            alignment = ParagraphAlignment.CENTER
            spacingAfter = 0
        }
        val font16 = createBlockStyles {
            fontSize = 16
        }

        defaultFont("Times New Roman")

        paragraph(center).block("РАСПИСАНИЕ", font16) { isBold = true }

        paragraph(center).block("работы Государственной Экзаменационной Комиссии", font16)
        paragraph(center).block("по специальности 1-53 01 07", font16)
        paragraph(center).block("\"Информационные технологии и управление в технических системах\"", font16)

        for ((date, students) in days) {

            emptyParagraph(font16)
            emptyParagraph(font16)

            paragraph {
                block("${date.first}, ", font16) { isBold = true; isItalic = true }
                block(date.second, font16)
            }

            emptyParagraph(font16)
            emptyParagraph(font16)

            table {
                removeBorders()
                students.forEachIndexed { index, student ->
                    row {
                        height(0.24)
                        cell(0) {
                            width(0.37)
                            paragraph().block((index + 1).toString()) { fontSize = 14 }
                        }
                        cell(1) {
                            verticalAlignment = CENTER
                            width(4.17)
                            paragraph().block(student.fullName) { fontSize = 11 }
                        }
                        cell(2) {
                            verticalAlignment = CENTER
                            width(1.62)
                            paragraph().block(student.group) { fontSize = 11 }
                        }
                    }
                }
            }
        }
    }
}

fun Double.toInch() = (this*1440).toInt()
fun Double.toBigInch(): BigInteger = BigInteger.valueOf(this.toInch().toLong())

fun XWPFDocument.defaultFont(name : String) {
    createStyles().setDefaultFonts(CTFonts.Factory.newInstance().apply { ascii = name })
}

inline fun createDoc(block: XWPFDocument.() -> Unit) = XWPFDocument().apply(block)

inline fun wrightDoc(path: String, block: XWPFDocument.() -> Unit) {
    XWPFDocument().apply(block).write(FileOutputStream(path))
}

inline fun XWPFDocument.paragraph(stdBlock: XWPFParagraph.() -> Unit = {},
                                  block: XWPFParagraph.() -> Unit = {}): XWPFParagraph {
    return createParagraph().apply(stdBlock).apply(block)
}

inline fun XWPFParagraph.block(text:String = "", stdBlock: XWPFRun.() -> Unit = {},
                               block: XWPFRun.() -> Unit = {}): XWPFRun {
    val run = createRun().apply(stdBlock).apply(block)
    run.setText(text)
    return run
}

inline fun XWPFDocument.emptyParagraph(stdBlock: XWPFRun.() -> Unit = {}){ paragraph().block(" ", stdBlock)}

fun createParagraphStyles(block: XWPFParagraph.() -> Unit) = block
fun createBlockStyles(block: XWPFRun.() -> Unit) = block

inline fun XWPFDocument.table(block: XWPFTable.() -> Unit = {}): XWPFTable = createTable().apply { removeRow(0) }.apply(block)

inline fun XWPFTable.row(index: Int = -1, block: XWPFTableRow.() -> Unit = {}) :XWPFTableRow {
    return (if (index > numberOfRows || index < 0) createRow() else getRow(index)).apply(block)
}

inline fun XWPFTableRow.cell(index: Int = -1, clear:Boolean = true,block: XWPFTableCell.() -> Unit = {}) :XWPFTableCell {
    val cell = if (index > ctRow.sizeOfTcArray() - 1 || index < 0) createCell() else getCell(index)
    if (clear) cell.apply { for (i in 0 until paragraphs.size) removeParagraph(0) }
    return cell.apply(block)
}

inline fun XWPFTableCell.paragraph(stdBlock: XWPFParagraph.() -> Unit = {},
                                  block: XWPFParagraph.() -> Unit = {}): XWPFParagraph {
    return addParagraph().apply(stdBlock).apply(block)
}
fun XWPFTable.removeBorders() {
    ctTbl.tblPr.unsetTblBorders()
}

fun XWPFTableRow.height(inches: Double) {
    height = inches.toInch()
}

fun XWPFTableCell.width(inches : Double) {
    ctTc.addNewTcPr().addNewTcW().w = inches.toBigInch()
}