package com.example.shopclues_clone.utils;

import android.content.Context;

public class SharedPreferences {
    static final String PREF_BOOK = "users";
    static android.content.SharedPreferences sharePreferences ;
    public static void getsharePreferences(Context context){
        if (sharePreferences==null){
            sharePreferences = context.getSharedPreferences(PREF_BOOK,Context.MODE_PRIVATE);
        }
    }
    public static void writeStringToPrefernces(String key,String value){
        android.content.SharedPreferences.Editor editor = sharePreferences.edit();
        editor.putString(key,value);
        editor.apply();
    }
    public static  String getStringFromPreference(String key){
        return sharePreferences.getString(key,"");
    }

    public static void writeBooleanToPrefernces(String key,Boolean value){
        android.content.SharedPreferences.Editor editor = sharePreferences.edit();
        editor.putBoolean(key,value);
        editor.apply();
    }
    public static  Boolean getBooleanFromPreference(String key){
        return sharePreferences.getBoolean(key,false);
    }
}
