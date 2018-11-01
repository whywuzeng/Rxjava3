package com.bhome.rxjava2.http3.cookie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by Administrator on 2018-11-01.
 * <p>
 * by author wz
 * <p>
 * com.bhome.rxjava2.http3.cookie
 */

public class SimpleCookieJar implements CookieJar {

    private final HashMap<String ,List<Cookie>> listHashMap =new HashMap<>();

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        listHashMap.put(url.host(),cookies);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
         List<Cookie> cookies = listHashMap.get(url.host());
        if (cookies==null)
        {
            cookies=new ArrayList<>();
        }

//        List<Cookie> list=new ArrayList<>();
//        for (Cookie cookie:list) {
                //这个 cookie 匹配原则更加严谨
//            cookie.matches(url)
//        }
        return cookies;
    }
}
