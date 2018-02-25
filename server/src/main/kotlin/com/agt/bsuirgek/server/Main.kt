package com.agt.bsuirgek.server

import com.agt.bsuirgek.server.Configuration.Local
import com.agt.bsuirgek.server.model.Student
import com.agt.bsuirgek.server.model.Students
import com.agt.bsuirgek.server.model.Teacher
import com.agt.bsuirgek.server.model.Teachers
import com.agt.bsuirgek.server.parser.ListObject
import com.agt.bsuirgek.server.parser.MapObject
import com.agt.bsuirgek.server.parser.ParseObject
import com.agt.bsuirgek.server.util.*
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.content.PartData
import io.ktor.request.isMultipart
import io.ktor.request.receiveMultipart
import io.ktor.response.respondWrite
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.File
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    speedTester { Local.test() }
}

fun Configuration.test() {
//    connectDB()
    println(parseTextDOCX(rootPath,2))
//    println(parseTextXLSX(rootPath, 0)[0])
//    val list = result[0] as ListObject
//    transaction {
//        logger.addLogger(StdOutSqlLogger)
//        create(Teachers, Students)
//        (list.objects as List<MapObject>).forEach {
//            val s = when (it.data.type) {
//                "Student" -> Student(it.params)
//                else -> throw  Throwable("SS")
//            }
//
//
//
//            Students.insert {
//                s.map.forEach loop@{ key, value ->
//                    val c = Students.columns.firstOrNull { it.name == key } ?: return@loop
//                    it[c as Column<String?>] = value
//                }
//            }

//            val ss = Students.select({Students.surname eq s.surname})
//            ss.forEach {
//                println(it[Students.name])
//            }
//
//        }
//    }
}

fun List<ParseObject>.c(): List<MapObject> {
    val s = partition { it is MapObject }
    val m = s.first as List<MapObject>
    val l = s.second as List<ListObject>
    val ss = l.flatMap { it.objects.c() }
    return m + ss
}

fun dbTest() {
    Database.connect("jdbc:mysql://localhost:3306/bsuirgek?useSSL=false","com.mysql.jdbc.Driver","serverUser","avantgarde")

    transaction {


    }
}

fun speedTester(body: ()->Unit) {
    println(measureTimeMillis(body))
}

fun parseTextXLSX(root: String, index: Int) = "${root}doc$index.xlsx".openAndParseXLSX("${root}temp$index.xlsx")
fun parseTextDOCX(root: String, index: Int) = "${root}doc$index.docx".openAndParseDOCX("${root}temp$index.docx")

fun Application.testServer() {
    routing {
        get("/trimpl") {
            println("so")
            upload()
            println("so")
        }
        get("/trimple/{index}") {
            println("REQ")
            val index = try {
                call.parameters["index"]?.toInt() ?: 0
            } catch (e: Exception) {
                0
            }
            val time = measureTimeMillis {
//                val result = parseTestDOCX(index)
//                call.respondText(result.toString(), ContentType.Text.Plain)
            }
            println(time)
        }
        post("upload"){
            val mp = call.receiveMultipart()
            println("hi man")
            call.respondWrite {
                if(!call.request.isMultipart()){
                    println("NOT MP")
                } else {
                    while(true) {
                        val part = mp.readPart()?:break
                        when(part) {
                            is PartData.FormItem -> println("Form field: ${part.partName} = ${part.value}")
                            is PartData.FileItem -> {
                                println("File field: ${part.partName} -> ${part.originalFileName} of ${part.contentType}")
                                val file = File("D:/doc123.docx")
                                part.streamProvider().use {
                                    file.outputStream().use { file ->
                                        it.copyTo(file)
                                    }
                                }
                            }
                        }
                        part.dispose()
                    }
                }
                println()
            }
        }
    }
}
