package com.agt.bsuirgek.client.Controller;

import com.agt.bsuirgek.client.Object.Student;
import com.agt.bsuirgek.client.Object.TempMemory;
import com.agt.bsuirgek.client.UI.MainTable;
import com.sun.javafx.scene.control.skin.TableColumnHeader;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainTableController {

    private MainTable parent;
    private ArrayList<TableColumn<Student, String>> mapColumn = new ArrayList<>();


    @FXML
    public TableView<Student> mainTable;

    public MainTableController(MainTable parent){
        this.parent = parent;

        for(Map.Entry<String, String> entry : TempMemory.listTempStudent.get(1).getMapStident().entrySet()){
            mapColumn.add( new TableColumn<Student, String>(entry.getKey()));
            System.out.println(mapColumn.get(mapColumn.size() - 1));
            mainTable.getColumns().add(mapColumn.get(mapColumn.size()-1));
        }
    }
}
