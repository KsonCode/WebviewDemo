package com.laoxu.webviewdemo;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

public class VolleyUtils {

    //第一步，私有静态属性
    private static VolleyUtils volleyUtils;

    private RequestQueue requestQueue;

    //第二步，构造方式：私有
    private VolleyUtils() {

        requestQueue = Volley.newRequestQueue(App.getContext());

    }

    //第三步，实例化对象，提供给外部调用者使用,懒加载
    public static VolleyUtils getInstance() {
        if (volleyUtils == null) {
            volleyUtils = new VolleyUtils();
        }
        return volleyUtils;
    }

    /**
     * 对接口进行get请求的方法
     */
    public void doGet(String url, final VolleyCallback volleyCallback){

        //第二步，创建请求对象，stringreauest
        StringRequest stringRequest = new StringRequest(StringRequest.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                volleyCallback.success(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyCallback.error(error);
            }
        });

        //第三步，添加请求对象到请求队列（容器）
        requestQueue.add(stringRequest);



    }

    /**
     * 对接口进行post请求
     */
    public void doPost(String url, final Map<String,String> params, final VolleyCallback volleyCallback){
        //第二步，创建请求对象，stringreauest
        StringRequest stringRequest = new StringRequest(StringRequest.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                volleyCallback.success(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                volleyCallback.error(error);

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };

        //第三步，添加请求对象到请求队列（容器）
        requestQueue.add(stringRequest);
    }


    public interface VolleyCallback{
        void success(String json);
        void error(Throwable throwable);
    }




}
