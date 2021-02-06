package com.example.whiterabbit.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.whiterabbit.R;
import com.example.whiterabbit.fragments.ProductListFragment;
import com.example.whiterabbit.modelclass.respose.ProductListResponse;
import com.example.whiterabbit.modelclass.respose.ProductResponse;


import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.RecyclerViewHolder> {


    private  int noOfItems = -1;
    Activity context;
    private View.OnClickListener mOnItemClickListener;
    //private ProductAdapter productResponse;
    private ArrayList<ProductResponse> productResponse = new ArrayList<>();


    public ProductAdapter(Activity context, int noOfItems) {
        this.context = context;
        this.noOfItems = noOfItems;

    }

    public ProductAdapter(Activity context) {
        this.context = context;


    }
    @NonNull
    @Override
    public ProductAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        view = LayoutInflater.from(context)
                .inflate(R.layout.product_list_item, parent,false);
        return new ProductAdapter.RecyclerViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.RecyclerViewHolder holder, int position) {
        ProductResponse productResponseItem = productResponse.get(position);

        holder.product_name.setText(productResponseItem.getProductName());
        holder.product_sub_name.setText(productResponseItem.getProductSubName());


        Glide.with(context)
                .load(productResponseItem.getUrl() )
                .centerInside()
                //.placeholder(ContextCompat.getDrawable(context,R.drawable.ic_home))
                //.apply(RequestOptions.bitmapTransform(new RoundedCorners(14)))
                .into(holder.product_image);

    }

    @Override
    public int getItemCount() {
        if(productResponse !=null && noOfItems == -1)
            return productResponse.size();
        else if(noOfItems > 0)
            return noOfItems;
        else
            return 0;


    }

    public void addAll(ProductListResponse productResponse) {
        this.productResponse.addAll(productResponse.getProductResponse());
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        ImageView product_image;
        TextView product_name, product_sub_name;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            product_image = itemView.findViewById(R.id.product_image);
            product_name = itemView.findViewById(R.id.product_name);
            product_sub_name = itemView.findViewById(R.id.product_sub_name);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);


        }
    }
}
