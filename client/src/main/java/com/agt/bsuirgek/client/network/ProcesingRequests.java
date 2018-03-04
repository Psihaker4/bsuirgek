package com.agt.bsuirgek.client.network;

import com.agt.bsuirgek.client.Object.Data;
import com.agt.bsuirgek.client.model.Student;
import com.agt.bsuirgek.client.model.Teacher;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.File;
import java.util.Map;

public class ProcesingRequests {

    public static void uploadFile(Queries req, File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part body =
                MultipartBody.Part.createFormData("picture", file.getName(), requestFile);

        Call<ResponseBody> call = req.upload(body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.out.println("socsesful");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("error");
            }
        });
    }

    public static void getData(Queries req, File dile){

        Gson gson = new Gson();

        String json = "some json";

        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(json).getAsJsonArray();

        array.forEach(jsonElement -> {
            JsonObject obj = jsonElement.getAsJsonObject();

            Map<String,String> map = gson.fromJson(obj.get("params"),new TypeToken<Map<String,String>>(){}.getType());
            String type = obj.get("type").getAsString();

            switch (type) {
                case "Teacher" : System.out.println(new Teacher(map));
                case "Student" : System.out.println(new Student(map));
            }

        });



    }
}
