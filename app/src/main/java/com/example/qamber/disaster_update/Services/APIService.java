package com.example.qamber.disaster_update.Services;

import com.example.qamber.disaster_update.ModelClass.Hero;
import com.example.qamber.disaster_update.ModelClass.MSG;

import java.util.List;

import javax.xml.transform.Result;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by qamber.haider on 6/19/2018.
 */

public interface APIService {

    @FormUrlEncoded
    @POST("login/views/signup.php")
    Call<MSG> userSignUp(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password);


    @FormUrlEncoded
    @POST("login/views/login.php")
    Call<MSG> userLogIn(
            @Field("email") String email,
            @Field("password") String password);

//    @GET("marvel")
//    Call<List<Hero>> getHeroes();

    @GET("search?q=title:DNA")
    Call<List<Result>> getHeroes();

}
