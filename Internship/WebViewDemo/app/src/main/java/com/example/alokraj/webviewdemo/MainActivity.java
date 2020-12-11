package com.example.alokraj.webviewdemo;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    WebView web;
    ProgressDialog dialog;
    SwipeRefreshLayout mySwipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        // Inflation

        mySwipeRefreshLayout = (SwipeRefreshLayout)this.findViewById(R.id.swipeContainer);


        web = (WebView) findViewById(R.id.webview);
        web.setWebViewClient(new WebViewClient() {

            // This method will be triggered when the Page Started Loading

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                dialog = ProgressDialog.show(MainActivity.this, null,
                        "Loading...");
                dialog.setCancelable(true); // dismiss progress on touch of screen
                dialog.setCanceledOnTouchOutside(true);
                super.onPageStarted(view, url, favicon);
            }

            // This method will be triggered when the Page loading is completed

            public void onPageFinished(WebView view, String url) {
                dialog.dismiss();
                mySwipeRefreshLayout.setRefreshing(false);
                super.onPageFinished(view, url);
            }

            // This method will be triggered when error page appear

            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                dialog.dismiss();
                // You can redirect to your own page instead getting the default
                // error page
//                Toast.makeText(MainActivity.this,
//                        "The Requested Page Does Not Exist", Toast.LENGTH_LONG).show();
                web.loadUrl("http://groupclap.com");
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
        web.loadUrl("http://groupclap.com");
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setUseWideViewPort(true);
        web.getSettings().setJavaScriptEnabled(true);
        web.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        web.reload();
                    }
                }
        );

    }

    @Override
    public void onBackPressed()
    {
        if (web.canGoBack())
        {
            web.goBack();
        }
        else
        {
            super.onBackPressed();
        }

    }
}
