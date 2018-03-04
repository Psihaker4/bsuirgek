package com.agt.bsuirgek.client.UI;

import com.agt.bsuirgek.client.Object.TypeTemplate;
import javafx.geometry.Orientation;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import java.util.List;

public class VBoxForCooseTemplate {

    private VBox vbMain;
    private List<TypeTemplate> list;

    public VBoxForCooseTemplate(){
        vbMain = new VBox();
        vbMain.setPrefSize(200,100);
        vbMain.setMinSize(200,100);
        vbMain.setMaxSize(200,100);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(200,100);
        scrollPane.setMinSize(200,100);
        scrollPane.setMaxSize(200,100);

        ListView lvTemplate = new ListView();
        lvTemplate.setStyle("-fx-background-color: #666666");
        lvTemplate.setPrefSize(185,400);
        lvTemplate.setMinSize(185,400);
        lvTemplate.setMaxSize(185,400);

        scrollPane.setContent(lvTemplate);
        vbMain.getChildren().add(scrollPane);

    }

    public VBox getVBox(){
        return vbMain;
    }

    public void setList(List<TypeTemplate> list){
        this.list = list;
    }
}
