package com.example.imalok.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView mywebview = (WebView) findViewById(R.id.webView1);
        mywebview.loadUrl("http://www.csksrc.org/");

        String data = "<html><body><h1>Hello, NiiT!</h1></body></html>";
        mywebview.loadData(data, "text/html", "UTF-8");

        mywebview.loadUrl("file:///android_asset/myresource.html");
    }



}
