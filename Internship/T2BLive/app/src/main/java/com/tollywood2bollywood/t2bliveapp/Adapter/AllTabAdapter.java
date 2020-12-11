package com.tollywood2bollywood.t2bliveapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.tollywood2bollywood.t2bliveapp.APISET.Constant;
import com.tollywood2bollywood.t2bliveapp.DaynamicTabModel;
import com.tollywood2bollywood.t2bliveapp.NewsDetails;
import com.tollywood2bollywood.t2bliveapp.R;
import org.json.JSONArray;
import java.util.List;

/**
 * Created by Lucson-4 on 12-04-2018.
 */

public class AllTabAdapter extends RecyclerView.Adapter<AllTabAdapter.MyViewHolder> {

    private Context context;
    private List<DaynamicTabModel> daynamicTabModelList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView cat1_news_heading;
        public ImageView cat1_news_image;
        LinearLayout linearLayout;

        public MyViewHolder(View view) {
            super(view);
            cat1_news_heading = (TextView) view.findViewById(R.id.cat1_news_heading);
            cat1_news_image = (ImageView) view.findViewById(R.id.cat1_news_image);
            linearLayout = (LinearLayout) view.findViewById(R.id.linear);
        }
    }


    public AllTabAdapter(Context context , List<DaynamicTabModel> daynamicTabModelList) {
        this.context = context;
        this.daynamicTabModelList = daynamicTabModelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.newslistdesign, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        try{
            final DaynamicTabModel daynamicTabModel = daynamicTabModelList.get(position);
            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/MavenPro-Regular.ttf");
            holder.cat1_news_heading.setText(daynamicTabModel.getTitle());
            holder.cat1_news_heading.setTypeface(custom_font);
            final JSONArray jsonArray1 = new JSONArray(daynamicTabModel.getAttachments());
            Picasso.with(context).load(jsonArray1.getJSONObject(0).getString("url")).memoryPolicy(MemoryPolicy.NO_CACHE).fit().into(holder.cat1_news_image);
            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        Intent intent = new Intent(context,NewsDetails.class);
                        intent.putExtra("img_id",jsonArray1.getJSONObject(0).getString("id"));
                        intent.putExtra("Heading",daynamicTabModel.getTitle());
                        intent.putExtra("id",daynamicTabModel.getId());
                        context.startActivity(intent);
                    }catch (Exception e){
                        Log.e("Exception ",""+e.getMessage());
                    }
                }
            });
        }catch (Exception e){
            Log.e("Exception ",""+e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return daynamicTabModelList.size();
    }
}
