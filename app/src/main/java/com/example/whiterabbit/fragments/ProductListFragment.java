package com.example.whiterabbit.fragments;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.whiterabbit.R;
import com.example.whiterabbit.adapter.ProductAdapter;
import com.example.whiterabbit.modelclass.respose.ProductListResponse;
import com.example.whiterabbit.viewmodel.ProductListViewModel;

public class ProductListFragment extends Fragment {

    private ProductListViewModel mViewModel;
    private ProductListResponse productResponse;

    public static ProductListFragment newInstance() {
        return new ProductListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() !=null){
            productResponse = ProductListFragmentArgs.fromBundle(getArguments()).getProductList();
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.product_list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProductListViewModel.class);
        final ProductAdapter productAdapter = new ProductAdapter(getActivity()  );
        RecyclerView productList = view.findViewById(R.id.product_list);
        productList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        productList.setAdapter(productAdapter);
        productAdapter.addAll(productResponse);
    }
}