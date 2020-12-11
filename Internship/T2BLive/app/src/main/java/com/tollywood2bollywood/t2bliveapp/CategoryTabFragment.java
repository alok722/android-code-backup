package com.tollywood2bollywood.t2bliveapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tollywood2bollywood.t2bliveapp.APISET.Constant;
import com.tollywood2bollywood.t2bliveapp.Adapter.DaynamicTabAdapter;
import com.tollywood2bollywood.t2bliveapp.Other.SessionManager;
import com.tollywood2bollywood.t2bliveapp.Other.TabModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;

/**
 * Created by JAI JALARAM on 29-01-2017.
 */

public class CategoryTabFragment extends Fragment
{
    public CategoryTabFragment() {
    }
    String title;
    int page;
    View view;
    DaynamicTabAdapter daynamicTabAdapter;
    GridView gridView;
    ProgressBar progressBar;
    SessionManager sessionManager;
    List<TabModel> tabModelList;
    TextView nodata;
    public static CategoryTabFragment newInstance(int page, String title) {
        CategoryTabFragment simpleExampleFragment = new CategoryTabFragment();
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab_fragment, container, false);
        if(view != null) {
            sessionManager = new SessionManager(getContext());
            wedgetInit();
            //Log.d(" fata ",title);

            /*gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });*/
        }
        return view;
    }

    private void wedgetInit(){
        nodata = (TextView) view.findViewById(R.id.nodata);
        tabModelList = new ArrayList<TabModel>();
        gridView = (GridView) view.findViewById(R.id.gridView);
        gridView.setNumColumns(3);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        setAllTab(sessionManager.getTab());
    }
    public String method(String str) {
        if (str != null && str.length() > 0 && str.charAt(str.length()-1)=='x') {
            str = str.substring(0, str.length()-1);
        }
        return str;
    }
    private void setAllTab(String json){
        try {
            JSONObject j = new JSONObject(json);
            if(j.getString("status").equals("ok")) {
                JSONArray jsonArray = new JSONArray(j.getString("categories"));
                if(jsonArray.length() > 0){
                    for(int i = 0 ; i < jsonArray.length(); i++){
                        TabModel tabModel = new TabModel(jsonArray.getJSONObject(i).getString("id"),jsonArray.getJSONObject(i).getString("title"));
                        tabModelList.add(tabModel);
                    }
                    HomeFragment parentFrag = ((HomeFragment)CategoryTabFragment.this.getParentFragment());
                    daynamicTabAdapter = new DaynamicTabAdapter(getContext(),tabModelList,parentFrag.getmTabLayout());
                    gridView.setAdapter(daynamicTabAdapter);
                }else{
                    nodata.setText("No data found");
                }
            }else{

            }
        }catch(Exception e){
            Log.e("Exception = ",e.toString());
        }
    }
}
