package com.example.prototype;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PlannerAdapter extends ArrayAdapter<Planner> {


    private Context context;
    private List<Planner> planners;

    public PlannerAdapter(Context context, List<Planner> planners){
        super(context, R.layout.planner_model,planners);
        this.context = context;
        this.planners = planners;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(R.layout.planner_model,parent,false);

        Planner planner = planners.get(position);
        TextView textView = convertView.findViewById(R.id.planner_title_textview);
        textView.setText(planner.getTitle());
        TextView textView1 = convertView.findViewById(R.id.planner_detail_textview);
        textView1.setText(planner.getDetails());

        return convertView;
    }
}
