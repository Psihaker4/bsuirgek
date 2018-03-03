package com.agt.bsuirgek.client.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class BtnMainMenuController extends HBox {

    @FXML
    private Button btnMain;

    @FXML
    private VBox vboxForElement;

    private boolean showPartMenu = false;


    public MenuLoadFileController partMenu;

    public BtnMainMenuController(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/BtnMainMenu.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try{
            loader.load();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void action(ActionEvent actionEvent){
        if(showPartMenu == false){
            partMenu = new MenuLoadFileController();
            vboxForElement.getChildren().add(1, partMenu);
            showPartMenu = true;
        }
        else{
            vboxForElement.getChildren().remove(1);
            showPartMenu = false;
        }
    }
}
