package com.laoxu.webviewdemo;

import android.app.Application;
import android.content.Context;

/**
 * 有且只有一个全局application类
 */
public class App extends Application {

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

    }

    public static Context getContext() {
        return context;
    }
}
