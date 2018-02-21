package com.agt.bsuirgek.server.model

class Student(id: Int = -1, linkId: List<Int> = emptyList()) : ObjectWithParams(id, linkId) {

    val surname by ParseMap()
    val name by ParseMap()
    val patronymic by ParseMap()
    val theme by ParseMap()
    val group by ParseMap()
    val average by ParseMap()
    val paymentPercent by ParseMap()

    val fullName by lazy { "$surname $name $patronymic" }
    val nameInitials by lazy { "$surname ${name[0]}. ${patronymic[0]}." }

    override fun toString(): String {
        return super.toString() + "Student($surname,$name,$patronymic,$group,$paymentPercent,$average,$theme)"
    }

}