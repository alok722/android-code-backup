package com.tollywood2bollywood.t2bliveapp.Other;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tollywood2bollywood.t2bliveapp.DaynamicTabModel;
import com.tollywood2bollywood.t2bliveapp.NewsDetails;
import com.tollywood2bollywood.t2bliveapp.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

public class ZigzagGridRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int IMAGE_VIEW_EVEN_TYPE = 0;
    private static final int IMAGE_VIEW_ODD_TYPE = 1;

    private Context context;
    private List<DaynamicTabModel> daynamicTabModelList;
    private int backgroundColourResId;
    private int placeholderDrawableResId;

    /*public interface ZigzagListOnClickListener {
        void onZigzagImageClicked(int position, ZigzagImage zigzagImage);
    }*/

    public ZigzagGridRecyclerViewAdapter(Context context, List<DaynamicTabModel> daynamicTabModelList) {
        this.context = context;
        this.daynamicTabModelList = daynamicTabModelList;
        backgroundColourResId = context.getResources().getColor(R.color.colorWhite);
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? IMAGE_VIEW_EVEN_TYPE : IMAGE_VIEW_ODD_TYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == IMAGE_VIEW_ODD_TYPE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newslistdesign, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newslistdesign_2, parent, false);
        }
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        final ImageViewHolder imageViewHolder = (ImageViewHolder) viewHolder;

        if (backgroundColourResId != 0) {
            imageViewHolder.linearLayout.setBackgroundColor(backgroundColourResId);
        }

        if(IMAGE_VIEW_EVEN_TYPE == 0) {
            //load first image
            int pos1 = position;
            final DaynamicTabModel zigzagImage1 = daynamicTabModelList.get(position);
            imageViewHolder.cat1_news_heading.setText(zigzagImage1.getTitle());
            String imageUrl1 = "";
            try {
                final JSONArray jsonArray1 = new JSONArray(zigzagImage1.getAttachments());
                imageUrl1 = jsonArray1.getJSONObject(0).getString("url");
                if (imageUrl1 != null) {
                    loadImageResourceUrl(imageUrl1, imageViewHolder.cat1_news_image);
                }
                imageViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try
                        {
                            Log.e("Check : "," "+jsonArray1.getJSONObject(0).getString("url"));
                            Intent intent = new Intent(context,NewsDetails.class);
                            intent.putExtra("img_id",jsonArray1.getJSONObject(0).getString("id"));
                            intent.putExtra("img_url",jsonArray1.getJSONObject(0).getString("url"));
                            intent.putExtra("Heading",zigzagImage1.getTitle());
                            intent.putExtra("id",zigzagImage1.getId());
                            context.startActivity(intent);
                        }catch (Exception e){
                            Log.e("Exception ",""+e.getMessage());
                        }
                        //zigzagListOnClickListener.onZigzagImageClicked(position, zigzagImage1);
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        if(IMAGE_VIEW_ODD_TYPE == 1) {

            //load second image if need as we might have odd number in list
            int secondImagePos = position;
            if (secondImagePos < daynamicTabModelList.size()) {
                final DaynamicTabModel zigzagImage2 = daynamicTabModelList.get(secondImagePos);
                imageViewHolder.cat1_news_heading.setText(zigzagImage2.getTitle());
                if (zigzagImage2 != null) {
                    String imageUrl2 = "";
                    try {
                        final JSONArray jsonArray1 = new JSONArray(zigzagImage2.getAttachments());
                        imageUrl2 = jsonArray1.getJSONObject(0).getString("url");
                        if (imageUrl2 != null) {

                            loadImageResourceUrl(imageUrl2, imageViewHolder.cat1_news_image);
                        }
                        imageViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                try{
                                    Log.e("Check : "," "+jsonArray1.getJSONObject(0).getString("url"));
                                    Intent intent = new Intent(context,NewsDetails.class);
                                    intent.putExtra("img_id",jsonArray1.getJSONObject(0).getString("id"));
                                    intent.putExtra("img_url",jsonArray1.getJSONObject(0).getString("url"));
                                    intent.putExtra("Heading",zigzagImage2.getTitle());
                                    intent.putExtra("id",zigzagImage2.getId());
                                    context.startActivity(intent);
                                }catch (Exception e){
                                    Log.e("Exception ",""+e.getMessage());
                                }
                                //zigzagListOnClickListener.onZigzagImageClicked(position, zigzagImage2);
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            } else {
                imageViewHolder.linearLayout.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        float size = daynamicTabModelList.size();
        int count = Math.round(size / 2);
        return daynamicTabModelList.size();
    }

    private void loadImageResource(int imageResource, ImageView obliqueView) {
        if (placeholderDrawableResId==0) {
            Picasso.with(context).load(imageResource)
                    .into(obliqueView);
        } else {
            Picasso.with(context).load(imageResource)
                    .placeholder(placeholderDrawableResId)
                    .into(obliqueView);
        }
    }

    private void loadImageResourceUrl(String imageUrlString, ImageView obliqueView) {
        if (placeholderDrawableResId==0) {
            Picasso.with(context).load(imageUrlString)
                    .into(obliqueView);
        } else {
            Picasso.with(context).load(imageUrlString)
                    .placeholder(placeholderDrawableResId)
                    .into(obliqueView);
        }
    }

    public void setData(List<DaynamicTabModel> daynamicTabModelList) {
        this.daynamicTabModelList = daynamicTabModelList;
        notifyDataSetChanged();
    }

    public List<DaynamicTabModel> getData() {
        return daynamicTabModelList;
    }

    /*public ZigzagListOnClickListener getZigzagListOnClickListener() {
        return zigzagListOnClickListener;
    }

    public void setZigzagListOnClickListener(ZigzagListOnClickListener zigzagListOnClickListener) {
        this.zigzagListOnClickListener = zigzagListOnClickListener;
    }*/

    public void setBackgroundColourResId(int backgroundColourResId) {
        this.backgroundColourResId = backgroundColourResId;
    }

    public void setPlaceholderDrawableResId(int placeholderDrawableResId) {
        this.placeholderDrawableResId = placeholderDrawableResId;
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView cat1_news_heading;
        public ImageView cat1_news_image;
        LinearLayout linearLayout;

        public ImageViewHolder(View v) {
            super(v);

            cat1_news_heading = (TextView) v.findViewById(R.id.cat1_news_heading);
            cat1_news_image = (ImageView) v.findViewById(R.id.cat1_news_image);
            linearLayout = (LinearLayout) v.findViewById(R.id.linear);
        }
    }

}

