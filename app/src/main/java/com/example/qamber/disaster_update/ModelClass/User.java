package com.example.qamber.disaster_update.ModelClass;

import com.google.gson.annotations.SerializedName;

/**
 * Created by qamber.haider on 6/19/2018.
 */

public class User {


//        @SerializedName("name")
//    private String name;
//    @SerializedName("email")
//    private String email;
//    @SerializedName("password")
//    private String password;


    private int id;
    private String name;
    private String email;
    private String password;
    private String gender;

    public User(String name, String email, String password, String gender) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }

    public User(String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    public User(String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword(){
        return password;
    }

    public String getGender() {
        return gender;
    }
}
