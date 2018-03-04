package com.agt.bsuirgek.client.UI;

import com.agt.bsuirgek.client.Controller.VBoxForLoadFileController;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;

public class VBoxForLoadFile {

    private VBox vboxMain;
    private Button btnLoad;
    private Button btnOk;

    private VBoxForLoadFileController controller = new VBoxForLoadFileController();
    private File choosedFile;

    public VBoxForLoadFile(){

        VBox mainVBox = new VBox();
        mainVBox.setPrefSize(200,175);
        mainVBox.setMinSize(200,175);
        mainVBox.setMaxSize(200,175);

        HBox hboxLoadFile = new HBox();
        hboxLoadFile.setPrefSize(200,40);
        hboxLoadFile.setMinSize(200,40);
        hboxLoadFile.setMaxSize(200,40);

        TextField tfWay = new TextField();
        tfWay.setPrefSize(125,30);
        tfWay.setMinSize(125,30);
        tfWay.setMaxSize(125,30);

        AnchorPane anchorTextField = new AnchorPane();
        anchorTextField.setPrefSize(135,40);
        anchorTextField.setMinSize(135,40);
        anchorTextField.setMaxSize(135,40);
        AnchorPane.setTopAnchor(tfWay, 5.0);
        AnchorPane.setRightAnchor(tfWay, 5.0);
        AnchorPane.setBottomAnchor(tfWay, 5.0);
        AnchorPane.setLeftAnchor(tfWay, 5.0);
        anchorTextField.setStyle("-fx-background-color: #666666");
        anchorTextField.getChildren().add(tfWay);
        hboxLoadFile.getChildren().add(anchorTextField);

        Button btnLoad = new Button("Открыть");
        this.btnLoad = btnLoad;
        btnLoad.setPrefSize(60,30);
        btnLoad.setMinSize(60,30);
        btnLoad.setMaxSize(60,30);
        btnLoad.setOnAction(event -> {
            choosedFile = controller.actionBtnLoad(tfWay, btnOk);
        });

        AnchorPane anchorBtnLoad = new AnchorPane();
        anchorBtnLoad.setPrefSize(65,40);
        anchorBtnLoad.setMinSize(65,40);
        anchorBtnLoad.setMaxSize(65,40);
        AnchorPane.setTopAnchor(btnLoad, 5.0);
        AnchorPane.setRightAnchor(btnLoad, 5.0);
        AnchorPane.setBottomAnchor(btnLoad, 5.0);
        AnchorPane.setLeftAnchor(btnLoad, 0.0);
        anchorBtnLoad.setStyle("-fx-background-color: #666666");
        anchorBtnLoad.getChildren().add(btnLoad);
        hboxLoadFile.getChildren().add(anchorBtnLoad);
        mainVBox.getChildren().add(hboxLoadFile);

        VBox vboxChooseTemplate = new VBoxForCooseTemplate().getVBox();
        mainVBox.getChildren().add(vboxChooseTemplate);

        HBox hboxBtnOkCancel = new HBox();
        hboxBtnOkCancel.setPrefSize(200,35);
        hboxBtnOkCancel.setMinSize(200,35);
        hboxBtnOkCancel.setMaxSize(200,35);

        Button btnOk = new Button("OK");
        this.btnOk = btnOk;
        btnOk.setDisable(true);
        btnOk.setPrefSize(60,30);
        btnOk.setMinSize(60,30);
        btnOk.setMaxSize(60,30);
        btnOk.setOnAction(event -> {
            controller.actionBtnOk(choosedFile);
        });

        AnchorPane anchorBtnOk = new AnchorPane();
        anchorBtnOk.setPrefSize(200,35);
        anchorBtnOk.setMinSize(200,35);
        anchorBtnOk.setMaxSize(200,35);
        AnchorPane.setTopAnchor(btnOk, 5.0);
        AnchorPane.setRightAnchor(btnOk, 5.0);
        AnchorPane.setBottomAnchor(btnOk, 5.0);
        AnchorPane.setLeftAnchor(btnOk, 135.0);
        anchorBtnOk.setStyle("-fx-background-color: #666666");
        anchorBtnOk.getChildren().add(btnOk);
        hboxBtnOkCancel.getChildren().add(anchorBtnOk);

        mainVBox.getChildren().add(hboxBtnOkCancel);

        vboxMain = mainVBox;
    }

    public VBox getMainVBox(){
        return vboxMain;
    }

}
