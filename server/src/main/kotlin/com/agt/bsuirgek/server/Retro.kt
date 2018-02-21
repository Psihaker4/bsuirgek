package com.agt.bsuirgek.server

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import java.io.File
import java.net.URI

object Service {
    val retrofit = Retrofit.Builder()
            .baseUrl("http://127.0.0.1:8080")
            .build()
}



interface FileUploadService {
    @Multipart
    @POST("/upload")
    fun upload(@Part("description") body: RequestBody, @Part file: MultipartBody.Part): Call<ResponseBody>
}

fun upload() {
    try {
        val file = File("D:/doc0.docx")

        val retro = Retrofit.Builder()
                .baseUrl("http://127.0.0.1:8080")
                .build()

        val service = retro.create(FileUploadService::class.java)

        val reqFile = RequestBody.create(MediaType.parse("docx"), file)
        val body = MultipartBody.Part.createFormData("file", file.name, reqFile)
        val desc = RequestBody.create(okhttp3.MultipartBody.FORM, "some desc")

        val call = service.upload(desc, body)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                println("SUCC")
            }

            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                println("fail")
            }

        })
    } catch (e: Exception) {
        println(e)
    }
    println("asda")
}