package com.agt.bsuirgek.client.view

import com.agt.bsuirgek.client.util.pix
import javafx.event.EventTarget
import javafx.geometry.Insets
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.TextAlignment
import org.apache.poi.xwpf.usermodel.ParagraphAlignment
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import org.apache.poi.xwpf.usermodel.XWPFTable
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
import tornadofx.*
import java.io.FileInputStream

//8.27 x 11.69
class DocumentView : View() {

    val document by lazy { XWPFDocument(FileInputStream("D:/doc0.docx")) }
    val section: CTSectPr by lazy { document.document.body.sectPr }

    init {
        primaryStage.isMaximized = true
    }

    fun EventTarget.page(op: VBox.() -> Unit = {}) = vbox {
        section.pgSz.apply {
            setMaxSize(w.pix, h.pix)
            setMinSize(w.pix, h.pix)
        }
        section.pgMar.apply {
            padding = Insets(top.pix, right.pix, bottom.pix, left.pix)
        }
        style {
            backgroundColor += Color.WHITE
        }
        apply(op)
    }

    override val root = scrollpane(fitToWidth = true) {
        vbox {
            page {
                vboxConstraints {
                    margin = Insets(100.0)
                    marginLeft = 400.0
                }
                var listStart = 0
                document.bodyElements.forEach {
                    when (it) {
                        is XWPFParagraph -> {
                            textflow {
                                style {
                                    borderColor += box(Color.RED)
                                    borderWidth += box(2.px)
                                    borderRadius += box(5.px)
                                }
                                if (it.runs.isEmpty()) text()
                                else {
                                    textAlignment = when (it.alignment) {
                                        ParagraphAlignment.CENTER -> TextAlignment.CENTER
                                        ParagraphAlignment.RIGHT -> TextAlignment.RIGHT
                                        ParagraphAlignment.BOTH -> TextAlignment.JUSTIFY
                                        else -> TextAlignment.LEFT
                                    }
                                    if (it.firstLineIndent != -1) {
                                        label {
                                            minWidth = it.firstLineIndent.pix
                                        }
                                    }
                                    if (it.numID == null) listStart = 0
                                    else {
                                        listStart++
                                        label("$listStart. ") {
                                            font = Font("Times New Roman", 14.0)
                                        }
                                    }

                                    it.runs.forEach {
                                        val text = if(it.isCapitalized) it.text().toUpperCase() else it.text()
                                        text(text) {
                                            println("${it.fontName}  ${it.fontSize}  ${it.isCapitalized}")
                                            font = Font("Times New Roman", 14.0)
                                        }
                                    }
                                }
                            }
                        }
                        is XWPFTable -> {
                            gridpane {
                                it.rows.forEachIndexed { i, row ->
                                    row.tableCells.forEachIndexed { j, cell ->
                                        val t = textfield(cell.text) {

                                        }
                                                .gridpaneConstraints {
                                                    columnRowIndex(j, i)
                                                }
                                        t.hide()
                                        val l = label(cell.text) {
                                            //                                            style {
//                                                borderColor += box(Color.BLACK)
//                                                borderWidth += box(2.px)
//                                            }
                                            gridpaneConstraints {
                                                columnRowIndex(j, i)
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}