package com.example.qamber.disaster_update.ModelClass;

import com.google.gson.annotations.SerializedName;

/**
 * Created by qamber.haider on 6/19/2018.
 */

public class MSG {

    private Integer success;
    private String message;

    @SerializedName("user")
    private User user;

    /**
     * No args constructor for use in serialization
     */
    public MSG() {
    }


    /**
     * @param message
     * @param success
     */
    public MSG(Integer success, String message,User user) {
        super();
        this.success = success;
        this.message = message;
        this.user = user;

    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }
}
