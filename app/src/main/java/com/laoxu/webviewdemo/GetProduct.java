package com.laoxu.webviewdemo;

import android.webkit.JavascriptInterface;

import com.google.gson.Gson;
import com.laoxu.webviewdemo.entity.ProductEntity;

/**
 * 发送方
 */
public class GetProduct {

    @JavascriptInterface
    public void getKeyword(String name){
        //处理请求逻辑

        String url = "http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?keyword="+name+"&count=5&page=1";

        VolleyUtils.getInstance().doGet(url, new VolleyUtils.VolleyCallback() {
            @Override
            public void success(String json) {

                ProductEntity productEntity = new Gson().fromJson(json, ProductEntity.class);

                if (networkCallback!=null){
                    networkCallback.success(productEntity);
                }

            }

            @Override
            public void error(Throwable throwable) {

                networkCallback.error(throwable);

            }
        });
    }

    //第二步。生命属性
    private NetworkCallback networkCallback;

    //第三步，set方法，提供给外界进行设置并初始化
    public void setNetworkCallback(NetworkCallback networkCallback) {
        this.networkCallback = networkCallback;
    }

    //第一步，创建接口类
    public interface NetworkCallback{
        void success(ProductEntity productEntity);
        void error(Throwable throwable);
    }
}
