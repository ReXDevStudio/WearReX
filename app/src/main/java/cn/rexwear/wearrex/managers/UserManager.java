package cn.rexwear.wearrex.managers;

import cn.rexwear.wearrex.beans.UserBean;
import cn.rexwear.wearrex.utils.SharedPreferencesUtils;

/**
 * Created by XC-Qan on 2022/5/27.
 * I'm very cute so please be nice to my code!
 * 给！爷！写！注！释！
 * 给！爷！写！注！释！
 * 给！爷！写！注！释！
 */

public class UserManager {
    /**
     * 获取用户是否为游客登录
     */
    public static boolean getUserIsExperiment() {
        return SharedPreferencesUtils.getBoolean("isExperiment", false);
    }

    /**
     * 获取储存在本地的userID
     */
    public static int getUserID() {
        return SharedPreferencesUtils.getInt("userID", -1);
    }

    /**
     * 删除所有储存在本地的用户数据
     */
    public static void deleteAllUserInfo() {
        SharedPreferencesUtils.delete("userID");
        SharedPreferencesUtils.delete("isExperiment");
        //SharedPreferencesUtils.delete("password");
    }

    /**
     * 本地设置用户是否为游客登录
     */
    public static void saveUserIsExperiment(boolean bool) {
        SharedPreferencesUtils.saveBool("isExperiment", bool);
    }

    public static void saveUserInfo(UserBean user) {
        SharedPreferencesUtils.saveInt("userID", user.user.userId);
        //SharedPreferencesUtils.saveString("password", );
    }
}