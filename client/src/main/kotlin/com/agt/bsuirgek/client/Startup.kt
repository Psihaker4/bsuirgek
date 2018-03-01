package com.agt.bsuirgek.client

import com.agt.bsuirgek.client.view.MainView
import javafx.application.Application
import tornadofx.App

class Startup : App(MainView::class)

fun main322(args: Array<String>) {
    Application.launch(Startup::class.java, *args)
}
