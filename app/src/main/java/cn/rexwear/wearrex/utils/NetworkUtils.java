package cn.rexwear.wearrex.utils;


import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class NetworkUtils {
    private static NetworkUtils instance;
    private NetworkUtils(Context context){
        context = this.context;
        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)//设置连接超时时间
                .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
                .build();
    }
    public static NetworkUtils getInstance(Context context)
    {
        if (instance == null)
        {
            instance = new NetworkUtils(context);
        }
        return instance;
    }

    Context context;
    final static String TAG = "NetworkUtils TAG";
    public OkHttpClient client;

    public void ShowLoadingDialogue() {

    }
}
