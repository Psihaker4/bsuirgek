package com.agt.bsuirgek.server

import com.agt.bsuirgek.server.parser.asDOCX
import com.agt.bsuirgek.server.parser.parse
import com.agt.bsuirgek.server.parser.toJson
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    Configuration.Linux.apply {
//        init(args)
//        connectDb()
//
//
        val template = "${rootPath}temp1.docx".asDOCX()
        val doc = "${rootPath}doc1.docx".asDOCX()
        val data = template.parse(doc)
        val jsonData = data.toJson()

//        println(data.joinToString("\n"))

//        val jsonArray = JSON_TEMP.asJsonArray()
//
//        val objects = jsonArray.groupById()
//        val heads = jsonArray.heads()
//
//        val obj = heads[0]
//
//        obj.pass(objects)
//

//        val links = obj.links
//        val isList: Boolean = links.isList()
//        val ids = links.keySet()
//        if(isList) {
//            ids.forEach {
//                val obj = objects[it] ?:return@forEach
//                println("0 --- $obj --- ${obj.links}")
//                when(obj.type) {
//                    "Teacher" -> {
//                        teachers += Teacher(obj.params.toMap())
//
//                        if(obj.isLinksList()) {
//                            println("LIST")
//                        } else {
//                            println("1 --- $obj --- ${obj.links}")
//                            obj.links.keySet().forEach {
//                                val newObj = objects[it]?:return@forEach
//                                println("2 --- $newObj --- ${newObj.links}")
//                                newObj.links.keySet().forEach {
//                                    val nnObj = objects[it]?:return@forEach
//                                    println("3 --- $nnObj --- ${nnObj.links}")
////                                    when(nnObj.type) {
////                                        "Student" -> println(nnObj)
////                                    }
//                                }
//                                println()
//
//                            }
//                            println()
//                        }
//                        obj.pass()
//
//                    }
//                }
//                println()
//            }
//        }

    }
    //    Configuration.Linux.startServer { config ->
//        config.init(args)
//        config.connectDb()
//        server(config)
//    }

}

inline fun <T: Any>speedTester(body: () -> T): T {
    lateinit var obj: T
    println(measureTimeMillis { obj = body() })
    return obj
}

//fun parseTextXLSX(root: String, index: Int) = "${root}doc$index.xlsx".openAndParseXLSX("${root}temp$index.xlsx")
//fun parseTextDOCX(root: String, index: Int) = "${root}doc$index.docx".openAndParseDOCX("${root}temp$index.docx")

fun Application.server(config: Configuration) {
    routing {
        get("trimple/test") { test() }
        get("trimple/testTemplates") {
            call.respondText("[{\"name\":\"temp0\",\"date\":\"23.12.2016\"},{\"name\":\"temp1\",\"date\":\"12.02.2018\"}]")
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