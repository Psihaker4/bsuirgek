package com.agt.bsuirgek.server.parser.obj

class Teacher : CustomObject() {

    var sur: String =""
        private set
    var name: String=""
        private set
    var patronymic: String=""
        private set
    var data: String = ""
        private set

    val fullName by lazy { "$sur $name $patronymic" }
    val nameInitials by lazy { "$sur ${name[0]}. ${patronymic[0]}." }

    override fun set(argument: String, value: String) {
        super.set(argument, value)
        when (argument) {
            "surname" -> sur = value
            "name" -> name = value
            "patronymic" -> patronymic = value
            "data" -> data = value
        }
    }

    override fun toString(): String {
        return "Teacher($sur,$name,$patronymic,$data)"
    }

}