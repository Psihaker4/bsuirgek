package com.agt.bsuirgek.client.Object;

import java.util.Map;

public class Teacher {

    private String surname;
    private String name;
    private String patronymic;
    private String data;
    private String phone;

    public Teacher(Map<String, String> map){
        surname = map.get("surname");
        name = map.get("name");
        patronymic = map.get("patronymic");
        data = map.get("data");
        phone = map.get("phone");
    }

    @Override
    public String toString(){
        return new String(name + " " + surname);
    }
}

