package com.laoxu.webviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.laoxu.webviewdemo.adapter.ProductAdapter;
import com.laoxu.webviewdemo.entity.ProductEntity;

/**
 * 接收方,实现接口
 */
public class WorkActivity extends AppCompatActivity  {
    private WebView webView;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        webView = findViewById(R.id.webview);
        rv = findViewById(R.id.rv);
//        rv.setLayoutManager(new LinearLayoutManager(this));//线性布局
//        rv.setLayoutManager(new GridLayoutManager(this,2));//两列网格列表
        rv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));//垂直瀑布流列表

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());//在当前webview上显示

        webView.loadUrl("file:///android_asset/zuoye.html");

        GetProduct getProduct = new GetProduct();
        //发送方去调用set方法，进行初始化接口属性
        getProduct.setNetworkCallback(new GetProduct.NetworkCallback() {
            @Override
            public void success(ProductEntity productEntity) {
                //得到数据了，开始创建适配器，展示数据
                ProductAdapter productAdapter = new ProductAdapter(WorkActivity.this,productEntity.result);

                rv.setAdapter(productAdapter);
            }

            @Override
            public void error(Throwable throwable) {
                //toast提示错误，或者弹窗提示错误

            }
        });
        webView.addJavascriptInterface(getProduct,"getProduct");
    }

//    /**
//     * 成功
//     * @param productEntity
//     */
//    @Override
//    public void success(ProductEntity productEntity) {
//
//    }
//
//    /**
//     * error
//     * @param throwable
//     */
//    @Override
//    public void error(Throwable throwable) {
//
//    }
}
