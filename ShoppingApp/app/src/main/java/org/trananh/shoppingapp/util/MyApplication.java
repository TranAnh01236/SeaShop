package org.trananh.shoppingapp.util;

import android.app.Application;

import org.trananh.shoppingapp.util.data_local.DataLocalManager;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DataLocalManager.init(getApplicationContext());
    }
}
