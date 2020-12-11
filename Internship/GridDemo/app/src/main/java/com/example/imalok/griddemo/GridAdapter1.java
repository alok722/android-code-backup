package com.example.imalok.griddemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class GridAdapter1 extends BaseAdapter {

    private int icons1[];

    private String letters1[];
    private Context context1;

    private LayoutInflater inflater;

    public GridAdapter1(Context context, int icons[], String letters[]){

        this.context1=context;
        this.icons1=icons;
        this.letters1=letters;
    }

    public GridAdapter1(){

    }

    @Override
    public int getCount() {
        return letters1.length;
    }

    @Override
    public Object getItem(int position) {
        return letters1[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View crime_grid = convertView;

        if(convertView==null){
            inflater=(LayoutInflater)context1.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            crime_grid =inflater.inflate(R.layout.custom_layout,null);
        }

        ImageView icon=(ImageView) crime_grid.findViewById(R.id.crime_image);
        TextView letter=(TextView)crime_grid.findViewById(R.id.crime_letter);

        icon.setImageResource(icons1[position]);
        letter.setText(letters1[position]);



        return crime_grid;

    }
}
