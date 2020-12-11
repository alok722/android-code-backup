package imalok.example.com.coupon;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class VerticalAdapter extends RecyclerView.Adapter<VerticalAdapter.RecyclerHolder> {

    private String[] category;
    private int[] cat_img;
    private Context context;
    Dialog myDialog;

    public VerticalAdapter(String[] category, int[] cat_img,Context context){
        this.context=context;
        this.category = category;
        this.cat_img = cat_img;

    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.most_used_recyclerview,parent,false);

        final RecyclerHolder vHolder = new RecyclerHolder(view);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerHolder holder, final int position) {

        String category_name = category[position];
        int category_image = cat_img[position];
        holder.category_name.setText(category_name);
        holder.category_image.setImageResource(category_image);

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                final Intent intent;
//
//                switch (holder.getAdapterPosition()){
//                    case 0:
//                        intent = new Intent(context,FitnessActivity.class);
//                        break;
//                    case 1:
//                        intent = new Intent(context,MoneyActivity.class);
//                        break;
//                    case 2:
//                        intent = new Intent(context,WeightlossActivity.class);
//                        break;
//                    case 3:
//                        intent = new Intent(context,Bounceback.class);
//                        break;
//                    case 4:
//                        intent = new Intent(context,BackPainReliefActivity.class);
//                        break;
//                    default:
//                        intent =  new Intent(context, MainActivity.class);
//                        break;
//
//                }
//
//                context.startActivity(intent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return category.length;
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder{
        private LinearLayout mLayout;
        TextView category_name;
        ImageView category_image;
        public RecyclerHolder(View itemView) {
            super(itemView);
            mLayout = (LinearLayout)itemView.findViewById(R.id.most_used_list);
            category_image = (ImageView) itemView.findViewById(R.id.most_used_image);
            category_name = (TextView) itemView.findViewById(R.id.most_used_text);
        }

    }



}