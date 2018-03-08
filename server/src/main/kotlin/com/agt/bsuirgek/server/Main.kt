package com.agt.bsuirgek.server

import com.agt.bsuirgek.server.model.Student
import com.agt.bsuirgek.server.model.Teacher
import com.agt.bsuirgek.server.model.Temp
import com.agt.bsuirgek.server.model.Templates
import com.agt.bsuirgek.server.parser.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.content.PartData
import io.ktor.content.forEachPart
import io.ktor.content.readAllParts
import io.ktor.request.isMultipart
import io.ktor.request.receiveMultipart
import io.ktor.response.respondText
import io.ktor.response.respondWrite
import io.ktor.routing.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {

//    val template = "D:/temp1.docx".asDOCX()
//    val doc = "D:/doc1.docx".asDOCX()
//    val data = template.parse(doc)
//
//    println(data)

//    val d = data.toJson()
//
//
//    val array = JsonParser().parse(d).asJsonArray
//    array.forEach {
//        val obj = it.asJsonObject
//        val type = obj["type"].asString
//        val map = Gson().fromJson<Map<String, String>>(obj["params"], object : TypeToken<Map<String, String>>() {}.type)
//        when (type) {
//            "Teacher" -> println(Teacher(map))
//            "Student" -> println(Student(map))
//        }
//    }

    Configuration.Local.startServer { config ->
        config.init(args)
        config.connectDb()
        server(config)
    }

}

inline fun speedTester(body: () -> Unit) = println(measureTimeMillis(body))

//fun parseTextXLSX(root: String, index: Int) = "${root}doc$index.xlsx".openAndParseXLSX("${root}temp$index.xlsx")
//fun parseTextDOCX(root: String, index: Int) = "${root}doc$index.docx".openAndParseDOCX("${root}temp$index.docx")

fun Application.server(config: Configuration) {
    routing {
        get("trimple/test") { test() }
        get("trimple/testTemplates") {
            call.respondText("[{\"name\":\"temp0\",\"date\":\"23.12.2016\"},{\"name\":\"temp1\",\"date\":\"12.02.2018\"}]")
        }
        get("trimple/templates") {
            try {
                val list = transaction {
                    logger.addLogger(StdOutSqlLogger)
                    Templates.selectAll().map {
                        Templates.run {
                            Temp(it[name], it[date] ?: "no date")
                        }
                    }
                }
                call.respondText(Gson().toJson(list))
            } catch (e: Exception) {
                println(e)
            }
        }
        post("trimple/uploadDocument") {
            if (!call.request.isMultipart()) return@post
            call.respondWrite {
                val data = call.receiveMultipart()

                var docFile: File? = null
                var temp: String? = null
                var type: String? = null

                data.forEachPart {
                    when (it) {
                        is PartData.FormItem -> {
                            if (it.partName == "type") type = it.value
                            else temp = it.value
                            println(it.value)
                        }
                        is PartData.FileItem -> {
                            println(it.originalFileName)
                            val file = File("${config.rootPath}${it.originalFileName}")
                            it.streamProvider().use { file.outputStream().use { file -> it.copyTo(file) } }
                            docFile = file
                        }
                    }
                    it.dispose()
                }

                val template = "${config.rootPath}$temp.$type".asDOCX()
                val doc = docFile?.asDOCX()

                if (doc == null) {
                    write("Failes OPen File")
                    return@respondWrite
                }
                val result = template.parse(doc)
                write(result.toJson())
            }
        }
    }
}

fun test() {

    val file = File("D:/doc1.docx")
    val reqFile = RequestBody.create(MediaType.parse(""), file)
    val body = MultipartBody.Part.createFormData("doc", file.name, reqFile)
    val desc = RequestBody.create(okhttp3.MultipartBody.FORM, "temp1")
    val type = RequestBody.create(okhttp3.MultipartBody.FORM, "docx")

    val int = System.currentTimeMillis()

    service.upload(desc,type, body).enqueue(object : Callback<ResponseBody> {
        override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
            println("FAILURE")
        }

        override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
            println("SUCCESS")
            val s = response?.body()?.string()?:""
            println(s)
            println(System.currentTimeMillis() - int)
        }

    })



    service.getTemplates().enqueue(object : Callback<ResponseBody> {
        override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
            println("FAIL")
        }

        override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
            println("SUCCESS")
            println(response?.body()?.string())
            println(System.currentTimeMillis() - int)
        }

    })

}