package cn.rexwear.wearrex.utils;


import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class NetworkUtils {

    final static String TAG = "NetworkUtils TAG";
    private static String domain = "https://rexwear.cn/api"; //主域名

    public static void setDomain(String domain) {
        NetworkUtils.domain = domain;
    }

    /**
     * GET网络请求
     *
     * @param url      url路径
     * @param callback 网络请求回调
     */
    public static void getUrl(String url, Callback callback) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(domain + url)
                .get()
                .addHeader("User-Agent", "ReXAppAndroid/JavaOkHttpRequested")
                .addHeader("XF-API-Key", "x3KEr7kI-ZOrNOjN46HAkB0oGgqHkXLt")
                .build();
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
