package com.agt.bsuirgek.client.network;

import com.agt.bsuirgek.client.Object.TypeTemplate;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.ArrayList;

public interface Queries {

    @Multipart
    @POST("trimple/uploadDocument")
    Call<ResponseBody> upload(@Part() MultipartBody.Part image, @Part("type") String type, @Part("temp") String temp);

    @GET("trimple/testTemplates")
    Call<ArrayList<TypeTemplate>> getTemplate();

}


