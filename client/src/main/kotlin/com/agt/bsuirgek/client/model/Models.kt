package com.agt.bsuirgek.client.model

class Temp(val name: String, val date: String)

class Student(val map: Map<String, String>){
    val surname = map["surname"]
    val name = map["name"]
    val patronymic = map["patronymic"]
    val theme = map["theme"]
    val group = map["group"]?.toInt()
    val average = map["average"]?.toDouble()
    val paymentPercent = map["paymentPercent"]?.removePrefix(" ")?.removeSuffix(" ")?.removeSuffix("%")?.toDouble()
    override fun toString() = "Student($surname;$name;$patronymic;$theme;$group;$average;$paymentPercent)"
}

class Teacher(val map: Map<String, String>){
    val surname = map["surname"]
    val name = map["name"]
    val patronymic = map["patronymic"]
    val data = map["data"]
    val phone = map["phone"]
    override fun toString() = "Teacher($surname;$name;$patronymic;$data;$phone)"
}
