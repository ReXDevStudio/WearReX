package cn.rexwear.wearrex.utils;

import static cn.rexwear.wearrex.activities.WelcomeActivity.TAG;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by XC-Qan on 2022/3/21.
 * I'm very cute so please be nice to my code!
 * 给！爷！写！注！释！
 * 给！爷！写！注！释！
 * 给！爷！写！注！释！
 */

public class GetSharedPreferences {
    //private static volatile GetSharedPreferences instance = null;


    //final static String PREFS_NAME = "WEAREXSHP";

    public static final String PREFS_NAME = "WEAREXSHP";
    private static GetSharedPreferences instance;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private GetSharedPreferences(Context context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public static GetSharedPreferences getInstance(Context context) {
        if (instance == null) {
            synchronized (GetSharedPreferences.class) {
                if (instance == null) {
                    instance = new GetSharedPreferences(context);
                }
            }
        }
        return instance;
    }

    /*public static GetSharedPreferences getInstance(Context context) {
        if(instance == null)
            instance = new GetSharedPreferences(context.getApplicationContext());
        return instance;
    }*/


    public int getUserID() {
        //SharedPreferences userInfo = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int username = preferences.getInt("userID", -1);//读取username
        return username;
    }

    public boolean getUserIsExperiment() {
        //SharedPreferences userInfo = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean bool = preferences.getBoolean("isExperiment", false);
        return bool;
    }

    public void saveUserInfo(int userID) {
        //SharedPreferences userInfo = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        //SharedPreferences.Editor editor = userInfo.edit();//获取Editor
        //得到Editor后，写入需要保存的数据
        editor.putInt("userID", userID);
        editor.commit();//提交修改
        Log.i(TAG, "保存用户信息成功");
    }

    public void DeleteUserInfo() {
        //SharedPreferences userInfo = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        //SharedPreferences.Editor editor = userInfo.edit();//获取Editor
        //得到Editor后，写入需要保存的数据
        editor.remove("userID");
        editor.remove("isExperiment");
        editor.commit();//提交修改
    }
}
