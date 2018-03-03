package com.agt.bsuirgek.client.Controller;

import com.agt.bsuirgek.client.network.Queries;
import com.agt.bsuirgek.client.network.ServiceGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import net.ButtonForMenuController;
import net.PartMenu;


public class Controller {

    Queries req = ServiceGenerator.createService(Queries.class);

    private PartMenu partMenu = new PartMenu();

    @FXML
    public Button btnMenuFirst;

    @FXML
    public HBox idHBox;

    @FXML
    public ButtonForMenuController btnMenu;

    @FXML
    public Pane mainPane;

    @FXML
    public void initialize() {
        BtnMainMenuController btnMainMenu = new BtnMainMenuController();
        //MenuLoadFileController btnMainMenu = new MenuLoadFileController();
        btnMainMenu.setLayoutX(400);
        btnMainMenu.setLayoutY(400);

        mainPane.getChildren().add(btnMainMenu);
    }

    public void btnClick(ActionEvent actionEvent) {
        Object sourse = actionEvent.getSource();

        Button clickButton = (Button) sourse;

        switch (clickButton.getId()) {
            case "btnMenuFirst": {
                //ProcesingRequests.uploadFile(req);

                idHBox.getChildren().add(new PartMenu());

                btnMenu.setPartMenu(new PartMenu());
                break;
            }
        }
    }


}
