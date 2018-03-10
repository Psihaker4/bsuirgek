package com.agt.bsuirgek.client.UI;

import com.agt.bsuirgek.client.Controller.FieldWindowPersonController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class FieldWindowPerson extends Pane {

    private FieldWindowPersonController controller = new FieldWindowPersonController();

    public FieldWindowPerson(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FieldWindowPerson.fxml"));
        loader.setController(controller);
        loader.setRoot(this);

        try{
            loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public FieldWindowPersonController getController() {
        return controller;
    }
}
