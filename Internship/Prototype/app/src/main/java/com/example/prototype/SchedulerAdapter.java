package com.example.prototype;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.List;

public class SchedulerAdapter extends ArrayAdapter<Scheduler> {

    private Context context;
    private List<Scheduler> schedulers;

    public SchedulerAdapter(Context context, List<Scheduler> schedulers){
        super(context, R.layout.scheduler_model,schedulers);
        this.context = context;
        this.schedulers = schedulers;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(R.layout.scheduler_model,parent,false);

        return convertView;
    }

}
