package com.agt.bsuirgek.client.UI;

import javafx.geometry.Orientation;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class VBoxForCooseTemplate {

    private VBox vbMain;

    public VBoxForCooseTemplate(){
        vbMain = new VBox();
        vbMain.setPrefSize(200,100);
        vbMain.setMinSize(200,100);
        vbMain.setMaxSize(200,100);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(200,100);
        scrollPane.setMinSize(200,100);
        scrollPane.setMaxSize(200,100);

        ListView list = new ListView();
        list.setPrefSize(185,400);
        list.setMinSize(185,400);
        list.setMaxSize(185,400);

        scrollPane.setContent(list);
        vbMain.getChildren().add(scrollPane);

    }

    public VBox getVBox(){
        return vbMain;
    }
}
