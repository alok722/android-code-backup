package com.tollywood2bollywood.t2bliveapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


//Single RecyclerViewAdapter for NewsList in Different Category

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.RecycleHolder>{

    private String[] mHeading;
    private int[] mImage;
    private Context context;

    public NewsListAdapter(String[] heading, int[] image,Context context){
        this.mHeading = heading;
        this.mImage = image;
        this.context=context;
    }

    @Override
    public RecycleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.newslistdesign,parent,false);
        return new RecycleHolder(view,context);
    }

    @Override
    public void onBindViewHolder(RecycleHolder holder, int position) {
        final String head = mHeading[position];
        final int img = mImage[position];
        holder.heading_news.setText(head);
        holder.heading_image.setImageResource(img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            Intent intent;
            @Override
            public void onClick(View v) {
                intent = new Intent(context,NewsDetails.class);
                intent.putExtra("img_id",img);
                intent.putExtra("Heading",head);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mHeading.length;
    }

    public class RecycleHolder extends RecyclerView.ViewHolder {

        ImageView heading_image;
        TextView heading_news;

        public RecycleHolder(View itemView,Context context) {
            super(itemView);
            heading_image = (ImageView) itemView.findViewById(R.id.cat1_news_image);
            heading_news = (TextView) itemView.findViewById(R.id.cat1_news_heading);
        }
    }

}
