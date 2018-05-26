package com.example.pcsingh.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    final private int[] rainbow;
    final private String[] imageList;

    public MyAdapter(int[] rainbow, String[] imageList) {
        this.rainbow = rainbow;
        this.imageList = imageList;
    }

    public MyAdapter(int[] rainbow) {
        this.rainbow = rainbow;
        imageList = null;
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
        holder.setImageLink(imageList != null ? imageList[position] :
                "https://glowroad.com/faceview/b3g/cc/ae/imgs/nt/1526448186735_collection_poster.jpeg");
        holder.circleView.setColorFilter(rainbow[position]);
        holder.ribbonView.setColorFilter(rainbow[position]);
        holder.arrowView.setColorFilter(Color.parseColor("#ffffff"));
        ImageUtil.getInstance().loadImage(holder.getImageLink(), holder.listImage);
        initSecondLineDetails(holder);
    }

    protected void initSecondLineDetails(@NonNull MyViewHolder holder) {
        final String price = "Rs. 1083";
        final String completeMessage = String.format(Locale.US, holder.secondLineDetailsFormat, price);
        final SpannableString sb = new SpannableString(completeMessage);
        sb.setSpan(new StyleSpan(Typeface.BOLD), completeMessage.indexOf(price),
                completeMessage.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        holder.secondLineDetails.setText(sb, TextView.BufferType.SPANNABLE);
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
        @BindView(R.id.second_line_details)
        protected TextView secondLineDetails;
        @BindView(R.id.copy_to_my_shop)
        protected TextView copyToMyShop;

        @BindString(R.string.caption)
        protected String caption;
        @BindString(R.string.second_line_details)
        protected String secondLineDetailsFormat;

        protected String imageLink;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            copyToMyShop.setTextIsSelectable(true);
        }

        public void setImageLink(String imageLink) {
            this.imageLink = imageLink;
        }

        public String getImageLink() {
            return imageLink;
        }

        @OnClick(R.id.share)
        public void share(View view) {
            if (PermissionUtil.isStoragePermissionGranted((Activity) view.getContext(),
                    PermissionUtil.WRITE_EXTERNAL_STORAGE_REQUEST_CODE)) {

                if (listImage.getDrawable() != null &&
                        ((BitmapDrawable) listImage.getDrawable()).getBitmap() != null) {
                    final Bitmap bm = ((BitmapDrawable) listImage.getDrawable()).getBitmap();
                    ShareUtil.sendingImageWithCaption(view.getContext(), caption, bm);
                } else {
                    Toast.makeText(view.getContext(),
                            "Image isn't available, Please Try again later", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
