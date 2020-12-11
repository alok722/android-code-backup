package com.example.imalok.griddemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class GridAdapter extends BaseAdapter {

    private int icons[];

    private String letters[];
    private Context context;

    private LayoutInflater inflater;

    public GridAdapter(Context context, int icons[], String letters[]){

        this.context=context;
        this.icons=icons;
        this.letters=letters;
    }

    public GridAdapter(){

    }

    @Override
    public int getCount() {
        return letters.length;
    }

    @Override
    public Object getItem(int position) {
        return letters[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View romance_grid = convertView;

        if(convertView==null){
            inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            romance_grid =inflater.inflate(R.layout.custom_layout,null);
        }

        ImageView icon=(ImageView) romance_grid.findViewById(R.id.romance_image);
        TextView letter=(TextView)romance_grid.findViewById(R.id.romance_letters);

        icon.setImageResource(icons[position]);
        letter.setText(letters[position]);



        return romance_grid;

    }
}
