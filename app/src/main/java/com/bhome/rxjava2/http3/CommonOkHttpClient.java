package com.bhome.rxjava2.http3;

import com.bhome.rxjava2.http3.cookie.SimpleCookieJar;
import com.bhome.rxjava2.http3.listener.DisposeDataHandle;
import com.bhome.rxjava2.http3.response.CommonFileCallback;
import com.bhome.rxjava2.http3.response.CommonJsonCallback;
import com.bhome.rxjava2.http3.ssl.HttpsUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018-11-01.
 * <p>
 * by author wz
 * http3集成
 *
 * build对象
 * 延时
 * 请求头
 * nameVerifier  verify
 * <p>
 * com.bhome.rxjava2.http3
 */

public class CommonOkHttpClient {

    private static final int TIME_TOU=30;
    private static OkHttpClient mOkHttpClient;

    static {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        /**
         * 添加请求头
         */
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                final Request request = chain.request()
                        .newBuilder()
                        .addHeader("User-Agent", "bhome-Mobile")
                        .build();
                return chain.proceed(request);
            }
        });
        builder.cookieJar(new SimpleCookieJar());
        builder.connectTimeout(TIME_TOU, TimeUnit.SECONDS);
        builder.readTimeout(TIME_TOU,TimeUnit.SECONDS);
        builder.writeTimeout(TIME_TOU,TimeUnit.SECONDS);
        builder.followRedirects(true);

        builder.sslSocketFactory(HttpsUtils.initSSLSocketFactory(),HttpsUtils.initTrustManager());
        mOkHttpClient= builder.build();
    }

    public static OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public static Call get(Request request, DisposeDataHandle handle)
    {
        final Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }

    public static Call post(Request request,DisposeDataHandle handle)
    {
        final Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }

    public static Call downloadFile(Request request,DisposeDataHandle handle)
    {
        final Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonFileCallback(handle));
        return call;
    }

}
