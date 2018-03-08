package com.agt.bsuirgek.client.Controller;

import com.agt.bsuirgek.client.network.ProcesingRequests;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.File;

public class MainMenuController {

    private File file;

    @FXML
    public Button btnLoad;

    @FXML
    public Button btnOK;

    @FXML
    public TextField tfFileName;

    @FXML
    public ListView<String> lvSetTemplate;

    private ObservableList<String> list = FXCollections.observableArrayList("аблон 1", "шаблон 2", "шаблон 3", "аблон 1", "шаблон 2", "шаблон 3", "аблон 1", "шаблон 2", "шаблон 3", "аблон 1", "шаблон 2", "шаблон 3");

    @FXML
    public void initialize() {
        lvSetTemplate.setItems(list);
    }

    public void btnLoadAction(ActionEvent actionEvent) {
        file = new VBoxForLoadFileController().actionBtnLoad(tfFileName, btnOK);
    }

    public void btnOKAction(ActionEvent actionEvent) {
        if(file != null){ ProcesingRequests.uploadFile(Controller.req, file); }
        else {
            //message box with error "File not choosing
        }
    }
}
