package com.agt.bsuirgek.server

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

val service = Retrofit.Builder()
        .baseUrl("http://${Configuration.Local.url}:8080/trimple/")
        .build()
        .create(TestService::class.java)

interface TestService {

    @Multipart
    @POST("uploadDocument")
    fun upload(@Part("template") body: RequestBody,
               @Part("type") type: RequestBody,
               @Part file: MultipartBody.Part): Call<ResponseBody>

    @GET("testTemplates")
    fun getTemplates(): Call<ResponseBody>
}