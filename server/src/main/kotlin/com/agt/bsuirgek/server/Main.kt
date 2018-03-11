package com.agt.bsuirgek.server

import com.agt.bsuirgek.server.model.Temp
import com.agt.bsuirgek.server.model.Templates
import com.agt.bsuirgek.server.parser.asDOCX
import com.agt.bsuirgek.server.parser.parse
import com.agt.bsuirgek.server.parser.toJson
import com.google.gson.Gson
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.content.PartData
import io.ktor.content.forEachPart
import io.ktor.request.isMultipart
import io.ktor.request.receiveMultipart
import io.ktor.response.respondText
import io.ktor.response.respondWrite
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
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
    speedTester {
        val path = Configuration.Linux.rootPath
        val template = "${path}temp2.docx".asDOCX()
        val doc = "${path}doc2.docx".asDOCX()
        val data = template.parse(doc)
        println(data.toJson())
    }
//    Configuration.Linux.startServer { config ->
        //        config.init(args)
        //        config.connectDb()
        //        server(config)
        //    }

        }

inline fun <T: Any>speedTester(body: () -> T): T {
    lateinit var some: T
    println(measureTimeMillis { some = body() })
    return some
}

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

    service.upload(desc, type, body).enqueue(object : Callback<ResponseBody> {
        override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
            println("FAILURE")
        }

        override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
            println("SUCCESS")
            val s = response?.body()?.string() ?: ""
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