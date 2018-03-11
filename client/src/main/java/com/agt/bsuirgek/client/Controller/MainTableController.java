package com.agt.bsuirgek.client.Controller;

import com.agt.bsuirgek.client.Object.Student;
import com.agt.bsuirgek.client.Object.TempMemory;
import com.agt.bsuirgek.client.UI.MainTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

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
        mainTable.setEditable(true);

        ObservableList<Student> items = FXCollections.observableArrayList(TempMemory.listTempStudent);
        mainTable.setItems(items);

        for (Map.Entry<String, String> entry : TempMemory.listTempStudent.get(1).getMapStident().entrySet()) {
            quantityColumn++;
            mapColumn.put(entry.getKey() ,new TableColumn<Student, String>(TempMemory.RUS_NAME_FIELD_STUDENT.get(entry.getKey())));
            mapColumn.get(entry.getKey()).setCellValueFactory(new PropertyValueFactory<>(entry.getKey()));

            mapColumn.get(entry.getKey()).setCellFactory(TextFieldTableCell.<Student> forTableColumn());

            mapColumn.get(entry.getKey()).setOnEditCommit((TableColumn.CellEditEvent<Student, String> event) -> {
                TablePosition<Student, String> pos = event.getTablePosition();

                String newString = event.getNewValue();

                int row = pos.getRow();
                Student person = event.getTableView().getItems().get(row);

                switch (entry.getKey()){
                    case"surname":{
                        person.setSurname(newString);
                        break;
                    }
                    case"name":{
                        person.setName(newString);
                        break;
                    }
                    case"patronymic":{
                        person.setPatronymic(newString);
                        break;
                    }
                    case"theme":{
                        person.setTheme(newString);
                        break;
                    }
                    case"group":{
                        person.setGroup(newString);
                        break;
                    }
                    case"average":{
                        person.setAverage(newString);
                        break;
                    }
                    case"paymentPercent":{
                        person.setPaymentPercent(newString);
                        break;
                    }
                }

                System.out.println(TempMemory.listTempStudent.get(0).name);
            });

            mainTable.getColumns().add(mapColumn.get(entry.getKey()));
        }
        double widthColumn = mainTable.getPrefWidth()/quantityColumn;
        for(Map.Entry<String, TableColumn<Student, String>> entry : mapColumn.entrySet()){
            entry.getValue().setPrefWidth(widthColumn);
            entry.getValue().setMinWidth(widthColumn);
            entry.getValue().setMaxWidth(widthColumn);
        }
    }
}