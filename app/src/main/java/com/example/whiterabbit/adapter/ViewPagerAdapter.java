package com.example.whiterabbit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.whiterabbit.R;
import com.example.whiterabbit.modelclass.respose.Banner;
import com.example.whiterabbit.modelclass.respose.BannerResponse;
import com.example.whiterabbit.modelclass.respose.SliderResponse;
import com.example.whiterabbit.modelclass.respose.Sliderlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {

    private final Context context;
    private boolean viewpager;
    //private final SliderResponse sliderResponse;
    private List<Sliderlist> sliderList = new ArrayList<>();
    private View.OnClickListener mOnItemClickListener;
    private List<Banner> bannerResponse;

    public ViewPagerAdapter(Context context) {
        this.context = context;

    }

    public ViewPagerAdapter(Context context, SliderResponse sliderResponseItems, boolean viewpager) {
        this.context = context;
        this.sliderList = sliderResponseItems.getSliderlist();
        this.viewpager = viewpager;
    }

    public ViewPagerAdapter(Context context, boolean viewpager) {
        this.context = context;
        //this.sliderList = sliderResponseItems.getSliderlist();
        this.viewpager = viewpager;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        view = LayoutInflater.from(context)
                .inflate(R.layout.viewpager_item, parent,false);
        return new ViewPagerAdapter.ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(viewpager)
            Glide.with(context)
                .load(sliderList.get(position).getUrl() )
                .centerCrop()
                .placeholder(ContextCompat.getDrawable(context,R.drawable.ic_launcher_background))
                //.apply(RequestOptions.bitmapTransform(new RoundedCorners(14)))
                .into(holder.slider_image);
        else
            Glide.with(context)
                    .load(bannerResponse.get(position).getUrl() )
                    .centerCrop()
                    .placeholder(ContextCompat.getDrawable(context,R.drawable.ic_launcher_background))
                    //.apply(RequestOptions.bitmapTransform(new RoundedCorners(14)))
                    .into(holder.slider_image);

    }

    @Override
    public int getItemCount() {
        if(viewpager)
            return sliderList.size();
        else
            return bannerResponse.size();
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setData(BannerResponse bannerResponse) {
        this.bannerResponse = bannerResponse.getBannerList();
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if(viewpager){
            return 0;
        }else
            return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView slider_image;

        ViewHolder(View itemView) {
            super(itemView);
            slider_image = itemView.findViewById(R.id.slider_image);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }

}