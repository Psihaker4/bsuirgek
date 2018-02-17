package com.agt.bsuirgek.server

import com.agt.bsuirgek.server.parser.obj.ListObject
import com.agt.bsuirgek.server.parser.obj.ParseObject
import com.agt.bsuirgek.server.parser.parser.ListParser
import com.agt.bsuirgek.server.utils.*
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.apache.poi.xwpf.usermodel.XWPFDocument
import java.io.FileInputStream
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
//    local { testServer() }

//    test()

    parserTest(0)
}

fun test() {

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
    val parsers = text.createParsers(0)

    val parser = parsers[0].second as ListParser

    val test = "FFF NNN PP - aslkjdaslk djlkas ldasjlkd lasjlkd"
//    val obj = parser.parse(test)
}


fun parserTest(index: Int ) : List<ParseObject> {

    val templatePath = FileInputStream("D:/temp$index.docx")
    val template = XWPFDocument(templatePath)
    val parsers = template.createParsers()
    var shift = 0

    val documentPath = FileInputStream("D:/doc$index.docx")
    val document = XWPFDocument(documentPath)
    val documentElements = document.bodyElements

    val result = parsers.map {
        val (start, parser) = it
        val elements = documentElements.drop(start + shift)
        val result = parser.parse(elements)
        if (result is ListObject) shift += result.objects.size - 1
        result
    }
    println(result)
    return result
}

fun local(body: Application.()->Unit) = start("0.0.0.0", body)
fun online(body: Application.()->Unit) = start("deadmedia.ru", body)
fun start(server: String, body: Application.()->Unit) = embeddedServer(Netty, 8080,server,module = body).start(true)

fun Application.testServer() {
    routing {
        get("/trimple/{index}") {
            println("REQ")
            val index = try {
                call.parameters["index"]?.toInt() ?: 0
            } catch (e: Exception) {
                0
            }
            val time = measureTimeMillis {
                val result = parserTest(index)
                call.respondText(result.toString(), ContentType.Text.Plain)
            }
            println(time)
        }
    }
}