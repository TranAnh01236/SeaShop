package org.trananh.shoppingapp.controller;

import android.util.Log;

import org.trananh.shoppingapp.api.ApiService;
import org.trananh.shoppingapp.util.MyHttpResponse;
import org.trananh.shoppingapp.util.MyHttpResponseArray;

import java.io.IOException;

public class UnitOfMeasureController {
    public UnitOfMeasureController(){

    }

    public MyHttpResponseArray getAll(){
        MyHttpResponseArray myHttpResponseArray;
        try{
            myHttpResponseArray = ApiService.apiService.getAllUnitOfMeasure().execute().body();
        }catch (IOException e){
            Log.e("error", e.getMessage());
            return null;
        }
        return myHttpResponseArray;
    }

    public MyHttpResponseArray getUnitsByProductId(String productId) {
        MyHttpResponseArray myHttpResponseArray;
        try {
            myHttpResponseArray = ApiService.apiService.getUnitsByProductId(productId).execute().body();
        } catch (IOException e) {
            Log.e("error", e.getMessage());
            return null;
        }
        return myHttpResponseArray;
    }

    public MyHttpResponse getLowesUnitByProductId(String productId) {
        MyHttpResponse myHttpResponse;
        try {
            myHttpResponse = ApiService.apiService.getLowestUnitByProductId(productId).execute().body();
        } catch (IOException e) {
            Log.e("error", e.getMessage());
            return null;
        }
        return myHttpResponse;
    }
}
