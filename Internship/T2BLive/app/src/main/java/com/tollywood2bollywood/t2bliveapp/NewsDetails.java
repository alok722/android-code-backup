package com.tollywood2bollywood.t2bliveapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import com.tollywood2bollywood.t2bliveapp.APISET.Constant;
import com.tollywood2bollywood.t2bliveapp.Other.Popup;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;

public class NewsDetails extends AppCompatActivity {
    ImageView imageView;
    TextView textView,title;
    WebView t1;
    ImageView mShare,back,like;
    Popup popup;
    boolean lik;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        popup = new Popup(this);
        t1 = (WebView) findViewById(R.id.t1);
        WebSettings webSettings = t1.getSettings();
        webSettings.setJavaScriptEnabled(true);
        WebViewClientImpl webViewClient = new WebViewClientImpl(this);
        t1.setWebViewClient(webViewClient);

        back = (ImageView)findViewById(R.id.back);
        like = (ImageView)findViewById(R.id.like);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!lik) {
                    lik = true;
                    like.setImageResource(R.drawable.icons_like_filled);
                }else{
                    lik = false;
                    like.setImageResource(R.drawable.icons_like);
                }
            }
        });
        title = (TextView)findViewById(R.id.title);
        imageView = (ImageView)findViewById(R.id.d_img);
        textView = (TextView)findViewById(R.id.d_head);
        Picasso.with(this).load(getIntent().getStringExtra("img_url")).fit().into(imageView);

        textView.setText(getIntent().getStringExtra("Heading"));
        title.setText(getIntent().getStringExtra("Heading"));

        mShare = (ImageView)findViewById(R.id.share);
        mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri bmpUri = getLocalBitmapUri(imageView);
                //Uri pictureUri = Uri.parse(getIntent().getStringExtra("img_url"));
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setAction(Intent.ACTION_SEND);
                sharingIntent.putExtra(Intent.EXTRA_TEXT, getIntent().getStringExtra("Heading"));
                sharingIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
                sharingIntent.setType("image/*");
                sharingIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(Intent.createChooser(sharingIntent, "Share News Using"));
            }
        });

        getSetData(Constant.get_post+getIntent().getStringExtra("id"));
    }
    public Uri getLocalBitmapUri(ImageView imageView) {
        // Extract Bitmap from ImageView drawable
        Drawable drawable = imageView.getDrawable();
        Bitmap bmp = null;
        if (drawable instanceof BitmapDrawable){
            bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        } else {
            return null;
        }
        // Store image to default external storage directory
        Uri bmpUri = null;
        try {
            // Use methods on Context to access package-specific directories on external storage.
            // This way, you don't need to request external read/write permission.
            // See https://youtu.be/5xVh-7ywKpE?t=25m25s
            File file =  new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            // **Warning:** This will fail for API >= 24, use a FileProvider as shown below instead.
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }


    public void getSetData(final String URLLL){
        AsyncTask<String, String, String> waitForCompletion = new AsyncTask<String, String, String>() 	{
            @Override
            protected void onPreExecute() {
                // TODO Auto-generated method stub
                super.onPreExecute();
                popup.showDialog();
            }
            @Override
            protected String doInBackground(String... params) {
                String json="";
                try {
                    HttpClient client = HttpClientBuilder.create().build();
                    Log.e("URLLL = ",""+URLLL);
                    HttpGet httpPost = new HttpGet(URLLL);
                    HttpResponse httpResponse = client.execute(httpPost);
                    BufferedReader bufferedReader = new BufferedReader(new
                            InputStreamReader(httpResponse.getEntity().getContent(),"utf-8"));
                    String line;
                    while((line = bufferedReader.readLine())!=null){
                        if(!line.trim().equals("")){
                            json += line;
                        }
                    }
                }catch(Exception e){
                    e.getMessage();
                }
                return json;
            };


            @Override
            protected void onPostExecute(String json) {
                popup.hideDialog();
                try{

                    JSONObject jsonObject1 = new JSONObject(json);
                    if(jsonObject1.getString("status").equals("ok")) {
                        jsonObject1.getString("previous_url");
                        jsonObject1.getString("next_url");
                        JSONObject jsonObject11 = new JSONObject(jsonObject1.getString("post"));
                        jsonObject11.getString("id");
                        jsonObject11.getString("type");
                        jsonObject11.getString("slug");
                        jsonObject11.getString("url");
                        jsonObject11.getString("status");
                        jsonObject11.getString("title");
                        jsonObject11.getString("title_plain");
                        //t1.loadDataWithBaseURL(jsonObject11.getString("content"));
                        t1.loadDataWithBaseURL("",jsonObject11.getString("content"), "text/html", "UTF-8", null);
                        jsonObject11.getString("excerpt");
                        jsonObject11.getString("date");
                        jsonObject11.getString("modified");
                        jsonObject11.getString("categories");
                        jsonObject11.getString("tags");
                        jsonObject11.getString("author");
                        jsonObject11.getString("comments");
                        jsonObject11.getString("attachments");
                        jsonObject11.getString("comment_count");
                        jsonObject11.getString("comment_status");
                        jsonObject11.getString("thumbnail");
                        jsonObject11.getString("custom_fields");
                        jsonObject11.getString("thumbnail_size");
                        jsonObject11.getString("thumbnail_images");
                    }else{
                        Toast.makeText(NewsDetails.this,"Something went wrong",Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    Log.e("Exception = ",e.toString());
                }
            }
        };
        if (isInternetAvailable())
            waitForCompletion.execute();
        else
            Toast.makeText(NewsDetails.this, "Internet service is not available", Toast.LENGTH_LONG).show();
    }
    public boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() != null)
            return (cm.getActiveNetworkInfo().isConnected() && cm.getActiveNetworkInfo().isAvailable());
        else
            return false;
    }
    public class WebViewClientImpl extends WebViewClient {

        private Activity activity = null;

        public WebViewClientImpl(Activity activity) {
            this.activity = activity;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            if (url.indexOf("journaldev.com") > -1) return false;

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            activity.startActivity(intent);
            return true;
        }
    }
}
