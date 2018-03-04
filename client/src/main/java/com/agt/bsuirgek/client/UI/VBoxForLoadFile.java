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
    private File choosedFile;

    private Button btnLoad;
    private Button btnOk;
    private Button btnCancel;
    private VBoxForLoadFileController controller = new VBoxForLoadFileController();

    public VBoxForLoadFile(){

        VBox mainVBox = new VBox();
        mainVBox.setPrefSize(200,75);
        mainVBox.setMinSize(200,75);
        mainVBox.setMaxSize(200,75);

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
        anchorTextField.setStyle("-fx-background-color: #cf1020");
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
        anchorBtnLoad.setStyle("-fx-background-color: #cf1020");
        anchorBtnLoad.getChildren().add(btnLoad);
        hboxLoadFile.getChildren().add(anchorBtnLoad);

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
        anchorBtnOk.setPrefSize(135,35);
        anchorBtnOk.setMinSize(135,35);
        anchorBtnOk.setMaxSize(135,35);
        AnchorPane.setTopAnchor(btnOk, 0.0);
        AnchorPane.setRightAnchor(btnOk, 20.0);
        AnchorPane.setBottomAnchor(btnOk, 5.0);
        AnchorPane.setLeftAnchor(btnOk, 55.0);
        anchorBtnOk.setStyle("-fx-background-color: #cf1020");
        anchorBtnOk.getChildren().add(btnOk);
        hboxBtnOkCancel.getChildren().add(anchorBtnOk);

        Button btnCancel = new Button("Отмена");
        this.btnCancel = btnCancel;
        btnCancel.setPrefSize(60,30);
        btnCancel.setMinSize(60,30);
        btnCancel.setMaxSize(60,30);
        btnCancel.setOnAction(event -> {
            controller.actionBtnCancel(tfWay, btnOk);
        });

        AnchorPane anchorBtnCancel = new AnchorPane();
        anchorBtnCancel.setPrefSize(65,35);
        anchorBtnCancel.setMinSize(65,35);
        anchorBtnCancel.setMaxSize(65,35);
        AnchorPane.setTopAnchor(btnCancel, 0.0);
        AnchorPane.setRightAnchor(btnCancel, 5.0);
        AnchorPane.setBottomAnchor(btnCancel, 5.0);
        AnchorPane.setLeftAnchor(btnCancel, 0.0);
        anchorBtnCancel.setStyle("-fx-background-color: #cf1020");
        anchorBtnCancel.getChildren().add(btnCancel);
        hboxBtnOkCancel.getChildren().add(anchorBtnCancel);

        mainVBox.getChildren().addAll(hboxLoadFile, hboxBtnOkCancel);

        vboxMain = mainVBox;
    }

    public VBox getMainVBox(){
        return vboxMain;
    }

}
