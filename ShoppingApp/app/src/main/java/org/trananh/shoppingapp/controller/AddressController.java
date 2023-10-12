package org.trananh.shoppingapp.controller;

import android.util.Log;

import org.trananh.shoppingapp.api.ApiService;
import org.trananh.shoppingapp.util.MyHttpResponseArray;

import java.io.IOException;

public class AddressController {
    public AddressController(){}

    public MyHttpResponseArray getAll() {
        MyHttpResponseArray myHttpResponseArray;
        try {
            myHttpResponseArray = (MyHttpResponseArray) ApiService.apiService.getAllAddress().execute().body();
        } catch (IOException e) {
            Log.e("error", e.getMessage());
            return null;
        }
        return myHttpResponseArray;
    }

    public MyHttpResponseArray getByLevel(int level){
        MyHttpResponseArray myHttpResponseArray;
        try{
            myHttpResponseArray = ApiService.apiService.getAddressByLevel(level).execute().body();
        }catch (IOException e){
            Log.e("error", e.getMessage());
            return null;
        }
        return myHttpResponseArray;
    }


}
