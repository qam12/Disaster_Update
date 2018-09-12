package com.example.qamber.disaster_update.Services;

import com.example.qamber.disaster_update.ModelClass.Hero;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by qamber.haider on 6/19/2018.
 */

public class ApiClient {
//    public static final String BASE_URL = "http://www.shaoniiuc.com/";
    //public static final String BASE_URL = "https://simplifiedcoding.net/demos/";
    public static final String BASE_URL ="http://api.plos.org/";
    private static Retrofit retrofit = null;



    public static Retrofit getClient() {
        if (retrofit==null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();


            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
