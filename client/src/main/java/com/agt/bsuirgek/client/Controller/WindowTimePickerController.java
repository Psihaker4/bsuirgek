package com.agt.bsuirgek.client.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class WindowTimePickerController {
    private int initialValue = 1;

    @FXML
    public Spinner spinHour;

    @FXML
    public Spinner spinMinute;

    @FXML
    public void initialize() {
        spinHour.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 24, initialValue));
        spinMinute.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, initialValue));


    }
}
