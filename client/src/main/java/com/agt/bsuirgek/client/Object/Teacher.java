package com.agt.bsuirgek.client.Object;

import java.util.Map;

public class Teacher {

    private String surname;
    private String name;
    private String patronymic;
    private String data;
    private String phone;
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