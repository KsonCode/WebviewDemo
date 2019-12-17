package com.laoxu.webviewdemo;

import android.webkit.JavascriptInterface;

/**
 * 接受js传过来的数据
 */
public class JsToAndroid {

    //注解
    @JavascriptInterface
    public void getJsData(){

        System.out.println("js 调用了android的无参方法");

    }

    @JavascriptInterface
    public void getJsData(String msg){

        System.out.println("js 调用了android的有参方法==="+msg);

    }
}
