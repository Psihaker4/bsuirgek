package model

data class Student(val firstName:String,
              val surname:String,
              val fathername:String,
              val average:Float,
              val paymentPercent:Int,
              val group:String) {
    val fullName by lazy { "$firstName $surname $fathername" }
}