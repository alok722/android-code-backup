package com.tollywood2bollywood.t2bliveapp;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.tollywood2bollywood.t2bliveapp.APISET.Constant;
import com.tollywood2bollywood.t2bliveapp.Adapter.AllTabAdapter;
import com.tollywood2bollywood.t2bliveapp.Other.Popup;
import com.tollywood2bollywood.t2bliveapp.Other.SessionManager;
import com.tollywood2bollywood.t2bliveapp.Other.ZigzagGridRecyclerViewAdapter;
import com.tollywood2bollywood.t2bliveapp.Other.ZigzagImage;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;

/**
 * Created by JAI JALARAM on 29-01-2017.
 */

public class AllTabsFragment extends Fragment{
    View view;
    String title;
    boolean getData = false;
    int page,pagess=1;
    ProgressBar progressBar;
    RecyclerView cat1_newsList;
    AllTabAdapter mAdapter;
    ArrayList<DaynamicTabModel> daynamicTabModelList;
    int count = 12,pages;
    ZigzagGridRecyclerViewAdapter zigzagGridRecyclerViewAdapter;
    Popup popup;

    TextView nodata;
    SessionManager sessionManager;
    public static AllTabsFragment newInstance(int page, String title) {
        AllTabsFragment simpleExampleFragment = new AllTabsFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        simpleExampleFragment.setArguments(args);
        return simpleExampleFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }
    private SwipeRefreshLayout swipeRefreshLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_category_one, container, false);
        popup = new Popup(getContext());
        daynamicTabModelList = new ArrayList<DaynamicTabModel>();
        sessionManager = new SessionManager(getActivity());
        wedgetInit();
        return view;
    }
    private void wedgetInit(){
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        nodata = (TextView) view.findViewById(R.id.nodata);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        cat1_newsList = (RecyclerView)view.findViewById(R.id.cat1_recyclerView_layout);
        cat1_newsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        cat1_newsList.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        zigzagGridRecyclerViewAdapter = new ZigzagGridRecyclerViewAdapter(getActivity(), daynamicTabModelList);
       // mAdapter = new AllTabAdapter(getActivity(), daynamicTabModelList);
        cat1_newsList.setAdapter(zigzagGridRecyclerViewAdapter);
        Log.e(" Hai  : ","111: "+sessionManager.getTabName(title));
        getSetData(Constant.get_category_posts + title + "&count=" + count + "&page=" + pagess);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                if(!getData) {
                    Collections.shuffle(daynamicTabModelList, new Random(System.currentTimeMillis()));
                    getSetData(Constant.get_category_posts + title + "&count=" + count + "&page=" + pagess);
                    swipeRefreshLayout.setRefreshing(true);
                }else{
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }


    public void getSetData(final String URLLL){
        AsyncTask<String, String, String> waitForCompletion = new AsyncTask<String, String, String>() 	{
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                swipeRefreshLayout.setRefreshing(true);
            }
            @Override
            protected String doInBackground(String... params) {
                String json="";
                try {
                    HttpClient client = HttpClientBuilder.create().build();
                    Log.e("URLLL = ",""+URLLL);
                    HttpGet httpPost = new HttpGet(URLLL);
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
            };


            @Override
            protected void onPostExecute(String json) {
                //sessionManager.setTabName(title,json);
                setList(json);
            }
        };
        if (isInternetAvailable())
            waitForCompletion.execute();
        else
            Toast.makeText(getContext(), "Internet service is not available", Toast.LENGTH_LONG).show();
    }
    private void setList(String json){
        swipeRefreshLayout.setRefreshing(false);
        try {

            JSONObject jsonObject1 = new JSONObject(json);
            pages = jsonObject1.getInt("pages");
            Log.e("pages 0 = ",""+pages);
            JSONObject jsonObject11 = new JSONObject(jsonObject1.getString("category"));
            //post_count = jsonObject11.getInt("pages");


                if (jsonObject1.getString("status").equals("ok"))
                {
                    JSONArray jsonArray = new JSONArray(jsonObject1.getString("posts"));
                    if (jsonArray.length() > 0)
                    {
                        for (int i = 0; i < jsonArray.length(); i++)
                        {
                            DaynamicTabModel daynamicTabModel = new DaynamicTabModel(
                                    jsonArray.getJSONObject(i).getString("id"),
                                    jsonArray.getJSONObject(i).getString("type"),
                                    jsonArray.getJSONObject(i).getString("slug"),
                                    jsonArray.getJSONObject(i).getString("url"),
                                    jsonArray.getJSONObject(i).getString("status"),
                                    jsonArray.getJSONObject(i).getString("title"),
                                    jsonArray.getJSONObject(i).getString("title_plain"),
                                    jsonArray.getJSONObject(i).getString("content"),
                                    jsonArray.getJSONObject(i).getString("excerpt"),
                                    jsonArray.getJSONObject(i).getString("date"),
                                    jsonArray.getJSONObject(i).getString("modified"),
                                    jsonArray.getJSONObject(i).getString("categories"),
                                    jsonArray.getJSONObject(i).getString("tags"),
                                    jsonArray.getJSONObject(i).getString("author"),
                                    jsonArray.getJSONObject(i).getString("comments"),
                                    jsonArray.getJSONObject(i).getString("attachments"),
                                    jsonArray.getJSONObject(i).getString("comment_count"),
                                    jsonArray.getJSONObject(i).getString("comment_status"),
                                    jsonArray.getJSONObject(i).getString("thumbnail"),
                                    jsonArray.getJSONObject(i).getString("custom_fields"),
                                    jsonArray.getJSONObject(i).getString("thumbnail_size"),
                                    jsonArray.getJSONObject(i).getString("thumbnail_images"));
                            daynamicTabModelList.add(daynamicTabModel);
                        }
                        zigzagGridRecyclerViewAdapter.notifyDataSetChanged();

                    }
                    if(pages == pagess){
                        getData = true;
                    }else if(pages > pagess) {
                        pagess++;
                    }
                }
                else {
                    nodata.setText("");
                    Toast.makeText(getActivity(), "No Data Found", Toast.LENGTH_LONG).show();
                }


        }catch (Exception e){
            Log.e("Exception = ",e.toString());
        }
    }
    public boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() != null)
            return (cm.getActiveNetworkInfo().isConnected() && cm.getActiveNetworkInfo().isAvailable());
        else
            return false;
    }

    private void changeSearchViewTextColor(View view) {
        if (view != null) {
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(Color.WHITE);
                return;
            } else if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    changeSearchViewTextColor(viewGroup.getChildAt(i));
                }
            }
        }
    }

    /*@Override
    public void onRefresh() {
        if(getData){
            swipeRefreshLayout.setRefreshing(false);

        } else {
            Log.e("Hello  Tr: ", "" + count+"   "+page);
            swipeRefreshLayout.setRefreshing(true);
            getSetData(Constant.get_category_posts + title + "&count=" + count+"&page="+page);
        }
    }*/
}
