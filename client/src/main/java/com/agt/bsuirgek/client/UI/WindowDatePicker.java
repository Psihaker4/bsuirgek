package com.agt.bsuirgek.client.UI;

import com.agt.bsuirgek.client.Controller.WindowDatePickerController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class WindowDatePicker extends VBox{

    private WindowDatePickerController controller = new WindowDatePickerController(this);

    public WindowDatePicker(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/WindowDatePicker.fxml"));
        loader.setRoot(this);
        loader.setController(controller);

        try{
            loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public WindowDatePickerController getController() {
        return controller;
    }
}
