package ru.kpfu.itis.archgis.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by DNS1 on 17.05.2017.
 */

public class PreferencesManager {


    private static final String ARCH_GIS_PREFS = "arch-gis-prefs";
    private static final String USER_NAME = "user name";
    private static final String TOKEN = "token";

    public static void saveUserName(@NonNull String username, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ARCH_GIS_PREFS, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(USER_NAME, username);
        editor.apply();
    }

    public static void saveToken(@NonNull String token, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ARCH_GIS_PREFS, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(TOKEN, token);
        editor.apply();
    }



    @Nullable
    public static String getToken(Context context){
        SharedPreferences prefs = context.getSharedPreferences(ARCH_GIS_PREFS, Activity.MODE_PRIVATE);
        return prefs.getString(TOKEN,null);
    }

    @Nullable
    public static String getUserName(Context context){
        SharedPreferences prefs = context.getSharedPreferences(ARCH_GIS_PREFS, Activity.MODE_PRIVATE);
        return prefs.getString(USER_NAME,null);
    }

}
