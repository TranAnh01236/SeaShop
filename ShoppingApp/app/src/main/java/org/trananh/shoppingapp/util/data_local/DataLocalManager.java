package org.trananh.shoppingapp.util.data_local;

import android.content.Context;

import com.google.gson.Gson;

import org.trananh.shoppingapp.model.User;
import org.trananh.shoppingapp.util.Constants;

public class DataLocalManager {
    private static final String REF_FIRST_INSTALL = "REF_FIRST_INSTALL";
    private static final String LOGIN_USER = "LOGIN_USER";
    private static DataLocalManager instance;
    private MySharePreferences mySharePreferences;

    public static void init(Context context){
        instance = new DataLocalManager();
        instance.mySharePreferences = new MySharePreferences(context);
    }

    public static DataLocalManager getInstance(){
        if (instance == null){
            instance = new DataLocalManager();
        }
        return instance;
    }

    public static void setUser(User user){
        String strJsonUser = Constants.gson.toJson(user);
        DataLocalManager.getInstance().mySharePreferences.putStringValue(LOGIN_USER, strJsonUser);
    }

    public static User getUser(){
        String strJsonUser = DataLocalManager.getInstance().mySharePreferences.getStringValue(LOGIN_USER);
        User user = null;
        if (!strJsonUser.trim().equals("")){
            user = Constants.gson.fromJson(strJsonUser, User.class);
        }
        return user;
    }

    public static void deleteUser(){
        DataLocalManager.getInstance().mySharePreferences.removeStringValue(LOGIN_USER);
    }

}
