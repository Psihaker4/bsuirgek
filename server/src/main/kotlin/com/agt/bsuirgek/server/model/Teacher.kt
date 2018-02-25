package com.agt.bsuirgek.server.model

import org.jetbrains.exposed.dao.IntIdTable

class Teacher(map: Map<String, String>) {
    val surname = map["surname"]
    val name = map["name"]
    val patronymic = map["patronymic"]
    val data = map["data"]
    val phone = map["phone"]
    override fun toString() = "Teacher($surname;$name;$patronymic;$data;$phone)"
}

object Teachers : IntIdTable() {
    val surname = varchar("surname", Int.MAX_VALUE)
    val name = varchar("name", Int.MAX_VALUE).nullable()
    val patronymic = varchar("patronymic", Int.MAX_VALUE).nullable()
    val data = varchar("data", Int.MAX_VALUE).nullable()
    val phone = varchar("phone", Int.MAX_VALUE).nullable()
}