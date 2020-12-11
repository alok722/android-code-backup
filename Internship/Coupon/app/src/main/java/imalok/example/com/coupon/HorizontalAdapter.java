package imalok.example.com.coupon;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.HorizontalViewHolder>{

    private int[] stores_items;

    public HorizontalAdapter(int[] stores_items) {
        this.stores_items = stores_items;
    }

    @NonNull
    @Override
    public HorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.popular_stores_recyclerview,parent,false);
        return new HorizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalViewHolder holder, int position) {

        holder.img.setImageResource(stores_items[position]);

    }

    @Override
    public int getItemCount() {
        return stores_items.length;
    }

    public class HorizontalViewHolder extends RecyclerView.ViewHolder{

        ImageView img;

        public HorizontalViewHolder(View itemView){

            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.popular_stores_item);

        }

    }

}
