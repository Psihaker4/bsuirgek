package com.agt.bsuirgek.client.Controller;

import com.agt.bsuirgek.client.App;
import com.agt.bsuirgek.client.Object.Student;
import com.agt.bsuirgek.client.Object.Teacher;
import com.agt.bsuirgek.client.Object.TempMemory;
import com.agt.bsuirgek.client.UI.FieldWindowPerson;
import com.agt.bsuirgek.client.UI.ModalitySaveWindow;
import com.agt.bsuirgek.client.UI.WindowPerson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class WindowPersonController {
    private Map<String, FieldWindowPerson> massFieldWindowPerson = new HashMap<>();
    private boolean itIsStudent;
    private int id;

    @FXML
    public VBox mainPane;

    @FXML
    public Label headerLabel;

    @FXML
    public Button btnClose;

    private Student studentPerson;
    private Teacher teacherPerson;
    private Pane heandPane;
    private WindowPerson parent;
    private Pane lastPane;

    public WindowPersonController(WindowPerson parent){
        this.parent = parent;

        Pane lastLine = new Pane();
        lastLine.setPrefHeight(27);
        lastLine.setMinHeight(27);
        lastLine.setMaxHeight(27);
        lastLine.setId("lastLine");
        lastLine.setStyle("-fx-background-color: #9E9E9E;");

        lastPane = lastLine;
    }

    public void addTeacher(Teacher person) {
        Class a = person.getClass();
        Field[] fields = a.getFields();

        try {
            for (Field field : fields) {
                if (field.get(person) != null) {
                    massFieldWindowPerson.put(field.getName(), new FieldWindowPerson());

                    mainPane.getChildren().add(massFieldWindowPerson.get(field.getName()));
                    massFieldWindowPerson.get(field.getName()).getController().setTextLable(TempMemory.RUS_NAME_FIELD_TEACHER.get(field.getName()));
                    massFieldWindowPerson.get(field.getName()).getController().setTfText((String) field.get(person));
                }
            }

            mainPane.getChildren().add(lastPane);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addStudent(Student person){
        Class a = person.getClass();
        Field[] fields = a.getFields();

        try {
            for (Field field : fields) {
                if (field.get(person) != null) {
                    massFieldWindowPerson.put(field.getName(), new FieldWindowPerson());

                    mainPane.getChildren().add(massFieldWindowPerson.get(field.getName()));
                    massFieldWindowPerson.get(field.getName()).getController().setTextLable(TempMemory.RUS_NAME_FIELD_STUDENT.get(field.getName()));
                    massFieldWindowPerson.get(field.getName()).getController().setTfText((String) field.get(person));
                }
            }

            mainPane.getChildren().add(lastPane);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setPersonStudent(Student person, int id){
        itIsStudent = true;
        studentPerson = person;
        headerLabel.setText("Студент:");
        addStudent(person);
    }

    public void setPersonTeacher(Teacher person, int id){
        itIsStudent = false;
        teacherPerson = person;
        headerLabel.setText("Преподователь:");
        addTeacher(person);
    }

    public void closeWindowPerson(ActionEvent actionEvent) throws IOException {
        if(itIsStudent == true){
            Class a = studentPerson.getClass();
            Field[] fields = a.getFields();

            try {
                for (Field field : fields) {
                    if (field.get(studentPerson) != null) {
                        field.set(studentPerson, massFieldWindowPerson.get(field.getName()).getController().tfText.getText());
                    }
                }
                TempMemory.listTempStudent.add(id, studentPerson);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            Class a = teacherPerson.getClass();
            Field[] fields = a.getFields();

            try {
                for (Field field : fields) {
                    if (field.get(teacherPerson) != null) {
                        field.set(teacherPerson, massFieldWindowPerson.get(field.getName()).getController().tfText.getText());
                    }
                }
                TempMemory.listTempTeacher.add(id, teacherPerson);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        Stage stage = new Stage();
        ModalitySaveWindow root = new ModalitySaveWindow();
        Scene scene = new Scene(root, 400, 120);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.initOwner(App.stage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();

        if(root.getController().isSave() == true){
            heandPane.getChildren().remove(parent);
        }
    }

    public void setMainPane(Pane pane){
        heandPane = pane;
    }

    public WindowPerson getParent() {
        return parent;
    }
}
