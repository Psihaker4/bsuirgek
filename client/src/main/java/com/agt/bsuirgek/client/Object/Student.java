package com.agt.bsuirgek.client.Object;

import java.util.Map;

public class Student {

    public String surname;
    public String name;
    public String patronymic;
    public String theme;
    public String group;
    public String average;
    public String paymentPercent;
    private Map<String, String> mapStident;

    public Student(Map<String, String> map){
        mapStident = map;
        surname = map.get("surname");
        name = map.get("name");
        patronymic = map.get("patronymic");
        theme = map.get("theme");
        group = map.get("group");
        average = map.get("average");
        paymentPercent = map.get("paymentPercent");
    }
}
