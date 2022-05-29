package cn.rexwear.wearrex.utils;


import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.rexwear.wearrex.managers.UserManager;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class NetworkUtils {

    private static final String domain = "https://rexwear.cn/api"; //主域名


    private final static HashMap<String, List<Cookie>> cookieStore = new HashMap<>();


    /**
     * GET网络请求
     *
     * @param url      url路径
     * @param callback 网络请求回调
     */
    public static void getUrl(String url, Callback callback) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(@NonNull HttpUrl httpUrl, @NonNull List<Cookie> list) {
                        cookieStore.put(httpUrl.host(), list);
                    }

                    @NonNull
                    @Override
                    public List<Cookie> loadForRequest(@NonNull HttpUrl httpUrl) {
                        List<Cookie> cookies = cookieStore.get(httpUrl.host());
                        return cookies != null ? cookies : new ArrayList<>();
                    }
                })
                .build();
        Request request;
        if (UserManager.getUserID() != -1) {
            request = new Request.Builder()
                    .url(domain + url)
                    .get()
                    .addHeader("User-Agent", "ReXAppAndroid/JavaOkHttpRequested")
                    .addHeader("XF-API-Key", "x3KEr7kI-ZOrNOjN46HAkB0oGgqHkXLt")
                    .addHeader("XF-API-User", String.valueOf(UserManager.getUserID()))
                    .build();
        } else {
            request = new Request.Builder()
                    .url(domain + url)
                    .get()
                    .addHeader("User-Agent", "ReXAppAndroid/JavaOkHttpRequested")
                    .addHeader("XF-API-Key", "x3KEr7kI-ZOrNOjN46HAkB0oGgqHkXLt")
                    .build();
        }
        client.newCall(request).enqueue(callback);
    }


    /**
     * Post网络请求
     *
     * @param url      url路径
     * @param body     请求体
     * @param callback 请求回调
     */
    public static void postUrl(String url, RequestBody body, Callback callback) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request;
        request = new Request.Builder()
                .url(domain + url)
                .post(body)
                .addHeader("User-Agent", "ReXAppAndroid/JavaOkHttpRequested")
                .addHeader("XF-API-Key", "x3KEr7kI-ZOrNOjN46HAkB0oGgqHkXLt")
                .build();

        client.newCall(request).enqueue(callback);
    }
}
