package com.moon.gossipnews.app;

import android.app.Application;
import android.util.DisplayMetrics;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * ***********************************************************
 * author: alex
 * time: 16/7/4 上午11:40
 * name: Application
 * overview: 全局的入口类。。。第一个运行的类。。。一般会在这里做全局的初始化工作
 * 比如： 百度地图的初始化，比如网络框架的初始化，比如，图片框架的初始化
 * 比如： 三方分享，消息推送等等
 * usage:
 * 1. 写一个类继承 Application
 * 2. 在清单文件中注册<application
 * android:name=".app.IApp"></application>
 * *************************************************************
 */
public class IApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader();
    }

    //初始化全局的图片配置信息
    private void initImageLoader() {
        //自定义设置全局信息
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .diskCacheSize(30 * 1024 * 1024)
                .memoryCacheSize(10 * 1024 * 1024)
                .build();
        ImageLoader.getInstance().init(config);
        //使用默认的全局配置信息
//        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));

    }
}
