package com.agt.bsuirgek.server.model

import org.jetbrains.exposed.dao.IntIdTable

object Students : IntIdTable() {
    val surname = varchar("surname", Int.MAX_VALUE)
    val name = varchar("type", Int.MAX_VALUE).nullable()
    val patronymic = varchar("patronymic", Int.MAX_VALUE).nullable()
    val theme = varchar("theme", Int.MAX_VALUE).nullable()
    val group = integer("group").nullable()
    val average = decimal("average",5,5).nullable()
    val paymentPercent = integer("paymentPercent").nullable()
}

object Teachers : IntIdTable() {
    val surname = varchar("surname", Int.MAX_VALUE)
    val name = varchar("type", Int.MAX_VALUE).nullable()
    val patronymic = varchar("patronymic", Int.MAX_VALUE).nullable()
    val phone = varchar("phone", Int.MAX_VALUE).nullable()
    val data = varchar("data", Int.MAX_VALUE).nullable()
}

object Templates : IntIdTable() {
    val name = varchar("name", Int.MAX_VALUE)
    val date = varchar("date", Int.MAX_VALUE).nullable()
}
