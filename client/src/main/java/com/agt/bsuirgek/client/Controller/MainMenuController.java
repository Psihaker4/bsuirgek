package com.agt.bsuirgek.client.Controller;

import com.agt.bsuirgek.client.App;
import com.agt.bsuirgek.client.Object.TempMemory;
import com.agt.bsuirgek.client.Object.TypeTemplate;
import com.agt.bsuirgek.client.network.ProcesingRequests;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;

public class MainMenuController {

    private File file;

    @FXML
    public Button btnLoad;

    @FXML
    public Button btnOK;

    @FXML
    public TextField tfFileName;

    @FXML
    public ListView<String> lvSetTemplate;

    private ObservableList<String> list = FXCollections.observableArrayList(TempMemory.TEMPLATE);

    @FXML
    public void initialize() {

        lvSetTemplate.setItems(list);
        tfFileName.setEditable(false);
        tfFileName.setText("Имя файла");
        //tfFileName.setId("tfNameFile");

        lvSetTemplate.setOnMouseClicked(event -> {

        });
    }

    public void btnLoadAction(ActionEvent actionEvent) {
        //file = new VBoxForLoadFileController().actionBtnLoad(tfFileName, btnOK);
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("WordDoc (*.docx)", "*.docx");

        FileChooser filechoos = new FileChooser();
        filechoos.getExtensionFilters().add(filter);

        File tempFile;

        tempFile = filechoos.showOpenDialog(App.stage);
        if(tempFile != null){
            file = tempFile;
            tfFileName.setText(file.getName());
            btnOK.setDisable(false);
        }

    }

    public void btnOKAction(ActionEvent actionEvent) {
        System.out.println(lvSetTemplate.getSelectionModel().getSelectedItems());
        if(file != null && lvSetTemplate.getFocusModel().getFocusedItem() != null){ ProcesingRequests.uploadFile(Controller.req, file, lvSetTemplate.getFocusModel().getFocusedItem()); }
        else {
            System.out.println("выбрано не все !");//message box with error "File not choosing
        }
    }

    public String getTemlate(){
        return lvSetTemplate.getSelectionModel().getSelectedItem();
    }
}
