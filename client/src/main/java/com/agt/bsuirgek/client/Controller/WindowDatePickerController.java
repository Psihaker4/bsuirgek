package com.agt.bsuirgek.client.Controller;

import com.agt.bsuirgek.client.App;
import com.agt.bsuirgek.client.UI.ModalitySaveWindow;
import com.agt.bsuirgek.client.UI.WindowDatePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WindowDatePickerController {

    private String date;
    private Pane heandPane;
    private WindowDatePicker parent;

    @FXML
    public Button btnClose;

    @FXML
    public DatePicker dpDate;

    public WindowDatePickerController(WindowDatePicker parent){
        this.parent = parent;
    }

    public void closeWindowDatePicker(ActionEvent actionEvent){
        Stage stage = new Stage();
        ModalitySaveWindow root = new ModalitySaveWindow();
        Scene scene = new Scene(root, 400, 120);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.initOwner(App.stage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();

        if(root.getController().isSave() == true){
            heandPane.getChildren().remove(parent);
        }
    }

    public void setMainPane(Pane pane){
        heandPane = pane;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }
}
