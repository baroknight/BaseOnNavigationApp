package com.example.mynavigation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mynavigation.R;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

public class VisualFragment extends SupportFragment {
    WebView wView;
    ArrayList<Object> dataName = new ArrayList<>();
    ArrayList<String> dataPrice = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fra_visual,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        setData();
        setWebView();
    }

    private void setData() {
//        此处如果想要在html中以字符串作为x轴，则必须在字符串左右添加""，
//        否则js接收到的数据是衬衫，而不是"衬衫"，这样将识别不出数据类型导致画不了图
        dataName.add("\"衬衫\"");
        dataName.add("\"羊毛衫\"");
        dataName.add("\"雪纺衫\"");
        dataName.add("\"裤子\"");
        dataName.add("\"高跟鞋\"");
        dataName.add("\"袜子\"");
        dataPrice.add("5");
        dataPrice.add("23");
        dataPrice.add("29");
        dataPrice.add("111");
        dataPrice.add("56");
        dataPrice.add("11");
    }
    private void setWebView() {
        System.out.println(dataName.toString());
        System.out.println(dataPrice.toString());
        wView.loadUrl("file:///android_asset/www/index.html");
//        在WebView中启用或禁用文件访问。
        wView.getSettings().setAllowFileAccess(true);
//        告诉WebView启用JavaScript执行
        wView.getSettings().setJavaScriptEnabled(true);
        wView.setWebViewClient(new WebViewClient(){
//            通知主机应用程序页面已完成加载。
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                wView.loadUrl("javascript:createChart("+dataName+","+dataPrice+")");
            }
        });

    }



    private void initView(View view) {
        wView = view.findViewById(R.id.wview_visual);
    }
}
