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
    private Map<String, String> map;

    public Student(Map<String, String> map){
        this.map = map;
        surname = map.get("surname");
        name = map.get("name");
        patronymic = map.get("patronymic");
        theme = map.get("theme");
        group = map.get("group");
        average = map.get("average");
        paymentPercent = map.get("paymentPercent");
    }

    public Map<String, String> getMap(){
        return map;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getTheme() {
        return theme;
    }

    public String getGroup() {
        return group;
    }

    public String getAverage() {
        return average;
    }

    public String getPaymentPercent() {
        return paymentPercent;
    }
}
