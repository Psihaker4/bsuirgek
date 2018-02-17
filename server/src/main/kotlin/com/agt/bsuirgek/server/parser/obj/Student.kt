package com.agt.bsuirgek.server.parser.obj

class Student : CustomObject() {

    var sur =""
        private set
    var name=""
        private set
    var patronymic: String=""
        private set
    var theme: String = ""
        private set

    var group: String = ""
        private set

    var average: Float = 0f
        private set

    var paymentPercent: Float = 0f
        private set

    val fullName by lazy { "$sur $name $patronymic" }
    val nameInitials by lazy { "$sur ${name[0]}. ${patronymic[0]}." }

    override fun set(argument: String, value: String) {
        super.set(argument, value)
        when (argument) {
            "surname" -> sur = value
            "name" -> name = value
            "patronymic" -> patronymic = value
            "theme" -> theme = value
        }
    }

    override fun toString(): String {
        return "Student($sur,$name,$patronymic,$theme)"
    }

}