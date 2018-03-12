package com.agt.bsuirgek.client.Controller;

import com.agt.bsuirgek.client.Object.TempMemory;
import com.agt.bsuirgek.client.UI.*;
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
import java.util.HashMap;
import java.util.Map;


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

        ProcesingRequests a = new ProcesingRequests();
        a.getData();

       WindowPerson person = new WindowPerson();
        person.getController().setPersonTeacher(TempMemory.listTempTeacher.get(1), 1);
        person.getController().setMainPane(mainPane);
        person.setLayoutX(400);
        person.setLayoutY(400);

        WindowPerson person2 = new WindowPerson();
        person2.getController().setPersonStudent(TempMemory.listTempStudent.get(1), 1);
        person2.getController().setMainPane(mainPane);
        person2.setLayoutX(1100);
        person2.setLayoutY(400);

        WindowDatePicker datePicker = new WindowDatePicker();
        datePicker.getController().setMainPane(mainPane);
        datePicker.setLayoutX(400);
        datePicker.setLayoutY(0);
        mainPane.getChildren().add(datePicker);

        mainPane.getChildren().add(person);
        mainPane.getChildren().add(person2);
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
