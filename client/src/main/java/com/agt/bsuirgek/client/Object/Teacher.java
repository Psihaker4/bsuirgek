package com.agt.bsuirgek.client.Object;

import java.util.Map;

public class Teacher {

    public String surname;
    public String name;
    public String patronymic;
    public String data;
    public String phone;
    private Map<String, String> map;

    public Teacher(Map<String, String> map){
        this.map = map;
        surname = map.get("surname");
        name = map.get("name");
        patronymic = map.get("patronymic");
        data = map.get("data");
        phone = map.get("phone");
    }
}