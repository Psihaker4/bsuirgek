package com.agt.bsuirgek.client.Controller;

import com.agt.bsuirgek.client.Object.Student;
import com.agt.bsuirgek.client.Object.TempMemory;
import com.agt.bsuirgek.client.UI.MainTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.HashMap;
import java.util.Map;

public class MainTableController {

    private MainTable parent;
    private Map<String, TableColumn<Student, String>> mapColumn = new HashMap<>();
    private int quantityColumn = 0;

    @FXML
    public TableView<Student> mainTable;

    public MainTableController(MainTable parent) {
        this.parent = parent;
    }

    @FXML
    public void initialize() {

        ObservableList<Student> items = FXCollections.observableArrayList(TempMemory.listTempStudent);

        quantityColumn = TempMemory.listTempStudent.get(1).getMap().size();

        for (String key : TempMemory.listTempStudent.get(1).getMap().keySet()) {
            TableColumn<Student, String> column = new TableColumn<>(TempMemory.RUS_NAME_FIELD_STUDENT.get(key));
            column.setCellValueFactory(new PropertyValueFactory<>(key));
            mapColumn.put(key, column);
        }

        mainTable.setItems(items);
        mainTable.getColumns().addAll(mapColumn.values());

        double widthColumn = mainTable.getPrefWidth()/quantityColumn;
        for(TableColumn<Student, String> entry : mapColumn.values()) {
            entry.setPrefWidth(widthColumn);
            entry.setMinWidth(widthColumn);
            entry.setMaxWidth(widthColumn);
        }
    }
}