package org.trananh.shoppingapp.controller;

import android.util.Log;

import org.trananh.shoppingapp.api.ApiService;
import org.trananh.shoppingapp.model.User;
import org.trananh.shoppingapp.util.MyHttpResponse;
import org.trananh.shoppingapp.util.MyHttpResponseArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthController {
    public AuthController(){

    }

    public MyHttpResponse login(String account, String password) {
        Map map = new HashMap();
        map.put("account", account);
        map.put("password", password);
        MyHttpResponse myHttpResponse;
        try {
            myHttpResponse = (MyHttpResponse) ApiService.apiService.login(map).execute().body();
        } catch (IOException e) {
            Log.e("error", e.getMessage());
            return null;
        }
        return myHttpResponse;
    }

    public MyHttpResponse register(User user){
        Map<String, String> map = new HashMap();
        map.put("id", user.getId());
        map.put("firstName", user.getFirstName());
        map.put("lastName", user.getLastName());
        map.put("loginName", user.getLoginName());
        map.put("password", user.getPassword());
        map.put("phoneNumber", user.getPhoneNumber());
        map.put("addressDetail", user.getAddressDetail());
        map.put("type", String.valueOf(user.getType()));
        map.put("dayOfBirth", user.getDayOfBirth().toString());
        map.put("email", user.getEmail());
        map.put("gender", String.valueOf(user.getGender()));
        map.put("address", user.getAddress().getId());

        MyHttpResponse myHttpResponse;
        try {
            myHttpResponse = (MyHttpResponse) ApiService.apiService.register(map).execute().body();
        } catch (IOException e) {
            Log.e("error", "Lỗi rồi");
            Log.e("error", e.getMessage());
            return null;
        }
        return myHttpResponse;
    }
}
