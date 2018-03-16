package com.agt.bsuirgek.server

import com.agt.bsuirgek.server.model.Student
import com.agt.bsuirgek.server.model.Teacher
import com.agt.bsuirgek.server.parser.*
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


//        val template = "${rootPath}temp1.docx".asDOCX()
//        val doc = "${rootPath}doc1.docx".asDOCX()
//        val data = template.parse(doc)
//        val jsonData = data.toJson()

//        println(data.joinToString("\n"))

        val temp = """[{"id":"1ruk","type":"Teacher","params":{"n":"1","surname":"Гурский","name":"Владимир","patronymic":"Евгеньевич ","data":"к. т. н. , доцент БГУ","phone":"+375297624881"},"links":{"1List":"List(student)"}},{"id":"1List0Student","type":"Student","params":{"surname":"Авраменко","name":"С.","patronymic":"А."},"links":{"1List0ruk":"reviewer_id"}},{"id":"1List1Student","type":"Student","params":{"surname":"Антропов","name":"В.","patronymic":"А."},"links":{"1List1ruk":"reviewer_id"}},{"id":"1List2Student","type":"Student","params":{"surname":"Бойко","name":"М.","patronymic":"В."},"links":{"1List2ruk":"reviewer_id"}},{"id":"1List3Student","type":"Student","params":{"surname":"Васильев","name":"А.","patronymic":"А."},"links":{"1List3ruk":"reviewer_id"}},{"id":"1List4Student","type":"Student","params":{"surname":"Вашко","name":"Ю.","patronymic":"А."},"links":{"1List4ruk":"reviewer_id"}},{"id":"1List5Student","type":"Student","params":{"surname":"Велесюк","name":"М.","patronymic":"Л."},"links":{"1List5ruk":"reviewer_id"}},{"id":"1List6Student","type":"Student","params":{"surname":"Верещаго","name":"В.","patronymic":"Ю."},"links":{"1List6ruk":"reviewer_id"}},{"id":"1List7Student","type":"Student","params":{"surname":"Ворушенко","name":"А.","patronymic":"В."},"links":{"1List7ruk":"reviewer_id"}},{"id":"1List8Student","type":"Student","params":{"surname":"Гайшун","name":"А.","patronymic":"В."},"links":{"1List8ruk":"reviewer_id"}},{"id":"1List9Student","type":"Student","params":{"surname":"Грабок","name":"С.","patronymic":"С."},"links":{"1List9ruk":"reviewer_id"}},{"id":"1List","type":"List","links":{"1List0Student":"0","1List1Student":"1","1List2Student":"2","1List3Student":"3","1List4Student":"4","1List5Student":"5","1List6Student":"6","1List7Student":"7","1List8Student":"8","1List9Student":"9"}},{"id":"2ruk","type":"Teacher","params":{"n":"2","surname":"Коваленко","name":"Валентин","patronymic":"Максимович","data":"доцент кафедры ТОЭ БГУИР, к. т. н. , доцент","phone":"м. т. : 80295007385"},"links":{"2List":"List(student)"}},{"id":"2List0Student","type":"Student","params":{"surname":"Денисенко","name":"А.","patronymic":"А."},"links":{"2List0ruk":"reviewer_id"}},{"id":"2List1Student","type":"Student","params":{"surname":"Денисов","name":"Е.","patronymic":"М."},"links":{"2List1ruk":"reviewer_id"}},{"id":"2List2Student","type":"Student","params":{"surname":"Дешко","name":"В.","patronymic":"Ю."},"links":{"2List2ruk":"reviewer_id"}},{"id":"2List3Student","type":"Student","params":{"surname":"Дещеня","name":"П.","patronymic":"А."},"links":{"2List3ruk":"reviewer_id"}},{"id":"2List4Student","type":"Student","params":{"surname":"Доманцевич","name":"А.","patronymic":"В."},"links":{"2List4ruk":"reviewer_id"}},{"id":"2List5Student","type":"Student","params":{"surname":"Дубровский","name":"П.","patronymic":"С."},"links":{"2List5ruk":"reviewer_id"}},{"id":"2List6Student","type":"Student","params":{"surname":"Дулик","name":"М.","patronymic":"В."},"links":{"2List6ruk":"reviewer_id"}},{"id":"2List7Student","type":"Student","params":{"surname":"Елисеев","name":"И.","patronymic":"С."},"links":{"2List7ruk":"reviewer_id"}},{"id":"2List","type":"List","links":{"2List0Student":"0","2List1Student":"1","2List2Student":"2","2List3Student":"3","2List4Student":"4","2List5Student":"5","2List6Student":"6","2List7Student":"7"}},{"id":"3ruk","type":"Teacher","params":{"n":"3","surname":"Курулёв","name":"Александр","patronymic":"Петрович","data":"профессор кафедры ТОЭ БГУИР, к. т. н. , доцент","phone":"м. т. : 80292617477"},"links":{"3List":"List(student)"}},{"id":"3List0Student","type":"Student","params":{"surname":"Жолнеркевич","name":"А.","patronymic":"И."},"links":{"3List0ruk":"reviewer_id"}},{"id":"3List1Student","type":"Student","params":{"surname":"Зуйкевич","name":"И.","patronymic":"А."},"links":{"3List1ruk":"reviewer_id"}},{"id":"3List2Student","type":"Student","params":{"surname":"Иванов","name":"А.","patronymic":"А."},"links":{"3List2ruk":"reviewer_id"}},{"id":"3List3Student","type":"Student","params":{"surname":"Иванов","name":"С.","patronymic":"О."},"links":{"3List3ruk":"reviewer_id"}},{"id":"3List4Student","type":"Student","params":{"surname":"Кацук","name":"Д.","patronymic":"Т."},"links":{"3List4ruk":"reviewer_id"}},{"id":"3List5Student","type":"Student","params":{"surname":"Качан","name":"В.","patronymic":"И."},"links":{"3List5ruk":"reviewer_id"}},{"id":"3List6Student","type":"Student","params":{"surname":"Качура","name":"Н.","patronymic":"В."},"links":{"3List6ruk":"reviewer_id"}},{"id":"3List7Student","type":"Student","params":{"surname":"Киреева","name":"Н.","patronymic":"А."},"links":{"3List7ruk":"reviewer_id"}},{"id":"3List","type":"List","links":{"3List0Student":"0","3List1Student":"1","3List2Student":"2","3List3Student":"3","3List4Student":"4","3List5Student":"5","3List6Student":"6","3List7Student":"7"}},{"id":"4ruk","type":"Teacher","params":{"n":"4","surname":"Свито","name":"Игорь","patronymic":"Леонтьевич","data":"к. т. н. , доцент кафедры ТОЭ БГУИР, ","phone":"р. т. : 293 86 08"},"links":{"4List":"List(student)"}},{"id":"4List0Student","type":"Student","params":{"surname":"Кирилович","name":"М.","patronymic":"Д."},"links":{"4List0ruk":"reviewer_id"}},{"id":"4List1Student","type":"Student","params":{"surname":"Ковалевский","name":"А.","patronymic":"В."},"links":{"4List1ruk":"reviewer_id"}},{"id":"4List2Student","type":"Student","params":{"surname":"Костив","name":"А.","patronymic":"С."},"links":{"4List2ruk":"reviewer_id"}},{"id":"4List3Student","type":"Student","params":{"surname":"Костюкова","name":"А.","patronymic":"Р."},"links":{"4List3ruk":"reviewer_id"}},{"id":"4List4Student","type":"Student","params":{"surname":"Красов","name":"В.","patronymic":"В."},"links":{"4List4ruk":"reviewer_id"}},{"id":"4List5Student","type":"Student","params":{"surname":"Кришнева","name":"Н.","patronymic":"В."},"links":{"4List5ruk":"reviewer_id"}},{"id":"4List6Student","type":"Student","params":{"surname":"Кудрявцев","name":"А.","patronymic":"А."},"links":{"4List6ruk":"reviewer_id"}},{"id":"4List","type":"List","links":{"4List0Student":"0","4List1Student":"1","4List2Student":"2","4List3Student":"3","4List4Student":"4","4List5Student":"5","4List6Student":"6"}},{"id":"5ruk","type":"Teacher","params":{"n":"5","surname":"Сергеев","name":"Владимир","patronymic":"Игнатьевич","data":"доцент кафедры информационных технологий БГУ, к. т. н.","phone":"р. т. : 209 59 28"},"links":{"5List":"List(student)"}},{"id":"5List0Student","type":"Student","params":{"surname":"Кузнечик","name":"А.","patronymic":"В."},"links":{"5List0ruk":"reviewer_id"}},{"id":"5List1Student","type":"Student","params":{"surname":"Лапко","name":"А.","patronymic":"В."},"links":{"5List1ruk":"reviewer_id"}},{"id":"5List2Student","type":"Student","params":{"surname":"Лебедик","name":"К.","patronymic":"М."},"links":{"5List2ruk":"reviewer_id"}},{"id":"5List3Student","type":"Student","params":{"surname":"Левдиков","name":"М.","patronymic":"И."},"links":{"5List3ruk":"reviewer_id"}},{"id":"5List4Student","type":"Student","params":{"surname":"Липский","name":"О.","patronymic":"В."},"links":{"5List4ruk":"reviewer_id"}},{"id":"5List5Student","type":"Student","params":{"surname":"Манько","name":"Л.","patronymic":"А."},"links":{"5List5ruk":"reviewer_id"}},{"id":"5List6Student","type":"Student","params":{"surname":"Масляков","name":"А.","patronymic":"С."},"links":{"5List6ruk":"reviewer_id"}},{"id":"5List","type":"List","links":{"5List0Student":"0","5List1Student":"1","5List2Student":"2","5List3Student":"3","5List4Student":"4","5List5Student":"5","5List6Student":"6"}},{"id":"6ruk","type":"Teacher","params":{"n":"6","surname":"Царик","name":"Сергей","patronymic":"Всеволодович","data":"доцент кафедры информационных технологий БГУ, к. т. н. , доцент","phone":"Vel. : 80296550009"},"links":{"6List":"List(student)"}},{"id":"6List0Student","type":"Student","params":{"surname":"Меерович","name":"П.","patronymic":"А."},"links":{"6List0ruk":"reviewer_id"}},{"id":"6List1Student","type":"Student","params":{"surname":"Микалуцкий","name":"К.","patronymic":"А."},"links":{"6List1ruk":"reviewer_id"}},{"id":"6List2Student","type":"Student","params":{"surname":"Михалап","name":"О.","patronymic":"В."},"links":{"6List2ruk":"reviewer_id"}},{"id":"6List3Student","type":"Student","params":{"surname":"Михалёв","name":"П.","patronymic":"В."},"links":{"6List3ruk":"reviewer_id"}},{"id":"6List4Student","type":"Student","params":{"surname":"Нагорный","name":"И.","patronymic":"С."},"links":{"6List4ruk":"reviewer_id"}},{"id":"6List5Student","type":"Student","params":{"surname":"Новицкий","name":"И.","patronymic":"О."},"links":{"6List5ruk":"reviewer_id"}},{"id":"6List6Student","type":"Student","params":{"surname":"Орлов","name":"А.","patronymic":"С."},"links":{"6List6ruk":"reviewer_id"}},{"id":"6List","type":"List","links":{"6List0Student":"0","6List1Student":"1","6List2Student":"2","6List3Student":"3","6List4Student":"4","6List5Student":"5","6List6Student":"6"}},{"id":"7ruk","type":"Teacher","params":{"n":"7","surname":"Белодед","name":"Николай","patronymic":"Иванович","data":"к. т. н. , доцент Академии управления при Президенте РБ,","phone":"+375296702710"},"links":{"7List":"List(student)"}},{"id":"7List0Student","type":"Student","params":{"surname":"Петроченко","name":"М.","patronymic":"В."},"links":{"7List0ruk":"reviewer_id"}},{"id":"7List1Student","type":"Student","params":{"surname":"Попов","name":"А.","patronymic":"А."},"links":{"7List1ruk":"reviewer_id"}},{"id":"7List2Student","type":"Student","params":{"surname":"Проказов","name":"Д.","patronymic":"Р."},"links":{"7List2ruk":"reviewer_id"}},{"id":"7List3Student","type":"Student","params":{"surname":"Пухальский","name":"В.","patronymic":"Э."},"links":{"7List3ruk":"reviewer_id"}},{"id":"7List4Student","type":"Student","params":{"surname":"Рачок","name":"В.","patronymic":"В."},"links":{"7List4ruk":"reviewer_id"}},{"id":"7List5Student","type":"Student","params":{"surname":"Романчук","name":"Д.","patronymic":"В."},"links":{"7List5ruk":"reviewer_id"}},{"id":"7List6Student","type":"Student","params":{"surname":"Севрюк","name":"В.","patronymic":"Э."},"links":{"7List6ruk":"reviewer_id"}},{"id":"7List","type":"List","links":{"7List0Student":"0","7List1Student":"1","7List2Student":"2","7List3Student":"3","7List4Student":"4","7List5Student":"5","7List6Student":"6"}},{"id":"8ruk","type":"Teacher","params":{"n":"8","surname":"Щербович","name":"Жанна","patronymic":"Ивановна","data":"старший преподаватель Академии управления при Президенте РБ,","phone":"+375295595658"},"links":{"8List":"List(student)"}},{"id":"8List0Student","type":"Student","params":{"surname":"Сезень","name":"С.","patronymic":"А."},"links":{"8List0ruk":"reviewer_id"}},{"id":"8List1Student","type":"Student","params":{"surname":"Сергута","name":"Д.","patronymic":"Г."},"links":{"8List1ruk":"reviewer_id"}},{"id":"8List2Student","type":"Student","params":{"surname":"Слугин","name":"С.","patronymic":"А."},"links":{"8List2ruk":"reviewer_id"}},{"id":"8List3Student","type":"Student","params":{"surname":"Стасевич","name":"К.","patronymic":"Ю."},"links":{"8List3ruk":"reviewer_id"}},{"id":"8List4Student","type":"Student","params":{"surname":"Субоч","name":"Д.","patronymic":"В."},"links":{"8List4ruk":"reviewer_id"}},{"id":"8List5Student","type":"Student","params":{"surname":"Ткаченко","name":"Ю.","patronymic":"А."},"links":{"8List5ruk":"reviewer_id"}},{"id":"8List6Student","type":"Student","params":{"surname":"Трухан","name":"А.","patronymic":"В."},"links":{"8List6ruk":"reviewer_id"}},{"id":"8List","type":"List","links":{"8List0Student":"0","8List1Student":"1","8List2Student":"2","8List3Student":"3","8List4Student":"4","8List5Student":"5","8List6Student":"6"}},{"id":"9ruk","type":"Teacher","params":{"n":"9","surname":"Юрча","name":"Ирина","patronymic":"Александровна","data":"старший преподаватель Академии управления при Президенте РБ,","phone":"+375297734843"},"links":{"9List":"List(student)"}},{"id":"9List0Student","type":"Student","params":{"surname":"Харитончик","name":"А.","patronymic":"А."},"links":{"9List0ruk":"reviewer_id"}},{"id":"9List1Student","type":"Student","params":{"surname":"Ходорович","name":"Н.","patronymic":"Н."},"links":{"9List1ruk":"reviewer_id"}},{"id":"9List2Student","type":"Student","params":{"surname":"Цивинский","name":"В.","patronymic":"В."},"links":{"9List2ruk":"reviewer_id"}},{"id":"9List3Student","type":"Student","params":{"surname":"Циунчик","name":"Е.","patronymic":"В."},"links":{"9List3ruk":"reviewer_id"}},{"id":"9List4Student","type":"Student","params":{"surname":"Шикунец","name":"Е.","patronymic":"В."},"links":{"9List4ruk":"reviewer_id"}},{"id":"9List5Student","type":"Student","params":{"surname":"Кравченко","name":"Н.","patronymic":"А."},"links":{"9List5ruk":"reviewer_id"}},{"id":"9List6Student","type":"Student","params":{"surname":"Моисеенков","name":"А.","patronymic":"С."},"links":{"9List6ruk":"reviewer_id"}},{"id":"9List","type":"List","links":{"9List0Student":"0","9List1Student":"1","9List2Student":"2","9List3Student":"3","9List4Student":"4","9List5Student":"5","9List6Student":"6"}},{"id":"Table","type":"List","links":{"1ruk":"0","2ruk":"1","3ruk":"2","4ruk":"3","5ruk":"4","6ruk":"5","7ruk":"6","8ruk":"7","9ruk":"8"}}]"""
        val jsonArray = temp.asJsonArray()

        val objects = jsonArray.map()
        val heads = jsonArray.heads()

        val teachers = mutableListOf<Teacher>()
        val students = mutableListOf<Student>()

        val obj = heads[0]

        val links = obj.links
        val isList: Boolean = links.isList()

        if(isList) {
            val ids = links.keySet()
            ids.forEach {
                val obj = objects[it]?.asJsonObject ?:return@forEach
                when(obj.type) {
                    "Teacher" -> {
                        teachers += Teacher(obj.params.toMap())

                        if(obj.isLinksList()) {
                            println("LIST")
                        } else {
                            obj.links.keySet().forEach {
                                println(objects[it])
                            }
                            println()
                        }
                    }
                }
            }
        }

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