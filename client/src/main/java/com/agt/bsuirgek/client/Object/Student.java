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

    public Map<String, String> getMapStident(){
        return mapStident;
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

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public void setPaymentPercent(String paymentPercent) {
        this.paymentPercent = paymentPercent;
    }
}
