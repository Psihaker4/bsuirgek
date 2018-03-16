package com.agt.bsuirgek.server.model

import java.awt.SystemColor.text

class Temp(val name: String, val date: String)

class Student(map: Map<String, String>){
    val surname = map["surname"]
    val name = map["name"]
    val patronymic = map["patronymic"]
    val theme = map["theme"]
    val group = map["group"]?.toInt()
    val average = map["average"]?.toDouble()
    val paymentPercent = map["paymentPercent"]?.removePrefix(" ")?.removeSuffix(" ")?.removeSuffix("%")?.toDouble()
    override fun toString() = "Student($surname;$name;$patronymic;$theme;$group;$average;$paymentPercent)"
}

class Teacher(map: Map<String, String>){
    val surname = map["surname"]
    val name = map["name"]
    val patronymic = map["patronymic"]

    val phoneNumber = map["phone_number"]
    val external = map["external"]
    val post = map["post"]

//    val facultyId = reference("faculty_id", if (archive) Faculties.Archive else Faculties.Main).nullable()
//    val pulpitId = reference("pulpit_id", if (archive) Pulpits.Archive else Pulpits.Main).nullable()

    override fun toString() = "Teacher($surname;$name;$patronymic;$phoneNumber;$post)"
}
