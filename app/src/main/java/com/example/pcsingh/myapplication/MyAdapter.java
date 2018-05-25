package com.example.pcsingh.myapplication;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private int[] rainbow;

    public MyAdapter(int[] rainbow) {
        this.rainbow = rainbow;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.circleView.setColorFilter(rainbow[position]);
        holder.ribbonView.setColorFilter(rainbow[position]);
        holder.arrowView.setColorFilter(Color.parseColor("#ffffff"));
    }

    @Override
    public int getItemCount() {
        return rainbow.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ribbon_view)
        protected ImageView ribbonView;
        @BindView(R.id.circle_view)
        protected ImageView circleView;
        @BindView(R.id.arrow_view)
        protected ImageView arrowView;
        @BindView(R.id.list_image)
        protected ImageView listImage;
        @BindView(R.id.share)
        protected TextView share;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
