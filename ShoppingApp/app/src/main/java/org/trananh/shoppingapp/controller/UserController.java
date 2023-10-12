package org.trananh.shoppingapp.controller;

import android.util.Log;

import org.trananh.shoppingapp.api.ApiService;
import org.trananh.shoppingapp.model.User;
import org.trananh.shoppingapp.util.MyHttpResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserController {

    public UserController(){

    }

    public MyHttpResponse getUserByPhoneNumber(String phoneNumber) {
        MyHttpResponse myHttpResponse;
        try {
            myHttpResponse = (MyHttpResponse) ApiService.apiService.getUserByPhoneNumber(phoneNumber).execute().body();
        } catch (IOException e) {
            Log.e("error", e.getMessage());
            return null;
        }
        return myHttpResponse;
    }

    public MyHttpResponse getByLoginName(String loginName) {
        MyHttpResponse myHttpResponse;
        try {
            myHttpResponse = (MyHttpResponse) ApiService.apiService.getUserByLoginName(loginName).execute().body();
        } catch (IOException e) {
            Log.e("error", e.getMessage());
            return null;
        }
        return myHttpResponse;
    }

}
