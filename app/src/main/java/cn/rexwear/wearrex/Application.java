package cn.rexwear.wearrex;


import android.annotation.SuppressLint;
import android.content.Context;

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

/**
 * Created by XC-Qan on 2022/4/9.
 * I'm very cute so please be nice to my code!
 * 给！爷！写！注！释！
 * 给！爷！写！注！释！
 * 给！爷！写！注！释！
 */

public class Application extends android.app.Application {
    @SuppressLint("StaticFieldLeak")
    static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AppCenter.start(this, "2f44bd31-a615-439b-8560-8386b74f29bd", Analytics.class, Crashes.class);
        context = getApplicationContext();
    }
}
