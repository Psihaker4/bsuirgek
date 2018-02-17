package com.agt.bsuirgek.client.view

import javafx.event.EventType
import javafx.fxml.FXMLLoader
import javafx.scene.input.DragEvent
import javafx.scene.input.MouseEvent
import javafx.scene.layout.AnchorPane
import tornadofx.*

class MainView : View("Main View") {
    override val root: AnchorPane = FXMLLoader.load(javaClass.getResource("/MainView.fxml"))
}
