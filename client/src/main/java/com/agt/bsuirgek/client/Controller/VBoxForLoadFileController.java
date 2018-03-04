package com.agt.bsuirgek.client.Controller;

import com.agt.bsuirgek.client.UI.MainStage;
import com.agt.bsuirgek.client.network.ProcesingRequests;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;


public class VBoxForLoadFileController {

    public File actionBtnLoad(TextField tfWayFile, Button btnOk){
        FileChooser filechoos = new FileChooser();
        File file = null;

            file = filechoos.showOpenDialog(MainStage.mainStage);
            if(file != null){
                tfWayFile.setText(file.getName());
                btnOk.setDisable(false);
                return file;
            }
            else{
            }
            return file;

    }

    public void actionBtnOk(File file){
        ProcesingRequests.uploadFile(Controller.req, file);
    }

}
