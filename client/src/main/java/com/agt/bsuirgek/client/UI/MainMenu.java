package com.agt.bsuirgek.client.UI;

import com.agt.bsuirgek.client.Controller.MainMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainMenu extends VBox {

    public MainMenu(){
            try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainMenu.fxml"));
        loader.setController(new MainMenuController());
        loader.setRoot(this);
        loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
