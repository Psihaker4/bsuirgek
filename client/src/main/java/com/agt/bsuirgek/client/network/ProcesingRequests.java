package com.agt.bsuirgek.client.network;

import com.agt.bsuirgek.client.Object.Student;
import com.agt.bsuirgek.client.Object.Teacher;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.*;
import java.util.ArrayList;
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

    public void getData(File file){
        ArrayList<Teacher> listTeacher = new ArrayList<Teacher>();
        ArrayList<Student> listStudent = new ArrayList<Student>();

        Gson gson = new Gson();

        String json = AppConfig.TEST_JSON;
            JsonParser parser = new JsonParser();
            JsonArray array = parser.parse(json).getAsJsonArray();

            for (JsonElement jsonElement : array) {
                JsonObject obj = jsonElement.getAsJsonObject();

                Map<String, String> map = gson.fromJson(obj.get("params"), new TypeToken<Map<String, String>>() {
                }.getType());
                String type = obj.get("type").getAsString();

                switch (type) {
                    case "Teacher": {
                        listTeacher.add(new Teacher(map));
                        break;
                    }
                    case "Student": {
                        listStudent.add(new Student(map));
                        break;
                    }
                }

            }
    }
}
