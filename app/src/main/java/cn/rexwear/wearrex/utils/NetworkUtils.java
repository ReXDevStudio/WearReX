package cn.rexwear.wearrex.utils;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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

    public void RequestPOST(String apiName, RequestBody form, String userID){
        Request request = new Request.Builder()
                .url("https://rexwear.cn/index.php?api/"+apiName)
                .addHeader("User-Agent", "Mozilla/5.0 (compatible;) ReXApp/1.0")
                .addHeader("XF-API-Key", "x3KEr7kI-ZOrNOjN46HAkB0oGgqHkXLt")
                .addHeader("XF-Api-User", userID)
                .post(form)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.d(TAG, "onResponse: " + response.body().string());
            }
        });
    }

    public void RequestGET(String apiName, String userID){
        Request request = new Request.Builder()
                .url("https://rexwear.cn/index.php?api/"+apiName)
                .method("GET", null)
                .addHeader("User-Agent", "ReXAppAndroid/JavaOkHttpRequested")
                .addHeader("XF-API-Key", "x3KEr7kI-ZOrNOjN46HAkB0oGgqHkXLt")
                .addHeader("XF-Api-User", userID)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.d(TAG, "onResponse: " + response.body().string());
            }
        });
    }
}
