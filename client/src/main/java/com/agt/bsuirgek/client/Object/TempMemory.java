package com.agt.bsuirgek.client.Object;

//import com.agt.bsuirgek.connectionService.model.Student;
//import com.agt.bsuirgek.connectionService.model.Teacher;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TempMemory {
    public static ArrayList<Teacher> listTempTeacher = new ArrayList<>();
    public static ArrayList<Student> listTempStudent = new ArrayList<>();
    public static Map<String, String> RUS_NAME_FIELD_TEACHER = getRusNameFieldTeacher();
    public static Map<String, String> RUS_NAME_FIELD_STUDENT = getRusNameFieldStudent();
    public static ArrayList<String> TEMPLATE = new ArrayList<>();

    static private Map<String, String> getRusNameFieldTeacher(){
        Map<String, String> rusNameField = new HashMap<>();
        rusNameField.put("name", "Имя:");
        rusNameField.put("surname", "Фамилия:");
        rusNameField.put("data", "Данные:");
        rusNameField.put("phone", "Телефон:");
        rusNameField.put("patronymic", "Отчество:");
        return rusNameField;
    }

    static private Map<String, String> getRusNameFieldStudent(){
        Map<String, String> rusNameField = new HashMap<>();
        rusNameField.put("name", "Имя:");
        rusNameField.put("surname", "Фамилия:");
        rusNameField.put("patronymic", "Отчество:");
        rusNameField.put("theme", "Темы:");
        rusNameField.put("group", "Группа:");
        rusNameField.put("average", "Средний балл:");
        rusNameField.put("paymentPercent", "Оплата:");
        return rusNameField;
    }
}
