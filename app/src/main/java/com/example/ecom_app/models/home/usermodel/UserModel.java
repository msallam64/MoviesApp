package com.example.ecom_app.models.home.usermodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserModel {
    @SerializedName("user")
    @Expose
    private UserLog user;

    public UserLog getUser() {
        return user;
    }

    public void setUser(UserLog user) {
        this.user = user;
    }
}
