package com.agt.bsuirgek.client.UI;

import com.agt.bsuirgek.client.Controller.ModalitySaveWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ModalitySaveWindow extends Pane {

    private ModalitySaveWindowController controller = new ModalitySaveWindowController();

    public ModalitySaveWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SaveModalityWindow.fxml"));
        loader.setRoot(this);
        loader.setController(controller);

        try{
            loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public ModalitySaveWindowController getController() {
        return controller;
    }
}
