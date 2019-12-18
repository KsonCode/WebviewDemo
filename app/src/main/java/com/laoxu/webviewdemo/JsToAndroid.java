package com.laoxu.webviewdemo;

import android.app.AlertDialog;
import android.app.Application;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * 接受js传过来的数据
 */
public class JsToAndroid   {

    //注解,确保这个方法js能调用到
    @JavascriptInterface
    public void b(){

        Toast.makeText(App.getContext(), "无参", Toast.LENGTH_SHORT).show();


        System.out.println("js 调用了android的无参方法");

    }

    @JavascriptInterface
    public void aaa(String msg){

        //电脑

        System.out.println("js 调用了android的有参方法==="+msg);

        //请求根据关键词搜索商品列表的接口
        //volley请求

    }
}
