package com.sensorsdata.analytics.android.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;

public class webActivity extends Activity {


    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = findViewById(R.id.webView);
        loadWeb();

    }

    public void loadWeb(){
        String url = "http://39.98.54.111:8000/sa/app.htm";
        webView.setWebViewClient(new WebViewClient());
        SensorsDataAPI.sharedInstance().showUpWebView(webView,false);
        webView.loadUrl(url);
    }
}
