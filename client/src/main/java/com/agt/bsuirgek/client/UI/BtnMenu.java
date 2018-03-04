package com.agt.bsuirgek.client.UI;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BtnMenu {

    private double width = 200;
    private VBox vboxForElements;
    private Button btnMenu;
    private boolean showMenu = false;
    private VBox vBoxForLoadFile;
    private HBox hboxMain;

    public BtnMenu(VBox mainPartMenu){
        vBoxForLoadFile = mainPartMenu;

        HBox hboxMain = new HBox();
        hboxMain.setPrefSize(80,80);
        this.hboxMain = hboxMain;

        btnMenu = new Button();
        btnMenu.setPrefSize(80,80);
        btnMenu.setMinSize(80,80);
        btnMenu.setMaxSize(80,80);

        hboxMain.getChildren().add(btnMenu);

        vboxForElements = new VBox();
        vboxForElements.setPrefWidth(width);
        vboxForElements.setMinWidth(width);
        vboxForElements.setMaxWidth(width);

        //vBoxForLoadFile.setVisible(false);
        //vboxForElements.getChildren().add(vBoxForLoadFile);

        btnMenu.setOnAction(event -> {
            if(showMenu == false){
                vboxForElements.getChildren().add(vBoxForLoadFile);
                showMenu = true;
                //vBoxForLoadFile.setVisible(true);

            }
            else{
                vboxForElements.getChildren().clear();
                showMenu = false;
                //vBoxForLoadFile.setVisible(false);
            }
        });

        hboxMain.getChildren().add(vboxForElements);
    }

    public void addEleventOnMenu(Node elment){
        vboxForElements.getChildren().add(elment);
    }

    public HBox getMainHBox(){
        return hboxMain;
    }


}
