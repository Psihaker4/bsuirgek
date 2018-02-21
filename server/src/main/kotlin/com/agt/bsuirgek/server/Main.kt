package com.agt.bsuirgek.server

import com.agt.bsuirgek.server.dao.Students
import com.agt.bsuirgek.server.dao.Teachers
import com.agt.bsuirgek.server.dsl.*
import com.agt.bsuirgek.server.model.*
import com.agt.bsuirgek.server.parser.ExcelTableParser
import com.agt.bsuirgek.server.parser.ListParser
import com.agt.bsuirgek.server.util.*
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.content.PartData
import io.ktor.http.ContentType
import io.ktor.request.isMultipart
import io.ktor.request.receiveMultipart
import io.ktor.response.respondText
import io.ktor.response.respondWrite
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import javafx.geometry.Pos
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.File
import java.io.FileInputStream
import java.io.OutputStream
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
//    local { testServer() }

//    test()

    val data = parseTest(2)
    println(data)
//    structureData(data)
//    parseTextXLSX(0)

//    dbTest()

//    local { testServer() }

}

fun structureData(objects: List<ParseObject>) {
    val newList = mutableListOf<ParseObject>()
    objects.forEach {
//        println("${it::class.simpleName}   ")
        when (it) {
            is ListObject -> {
//                correctList(it)
                newList.add(it)
            }
            is ObjectWithParams -> {
                newList.add(it)
            }
            is Error -> {
                println(it)
            }
        }
    }
//    println(newList)
}

fun correctList(list: ListObject){
    val grouped = list.objects.groupBy { it::class }
    grouped.keys.forEach { clazz->
        println(clazz)
        grouped[clazz]?.forEach {
            println(it)
        }
    }
}

fun dbTest() {
    Database.connect("jdbc:mysql://localhost:3306/bsuirgek?useSSL=false","com.mysql.jdbc.Driver","serverUser","avantgarde")

    transaction {
        logger.addLogger(StdOutSqlLogger)
        create(Teachers, Students)

//        val st = Students.insert {
//            it[name] = "geel"
//        }

    }
}

fun test() {

    pattern(RootParseElement.List) {
        s("SOME")
        teacher {
            "surname"()
            s()
            "asd"()
        }
        s()
        student {
            "data"()
        }
        custom {
            "some"()
            s()
            "end"()
        }
    }

    val xml = xml("List") {
        "Hello"()
        "Teacher" {
            "surname"()
            "s"("t" to " ")
            "name"()
            "s"("t" to " ")
            "patronymic"()
            "s"("t" to " - ")
            "data"()
        }
    }

    val xmlString = xml.format()

    println(xmlString)

    val text = "Кафедра систем управления рекомендует для проведения защи" +
            "ты дипломных проектов и раб\${$xmlString}от в июне 2018 года следующий сос" +
            "тав ГЭК №1 по специальности 1-53 01 07” Информационные технологии и управление в технических системах” для" +
            " студентов ФИТиУ и ФИНО (отделение заочного обучения). "
    val parsers = text.createIndexedParsers(0)

    val parser = parsers[0].second as ListParser

    val test = "FFF NNN PP - aslkjdaslk djlkas ldasjlkd lasjlkd"
//    val obj = parser.parse(test)
}

fun parseTextXLSX(index: Int) : List<List<ParseObject>> {
    val temp = "temp$index".asXLSX()
    val doc = "doc$index".asXLSX()

    val sheetsWithParsers = temp.positionedParsers()

    return sheetsWithParsers.map { sheet ->
        var shift = Position(0, 0)
        sheet.map {
            val (pos, parser) = it
            print(pos)
            parser.run(doc.getSheetAt(0), shift + pos).apply { if (this is ListObject) shift += Position(objects.size, 0) }
        }
    }
}

fun parseTest(index: Int ) : List<ParseObject> {

    val template = "temp$index".asDOCX()
    val document = "doc$index".asDOCX()

    val parsers = template.indexedParsers()
    val elements = document.bodyElements

    var shift = 0
    val result = parsers.map {
        val (start, parser) = it
        parser.run(elements, start + shift).apply { if (this is ListObject) shift += objects.size - 1 }
    }
    return result
}

fun local(body: Application.()->Unit) = start("0.0.0.0", body)
fun online(body: Application.()->Unit) = start("deadmedia.ru", body)
fun start(server: String, body: Application.()->Unit) = embeddedServer(Netty, 8080,server,module = body).start(true)

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
                val result = parseTest(index)
                call.respondText(result.toString(), ContentType.Text.Plain)
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

val rootPath = "D:/"
fun String.asDOCX() = XWPFDocument(FileInputStream(rootPath + this + ".docx"))
fun String.asXLSX() = XSSFWorkbook(FileInputStream(rootPath + this + ".xlsx"))