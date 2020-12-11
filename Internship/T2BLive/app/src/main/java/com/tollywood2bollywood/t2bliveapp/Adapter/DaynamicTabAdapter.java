package com.tollywood2bollywood.t2bliveapp.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;


import com.squareup.picasso.Picasso;
import com.tollywood2bollywood.t2bliveapp.APISET.Constant;
import com.tollywood2bollywood.t2bliveapp.CategoryTabFragment;
import com.tollywood2bollywood.t2bliveapp.HomeFragment;
import com.tollywood2bollywood.t2bliveapp.Other.TabModel;
import com.tollywood2bollywood.t2bliveapp.R;
import com.tollywood2bollywood.t2bliveapp.DaynamicTabModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JAI JALARAM on 05-02-2017.
 */

public class DaynamicTabAdapter extends BaseAdapter
{
    private Context context;
    List<TabModel> simpleExampleModelArrayList;
    private TabLayout tabLayout;

    public DaynamicTabAdapter(Context context, List<TabModel> simpleExampleModelArrayList,TabLayout tabLayout) {
        this.context = context;
        this.simpleExampleModelArrayList = simpleExampleModelArrayList;
        this.tabLayout = tabLayout;
    }
    static class ViewHolder {
        TextView text;
    }
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder holder;
        View convertView = view;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_tab, null);
            holder = new ViewHolder();
            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/MavenPro-Regular.ttf");
            holder.text = (TextView) convertView.findViewById(R.id.text);
            holder.text.setTypeface(custom_font);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        TextView text = holder.text;
        TabModel tabModel = simpleExampleModelArrayList.get(position);
        text.setText(tabModel.getName());
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabLayout.getTabAt(position).select();
            }
        });

        /*if(simpleExampleModel.getCategory_title()!= null){
            holder.cat1_news_heading.setText(simpleExampleModel.getCategory_title());
        }*/

        //Picasso.with(context).load(simpleExampleModel.getImage_url()).centerInside().into(imageView);


        return convertView;
    }

    @Override
    public int getCount() {
        return simpleExampleModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
