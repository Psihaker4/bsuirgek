package com.agt.bsuirgek.client.UI;

import com.agt.bsuirgek.client.Controller.WindowTimePickerController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class WindowTimePicker extends VBox {

    private WindowTimePickerController controller = new WindowTimePickerController();

    public WindowTimePicker() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/WindowTimePicker.fxml"));

        loader.setRoot(this);
        loader.setController(controller);

        try {
            loader.load();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public WindowTimePickerController getController() {
        return controller;
    }
}
