package com.agt.bsuirgek.client.UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainStage {

    public static Scene root;

    public MainStage(Stage mainStage) throws IOException {
        //root = new BorderPane();
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        Scene loyaut = new Scene(root, 1360, 920);
        this.root = loyaut;
        mainStage.setScene(loyaut);
        mainStage.show();

    }
}
