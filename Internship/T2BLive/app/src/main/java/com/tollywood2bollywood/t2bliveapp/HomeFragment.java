package com.tollywood2bollywood.t2bliveapp;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.tollywood2bollywood.t2bliveapp.APISET.Constant;
import com.tollywood2bollywood.t2bliveapp.Adapter.CategoryAdapter;
import com.tollywood2bollywood.t2bliveapp.Other.Popup;
import com.tollywood2bollywood.t2bliveapp.Other.SessionManager;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;

public class HomeFragment extends Fragment {
    private ViewPager mViewPager;
    public TabLayout mTabLayout;

    public TabLayout getmTabLayout() {
        return mTabLayout;
    }

    public void setmTabLayout(TabLayout mTabLayout) {
        this.mTabLayout = mTabLayout;
    }

    SessionManager sessionManager;

    public HomeFragment() {
        // Required empty public constructor
    }
    ArrayList<String> catId;
    ArrayList<String> catName;
    Popup popup;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        sessionManager = new SessionManager(getActivity());
        popup = new Popup(getContext());
        //Adapting ViewPager for Tabs
        mViewPager = (ViewPager)view.findViewById(R.id.tab_pager);
        catId = new ArrayList<String>();
        catName = new ArrayList<String>();
         // mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
        //mViewPager.setAdapter(mSectionsPagerAdapter);

        //Syncing TabLayout with ViewPager
        mTabLayout = (TabLayout)view.findViewById(R.id.main_page_tab);
        //mTabLayout.setupWithViewPager(mViewPager);
        if(sessionManager.getTab().equals("") || sessionManager.getTab().equals("null")) {
            getList(Constant.get_category_index);
        }
        else{
            setAllTab(sessionManager.getTab());
        }
        return view;
    }
    public void setTab(int position){
        mTabLayout.getTabAt(position);
    }

    private void setTabLayout(JSONArray jsonArray){
        try{
            for(int i = 0; i < jsonArray.length(); i++){
                catId.add(jsonArray.getJSONObject(i).getString("id"));
                catName.add(jsonArray.getJSONObject(i).getString("title"));
                jsonArray.getJSONObject(i).getString("slug");
                jsonArray.getJSONObject(i).getString("description");
                jsonArray.getJSONObject(i).getString("parent");
                jsonArray.getJSONObject(i).getString("post_count");
            }
            catId.add("000");
            catName.add("Channel");
            catId.add("00");
            catName.add("Add");
            for(String str : catName){
                mTabLayout.addTab(mTabLayout.newTab().setCustomView(getTabView(mTabLayout,getContext(),str)));
            }
            Log.e("Heellllll   count = ",""+mTabLayout.getTabCount() );
           CategoryAdapter categoryAdapter = new CategoryAdapter(getChildFragmentManager(), mTabLayout.getTabCount(),catId,catId.size());
            mViewPager.setAdapter(categoryAdapter);


            mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
            mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    mViewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {  }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {  }
            });
        }catch (Exception e){
            Log.e("Exception : ",""+e.getMessage());
        }
    }

    public View getTabView(TabLayout tabLayout, Context context, String str) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.tab_icons, tabLayout, false);
        TextView title = (TextView) view.findViewById(R.id.title);
        ImageView imgTitle = (ImageView) view.findViewById(R.id.plus);
        if(str.equals("Add")){
            title.setVisibility(View.GONE);
        }else{
            imgTitle.setVisibility(View.GONE);
        }
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/MavenPro-Regular.ttf");
        title.setTypeface(tf);
        ViewGroup layout = (ViewGroup) view.findViewById(R.id.layout);

        title.setText(str);
        return view;
    }

    private void getList(String... s) {
        final String str1 = s[0];
        AsyncTask<String,String,String> asyncTask = new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                popup.showDialog();
            }

            @Override
            protected String doInBackground(String... strings) {
                String json="";
                try {
                    HttpClient client = HttpClientBuilder.create().build();
                    HttpPost httpPost = new HttpPost(str1);

                    HttpResponse httpResponse = client.execute(httpPost);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(),"utf-8"));
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
            }

            @Override
            protected void onPostExecute(String json) {
                Log.e("json : ",""+json);
                popup.hideDialog();
                sessionManager.setTab(json);
                setAllTab(json);


            }
        };
        if(isOnline())
            asyncTask.execute();
        else
            checkInternet("No internet connection!");
    }
    private void setAllTab(String json){
        try {
            JSONObject j = new JSONObject(json);
            //Toast.makeText(AddProductActivity.this, j.getString("message"),Toast.LENGTH_LONG).show();
            if(j.getString("status").equals("ok")) {
                JSONArray jsonArray = new JSONArray(j.getString("categories"));
                if(jsonArray.length() > 0){

                    setTabLayout(jsonArray);
                }
            }else{

            }
        }catch(Exception e){
            Log.e("Exception = ",e.toString());
        }
    }
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    public void checkInternet(String msg) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.simple_message_dailog);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView textMsg = (TextView) dialog.findViewById(R.id.textMsg);
        textMsg.setText(msg);
        TextView ok = (TextView) dialog.findViewById(R.id.ok);
        ok.setText("Retry");
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sessionManager.getTab().equals("") || sessionManager.getTab().equals("null")) {
                    getList(Constant.get_category_index);
                }
                else{
                    setAllTab(sessionManager.getTab());
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
