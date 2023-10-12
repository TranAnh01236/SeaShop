package org.trananh.shoppingapp.controller;

import android.util.Log;

import org.trananh.shoppingapp.api.ApiService;
import org.trananh.shoppingapp.util.MyHttpResponseArray;

import java.io.IOException;

public class ProductController {
    public ProductController(){

    }

    public MyHttpResponseArray getAll() {
        MyHttpResponseArray myHttpResponseArray;
        try {
            myHttpResponseArray = (MyHttpResponseArray) ApiService.apiService.getAllProduct().execute().body();
        } catch (IOException e) {
            Log.e("error", e.getMessage());
            return null;
        }
        return myHttpResponseArray;
    }

    public MyHttpResponseArray getByCategory(String categoryId){
        MyHttpResponseArray myHttpResponseArray;
        try {
            myHttpResponseArray = (MyHttpResponseArray) ApiService.apiService.getProductByCategory(categoryId).execute().body();
        } catch (IOException e) {
            Log.e("error", e.getMessage());
            return null;
        }
        return myHttpResponseArray;
    }
}
