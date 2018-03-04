package com.agt.bsuirgek.client.Controller;

import com.agt.bsuirgek.client.UI.BtnMenu;
import com.agt.bsuirgek.client.UI.VBoxForCooseTemplate;
import com.agt.bsuirgek.client.UI.VBoxForLoadFile;
import com.agt.bsuirgek.client.network.ProcesingRequests;
import com.agt.bsuirgek.client.network.Queries;
import com.agt.bsuirgek.client.network.ServiceGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.File;


public class Controller {

    public static Queries req = ServiceGenerator.createService(Queries.class);

    @FXML
    private VBox vBoxForMenuBtn;

    @FXML
    private Pane mainPane;

    @FXML
    public void initialize() {
        //mainPane.setStyle("-fx-background-color: #424242");
        HBox btn = new BtnMenu(new VBoxForLoadFile().getMainVBox()).getMainHBox();
        vBoxForMenuBtn.getChildren().add(btn);

        //ProcesingRequests.getData();
        mainPane.setStyle("-fx-background-image: url(/MainBackend.jpg); \n" +
                "-fx-background-position: 50%;");

        //btn.getChildren().get(0).getStyle;
    }

    public void btnClick(ActionEvent actionEvent) {
        Object sourse = actionEvent.getSource();

        Button clickButton = (Button) sourse;

        switch (clickButton.getId()) {
            case "btnMenuFirst": {
                break;
            }
        }
    }


}
