package com.moon.gossipnews.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * ***********************************************************
 * author: alex
 * time: 16/7/5 上午11:57
 * name:
 * overview:
 * usage: crash
 * 1.分配一定内存空间，专门存取图片，一般为内存大小的1/8
 * 2.
 * *************************************************************
 */
public class MyImageCache implements ImageLoader.ImageCache {

    private LruCache<String, Bitmap> mCache;

    public MyImageCache() {
        //分配最大内存空间的1/8
        long maxMemory = Runtime.getRuntime().maxMemory() / 8;
        mCache = new LruCache<String, Bitmap>((int) maxMemory) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //得到当前图片的大小
                return value.getByteCount();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return mCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        if (getBitmap(url) == null)
            mCache.put(url, bitmap);
    }
}
