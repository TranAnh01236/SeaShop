package org.trananh.shoppingapp.controller;

import android.util.Log;

import org.trananh.shoppingapp.api.ApiService;
import org.trananh.shoppingapp.util.MyHttpResponseArray;

import java.io.IOException;

public class PriceDetailController {

    public PriceDetailController(){}

    public MyHttpResponseArray getAll() {
        MyHttpResponseArray myHttpResponseArray;
        try {
            myHttpResponseArray = (MyHttpResponseArray) ApiService.apiService.getAllPriceDetail().execute().body();
        } catch (IOException e) {
            Log.e("error", e.getMessage());
            return null;
        }
        return myHttpResponseArray;
    }

}
