package com.agt.bsuirgek.client.UI;

import com.agt.bsuirgek.client.Controller.MainTableController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MainTable extends Pane {

    private MainTableController controller = new MainTableController();

    public MainTable(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainTable.fxml"));
        loader.setRoot(this);
        loader.setController(controller);

        try{
            loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public MainTableController getController() {
        return controller;
    }
}
