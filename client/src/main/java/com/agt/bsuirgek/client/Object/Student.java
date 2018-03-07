package com.agt.bsuirgek.client.Object;

import java.util.Map;

public class Student {

    private String surname;
    private String name;
    private String patronymic;
    private String theme;
    private String group;
    private String average;
    private String paymentPercent;

    public Student(Map<String, String> map){
        surname = map.get("surname");
        name = map.get("name");
        patronymic = map.get("patronymic");
        theme = map.get("name");
        group = map.get("group");
        average = map.get("average");
        paymentPercent = map.get("paymentProcent");
    }

    @Override
    public String toString(){
        return new String(name + " " + surname);
    }
}
