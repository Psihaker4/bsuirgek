package com.agt.bsuirgek.client.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FieldWindowPersonController {

    @FXML
    public Label labelNameField;

    @FXML
    public TextField tfText;

    public void setTextLable(String text){ labelNameField.setText(text); }

    public  void setTfText(String text){ tfText.setText(text);}
}
