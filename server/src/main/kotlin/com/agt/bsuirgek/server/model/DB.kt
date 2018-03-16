package com.agt.bsuirgek.server.model

import org.jetbrains.exposed.dao.IntIdTable

abstract class TableWithId(name: String, archive: Boolean = false) : IntIdTable("$name${if(archive) "_archive" else ""}")

sealed class Templates(archive:Boolean = false) : TableWithId("templates", archive) {
    object Main : Templates()
    object Archive : Templates(true)

    val name = text("name")
    val loadDate = long("load_date")
    val input = bool("input")
    val output = bool("output")
}

sealed class Documents(archive:Boolean = false) : TableWithId("documents", archive) {
    object Main : Documents()
    object Archive : Documents(true)

    val name = text("name")
    val type = text("type")
    val loadDate = long("load_date")
}

sealed class Requests(archive:Boolean = false) : TableWithId("requests", archive) {
    object Main : Requests()
    object Archive : Requests(true)

    val name = text("name")
    val code = text("type")
}

sealed class Faculties(archive:Boolean = false) : TableWithId("faculties", archive) {
    object Main : Faculties()
    object Archive : Faculties(true)

    val name = text("name")
}

sealed class Specialties(archive:Boolean = false) : TableWithId("specialties", archive) {
    object Main : Specialties()
    object Archive : Specialties(true)

    val name = text("name")
    val index = integer("index")

    val facultyId = reference("faculty_id", if (archive) Faculties.Archive else Faculties.Main)
}

sealed class Pulpits(archive:Boolean = false) : TableWithId("pulpits", archive) {
    object Main : Pulpits()
    object Archive : Pulpits(true)

    val name = text("name")

    val facultyId = reference("faculty_id", if (archive) Faculties.Archive else Faculties.Main)
}

sealed class Groups(archive:Boolean = false) : TableWithId("groups", archive) {
    object Main : Groups()
    object Archive : Groups(true)

    val number = integer("number")

    val facultyId = reference("faculty_id", if (archive) Faculties.Archive else Faculties.Main)
    val specialtyId = reference("specialty_id", if (archive) Specialties.Archive else Specialties.Main)
    val pulpitId = reference("pulpit_id", if (archive) Pulpits.Archive else Pulpits.Main)
}

sealed class Teachers(archive:Boolean = false) : TableWithId("groups", archive) {
    object Main : Teachers()
    object Archive : Teachers(true)

    val surname = text("surname")
    val name = text("type").nullable()
    val patronymic = text("patronymic").nullable()
    val phoneNumber = text("phone_number")
    val external = bool("external")
    val post = text("post")

    val facultyId = reference("faculty_id", if (archive) Faculties.Archive else Faculties.Main).nullable()
    val pulpitId = reference("pulpit_id", if (archive) Pulpits.Archive else Pulpits.Main).nullable()
}

sealed class Students(archive:Boolean = false) : TableWithId("groups", archive) {
    object Main : Students()
    object Archive : Students(true)

    val surname = text("surname")
    val name = text("type").nullable()
    val patronymic = text("patronymic").nullable()
    val theme = text("theme").nullable()
    val averageScore = decimal("average_score", 2, 2).nullable()
    val paymentPercent = integer("payment_percent").nullable()
    val recordBookId = text("record_book_id")
    val studentId = text("student_id")

    val groupId = reference("group_id", if (archive) Groups.Archive else Groups.Main).nullable()

    val leaderId = reference("leader_id", if (archive) Teachers.Archive else Teachers.Main).nullable()
    val consultantId = reference("consultant_id", if (archive) Teachers.Archive else Teachers.Main).nullable()
    val reviewerId = reference("reviewer_id", if (archive) Teachers.Archive else Teachers.Main).nullable()
    val economyConsultantId =
        reference("economy_consultant_id", if (archive) Teachers.Archive else Teachers.Main).nullable()

}