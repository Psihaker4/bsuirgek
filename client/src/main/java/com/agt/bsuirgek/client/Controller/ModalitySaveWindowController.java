package com.agt.bsuirgek.client.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ModalitySaveWindowController {

    @FXML
    public Button btnOK;

    @FXML
    public Button btnNO;

    private boolean save = false;

    public void btnOKClick(ActionEvent actionEvent){
        save = true;
        Stage stage = (Stage) btnNO.getScene().getWindow();
        stage.close();
    }

    public void btnNOClick(ActionEvent actionEvent){
        Stage stage = (Stage) btnNO.getScene().getWindow();
        stage.close();
    }

    public boolean isSave() {
        return save;
    }
}
