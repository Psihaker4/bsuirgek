package com.agt.bsuirgek.client.Controller;

import com.agt.bsuirgek.client.network.ProcesingRequests;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
