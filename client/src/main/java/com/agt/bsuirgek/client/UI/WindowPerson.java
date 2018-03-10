package com.agt.bsuirgek.client.UI;

import com.agt.bsuirgek.client.Controller.WindowPersonController;
import com.agt.bsuirgek.client.Object.Teacher;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.lang.reflect.Field;

public class WindowPerson extends VBox {

    private WindowPersonController controller = new WindowPersonController(this);

    public WindowPerson(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/WindowPerson.fxml"));
        loader.setController(controller);
        loader.setRoot(this);

        try{
            loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public WindowPersonController getController(){
        return controller;
    }


}
