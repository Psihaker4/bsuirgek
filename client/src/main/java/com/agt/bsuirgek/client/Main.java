package com.agt.bsuirgek.client;

import com.agt.bsuirgek.client.UI.MainStage;
import javafx.application.Application;
import javafx.stage.Stage;

public class  Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        MainStage stage = new MainStage(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
