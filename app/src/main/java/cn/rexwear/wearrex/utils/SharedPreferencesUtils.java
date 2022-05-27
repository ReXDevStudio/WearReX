package cn.rexwear.wearrex.utils;

import android.content.Context;
import android.content.SharedPreferences;

import cn.rexwear.wearrex.Application;

/**
 * Created by XC-Qan on 2022/3/21.
 * I'm very cute so please be nice to my code!
 * 给！爷！写！注！释！
 * 给！爷！写！注！释！
 * 给！爷！写！注！释！
 */

public class SharedPreferencesUtils {
    public static final String PREFS_NAME = "cn.rexwear.sharedpreferences";
    private static final SharedPreferences preferences = Application.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    private static final SharedPreferences.Editor editor = preferences.edit();


    public static String getString(String key, String defaultValue) {
        return preferences.getString(key, defaultValue);
    }

    public static Integer getInt(String key, Integer defaultValue) {
        return preferences.getInt(key, defaultValue);
    }

    public static Boolean getBoolean(String key, Boolean defaultValue) {
        return preferences.getBoolean(key, defaultValue);
    }


    public static void saveString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public static void saveInt(String key, Integer value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public static void saveBool(String key, Boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static void delete(String key) {
        editor.remove(key);
        editor.commit();
    }
}
