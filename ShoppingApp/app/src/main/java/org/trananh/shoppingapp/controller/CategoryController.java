package org.trananh.shoppingapp.controller;

import android.util.Log;

import org.trananh.shoppingapp.api.ApiService;
import org.trananh.shoppingapp.util.MyHttpResponse;
import org.trananh.shoppingapp.util.MyHttpResponseArray;

import java.io.IOException;

public class CategoryController {
    public CategoryController(){

    }

    public MyHttpResponseArray getAll() {
        MyHttpResponseArray myHttpResponseArray;
        try {
            myHttpResponseArray = (MyHttpResponseArray) ApiService.apiService.getAllCategory().execute().body();
        } catch (IOException e) {
            Log.e("error", e.getMessage());
            return null;
        }
        return myHttpResponseArray;
    }

    public MyHttpResponseArray getByParentId(String parentId){
        MyHttpResponseArray myHttpResponseArray;
        try {
            myHttpResponseArray = (MyHttpResponseArray) ApiService.apiService.getCategoryByParentId(parentId).execute().body();
        } catch (IOException e) {
            Log.e("error", e.getMessage());
            return null;
        }
        return myHttpResponseArray;
    }

    public MyHttpResponseArray getByTypeAndLevel(int type, int level){
        MyHttpResponseArray myHttpResponseArray;
        try{
            myHttpResponseArray = ApiService.apiService.getCategoryByTypeAndLevel(type, level).execute().body();
        }catch (IOException e){
            Log.e("error", e.getMessage());
            return null;
        }
        return myHttpResponseArray;
    }
}
