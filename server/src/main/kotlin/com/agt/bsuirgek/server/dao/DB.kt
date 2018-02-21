package com.agt.bsuirgek.server.dao

import org.jetbrains.exposed.dao.IntIdTable

object Teachers : IntIdTable() {
    val surname = varchar("surname", Int.MAX_VALUE).nullable()
    val name = varchar("name", Int.MAX_VALUE).nullable()
    val patronymic = varchar("patronymic", Int.MAX_VALUE).nullable()
    val data = varchar("data", Int.MAX_VALUE).nullable()
    val phone = varchar("phone", Int.MAX_VALUE).nullable()
}

object Students : IntIdTable() {
    val surname = varchar("surname",Int.MAX_VALUE).nullable()
    val name = varchar("name",Int.MAX_VALUE).nullable()
    val patronymic = varchar("patronymic",Int.MAX_VALUE).nullable()
    val theme = varchar("theme",Int.MAX_VALUE).nullable()
    val group = varchar("group",Int.MAX_VALUE).nullable()
    val average = varchar("average",Int.MAX_VALUE).nullable()
    val paymentPercent = varchar("payment_percent",Int.MAX_VALUE).nullable()
}