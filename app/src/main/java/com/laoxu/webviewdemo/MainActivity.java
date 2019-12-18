package com.laoxu.webviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private Button loadUrlbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        initSettings();//webview的设置

        initData();

    }

    /**
     * 初始化数据的方法
     */
    private void initData() {

        //首先加载html
        webView.loadUrl("file:///android_asset/zuoye.html");


        //把创建的接收类，加入到webviw中进行管理
//        webView.addJavascriptInterface(new JsToAndroid(),"a");
        webView.addJavascriptInterface(new GetProduct(),"getProduct");
        loadUrlbtn = findViewById(R.id.loadUrl);
        loadUrlbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //加载一个远程网页
                webView.loadUrl("https://www.baidu.com/");
            }
        });

    }

    /**
     * webview的设置
     */
    private void initSettings() {
        //支持js，变成动态，可以传递数据
        webView.getSettings().setJavaScriptEnabled(true);



    }

    private void initView() {

        webView =findViewById(R.id.webview);

        //支持弹窗，处理webview加载过程中的监听,可以在应用内加载
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
    }


    public void loadLocal(View view) {

        // 先载入JS代码
        // 格式规定为:file:///android_asset/文件名.html
        webView.loadUrl("file:///android_asset/hellojs.html");
    }

    /**
     * 加载局部代码
     * @param view
     */
    public void loadData(View view) {

        String s = "<p>我是一个段落</p>\n" +
                "<div id=\"content\"></div>\n" +
                "<button onclick=\"click()\" >点击</button>";

        webView.loadData(s,"text/html; charset=UTF-8",null);
    }

    /**
     * android 传递数据给js（网站的js代码）
     * @param view
     */
    public void androidtojs(View view) {

        int age = 50;

        //无参数
//        webView.loadUrl("javascript:tomsg()");
        //有参数，年龄
        webView.loadUrl("javascript:age('"+age+"')");
    }


}
