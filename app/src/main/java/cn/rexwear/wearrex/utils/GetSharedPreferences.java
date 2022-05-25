package cn.rexwear.wearrex.utils;

import static cn.rexwear.wearrex.activities.WelcomeActivity.TAG;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import cn.rexwear.wearrex.Application;

/**
 * Created by XC-Qan on 2022/3/21.
 * I'm very cute so please be nice to my code!
 * 给！爷！写！注！释！
 * 给！爷！写！注！释！
 * 给！爷！写！注！释！
 */

public class GetSharedPreferences {
    public static final String PREFS_NAME = "cn.rexwear.sharedpreferences";
    private static final SharedPreferences preferences = Application.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    private static final SharedPreferences.Editor editor = preferences.edit();

    /**
     * 获取储存在本地的userID
     */
    public static int getUserID() {
        //SharedPreferences userInfo = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return preferences.getInt("userID", -1);
    }

    /**
     * 获取用户是否为游客登录
     */
    public static boolean getUserIsExperiment() {
        //SharedPreferences userInfo = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return preferences.getBoolean("isExperiment", false);
    }

    /**
     * 将获取到的userID储存在本地
     */
    public static void saveUserInfo(int userID) {
        //SharedPreferences userInfo = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        //SharedPreferences.Editor editor = userInfo.edit();//获取Editor
        //得到Editor后，写入需要保存的数据
        editor.putInt("userID", userID);
        editor.commit();//提交修改
        Log.i(TAG, "保存用户信息成功");
    }

    /**
     * 删除储存在本地的userID
     */
    public static void DeleteUserInfo() {
        //SharedPreferences userInfo = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        //SharedPreferences.Editor editor = userInfo.edit();//获取Editor
        //得到Editor后，写入需要保存的数据
        editor.remove("userID");
        editor.remove("isExperiment");
        editor.commit();//提交修改
    }

    /**
     * 本地设置用户是否为游客登录
     *
     */
    public static void saveUserIsExperiment(boolean bool) {
        editor.putBoolean("isExperiment", bool);
        editor.commit();//提交修改
    }
}
