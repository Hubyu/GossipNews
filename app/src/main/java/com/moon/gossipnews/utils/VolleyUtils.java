package com.moon.gossipnews.utils;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * ***********************************************************
 * author: alex
 * time: 16/7/5 下午2:59
 * name:
 * overview:
 * usage:
 * *************************************************************
 */
public class VolleyUtils {
    private static VolleyUtils mInstance;
    private static RequestQueue mQueue;
    private ImageLoader mLoader;
    private ImageLoader.ImageCache mCache;

    public RequestQueue getQueue() {
        return mQueue;
    }

    public ImageLoader getLoader() {
        return mLoader;
    }

    //1.构造方法私有化
    private VolleyUtils(Context context) {
        //做一些事情
        mQueue = Volley.newRequestQueue(context);
        mCache = new MyImageCache();
        mLoader = new ImageLoader(mQueue, mCache);
    }

    //2.提供一个静态方法，返回一个当前类
    public static VolleyUtils newInstance(Context context) {
        if (mInstance == null) {
            synchronized (VolleyUtils.class) {
                if (mInstance == null) {
                    mInstance = new VolleyUtils(context);
                }
            }
        }
        return mInstance;
    }

    public static <T> GsonRequest<T> newGsonRequest(int method, String url, Response.Listener<T> listener,
                                                    Response.ErrorListener errorlistener, Class<T> clazz) {
        GsonRequest<T> request = new GsonRequest<>(method, url, listener, errorlistener, clazz);
        mQueue.add(request);
        return request;
    }
}
