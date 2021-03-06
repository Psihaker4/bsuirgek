package com.agt.bsuirgek.client.network;

import com.agt.bsuirgek.client.Object.Student;
import com.agt.bsuirgek.client.Object.Teacher;
import com.agt.bsuirgek.client.Object.TempMemory;
import com.agt.bsuirgek.client.Object.TypeTemplate;
import com.agt.bsuirgek.client.model.Temp;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

//import com.agt.bsuirgek.connectionService.model.Student;
//import com.agt.bsuirgek.connectionService.model.Teacher;

public class ProcesingRequests {

    public static void uploadFile(Queries req, File file, String temp) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part body =
                MultipartBody.Part.createFormData("picture", file.getName(), requestFile);

        Call<ResponseBody> call = req.upload(body, "doc", temp);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.out.println("socsesful");
                Temp temp = new Temp("asd","ad");

                try {
                    ResponseBody responseBody = response.body();
                    if(responseBody!=null)
                        System.out.println(responseBody.string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("error");
            }
        });
    }

    public void getData(){
        /*ArrayList<Teacher> listTeacher = new ArrayList<Teacher>();
        ArrayList<Student> listStudent = new ArrayList<Student>();*/

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
                        TempMemory.listTempTeacher.add(new Teacher(map));
                        break;
                    }
                    case "Student": {
                        TempMemory.listTempStudent.add(new Student(map));
                        break;
                    }
                }

            }
    }

    public void getTemplate(Queries req){
        Call<ArrayList<TypeTemplate>> call = req.getTemplate();

        call.enqueue(new Callback<ArrayList<TypeTemplate>>() {
            @Override
            public void onResponse(Call<ArrayList<TypeTemplate>> call, Response<ArrayList<TypeTemplate>> response) {
                ArrayList<TypeTemplate> list = response.body();

                for(int i = 0; i < list.size(); i++){
                    TempMemory.TEMPLATE.add(list.get(i).getName());
                }

                System.out.println(list.get(list.size() - 1).getName());
            }

            @Override
            public void onFailure(Call<ArrayList<TypeTemplate>> call, Throwable t) {

            }
        });
    }
}
