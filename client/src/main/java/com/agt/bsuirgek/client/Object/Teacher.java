package com.agt.bsuirgek.client.Object;

import java.util.Map;

public class Teacher {

    public String surname;
    public String name;
    public String patronymic;
    public String data;
    public String phone;
    private Map<String, String> mapTeacher;

    public Teacher(Map<String, String> map){
        mapTeacher = map;
        surname = map.get("surname");
        name = map.get("name");
        patronymic = map.get("patronymic");
        data = map.get("data");
        phone = map.get("phone");
    }
}