package com.agt.bsuirgek.client.Controller;

import com.agt.bsuirgek.client.UI.MainStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MenuLoadFileController extends VBox {

    @FXML
    private Button btn;

    public MenuLoadFileController(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuChooseFile.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void action(ActionEvent actionEvent){
        System.out.println("dgsdgsrg");
    }

}
