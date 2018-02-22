package com.agt.bsuirgek.server.model

class Teacher(id: String, linkId: List<String>) : ObjectWithParams(id, linkId) {

    val surname by ParseMap()
    val name by ParseMap()
    val patronymic by ParseMap()
    val data by ParseMap()
    val phone by ParseMap()

    val fullName by lazy { "$surname $name $patronymic" }
    val nameInitials by lazy { "$surname ${name[0]}. ${patronymic[0]}." }

    override fun toString(): String {
        return super.toString() + "Teacher($surname,$name,$patronymic,$data,$phone)"
    }

}