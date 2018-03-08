package com.agt.bsuirgek.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        Scene layout = new Scene(root, 1366, 768);
        primaryStage.setScene(layout);
        primaryStage.setMaximized(true);
        primaryStage.show();

        stage = primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
