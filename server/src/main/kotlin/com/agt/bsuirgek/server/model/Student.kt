package com.agt.bsuirgek.server.model

import org.jetbrains.exposed.dao.IntIdTable

class Student(val map: Map<String, String>) {
    val surname = map["surname"]
    val name = map["name"]
    val patronymic = map["patronymic"]
    val theme = map["theme"]
    val group = map["group"]
    val average = map["average"]
    val paymentPercent = map["paymentPercent"]
    override fun toString() = "Student($surname;$name;$patronymic;$theme;$group;$average;$paymentPercent)"
}

object Students : IntIdTable() {
    val surname = varchar("surname",Int.MAX_VALUE)
    val name = varchar("name",Int.MAX_VALUE).nullable()
    val patronymic = varchar("patronymic",Int.MAX_VALUE).nullable()
    val theme = varchar("theme",Int.MAX_VALUE).nullable()
    val group = varchar("group",Int.MAX_VALUE).nullable()
    val average = varchar("average",Int.MAX_VALUE).nullable()
    val paymentPercent = varchar("paymentPercent",Int.MAX_VALUE).nullable()
}