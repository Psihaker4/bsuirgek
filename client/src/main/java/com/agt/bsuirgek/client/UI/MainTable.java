package com.agt.bsuirgek.client.UI;

import com.agt.bsuirgek.client.Controller.MainTableController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainTable extends VBox {

    private MainTableController controller;

    public MainTable(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainTable.fxml"));
        controller = new MainTableController(this);
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
