package com.agt.bsuirgek.client.Controller;

import com.agt.bsuirgek.client.UI.BtnMenu;
import com.agt.bsuirgek.client.UI.MainMenu;
import com.agt.bsuirgek.client.network.ProcesingRequests;
import com.agt.bsuirgek.client.network.Queries;
import com.agt.bsuirgek.client.network.ServiceGenerator;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
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
        mainPane.setId("mainPane");

        HBox btn = new BtnMenu(new MainMenu()).getMainHBox();
        vBoxForMenuBtn.getChildren().add(btn);

        ProcesingRequests rq = new ProcesingRequests();
        rq.getData(new File("/data.json"));
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
