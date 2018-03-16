package com.agt.bsuirgek.client

import com.agt.bsuirgek.client.view.DocumentView
import com.agt.bsuirgek.client.view.TemplateCardView
import tornadofx.App

class TemplaterApp : App(TemplateCardView::class)

class DocumentApp: App(DocumentView::class)

fun main(args: Array<String>) {
//    Application.launch(DocumentApp::class.java, *args)
    test()
}