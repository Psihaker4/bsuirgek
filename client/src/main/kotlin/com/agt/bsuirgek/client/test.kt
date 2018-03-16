package com.agt.bsuirgek.client

import com.google.gson.JsonParser
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.MultipartBody.Part.createFormData
import okhttp3.RequestBody
import okhttp3.RequestBody.create
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import java.io.File

val connectionService = Retrofit.Builder()
    .baseUrl("http://localhost:8080/trimple/")
    .build()
    .create(ConnectionService::class.java)

interface ConnectionService {

    @Multipart
    @POST("uploadDocument")
    fun upload(@Part("template") body: RequestBody,
               @Part("type") type: RequestBody,
               @Part file: MultipartBody.Part): Call<ResponseBody>

    @GET("testTemplates")
    fun getTemplates(): Call<ResponseBody>
}

fun test() {

    val file = File("/home/kotone/Documents/doc2.docx")
    val sendTime = System.currentTimeMillis()

    connectionService.upload(
        create(MultipartBody.FORM, "temp2"),
        create(MultipartBody.FORM, "docx"),
        createFormData("doc", file.name, create(MediaType.parse(""), file))
    ).enqueue(object : Callback<ResponseBody> {
        override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
            println("Error in ${System.currentTimeMillis() - sendTime}")
        }

        override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
            val result = response?.body()?.string() ?: ""
            println("JSON: $result in ${System.currentTimeMillis() - sendTime}")

            val parser = JsonParser()
            val array = parser.parse(result).asJsonArray

            array.forEach {

            }


        }

    })

}