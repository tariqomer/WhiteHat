package com.example.whiterabbit.fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.whiterabbit.R;
import com.example.whiterabbit.adapter.ProductAdapter;
import com.example.whiterabbit.adapter.ViewPagerAdapter;
import com.example.whiterabbit.modelclass.respose.BannerResponse;
import com.example.whiterabbit.modelclass.respose.ProductListResponse;
import com.example.whiterabbit.modelclass.respose.ProductResponse;
import com.example.whiterabbit.modelclass.respose.SliderResponse;
import com.example.whiterabbit.viewmodel.HomeViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.IOException;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    private ViewPager2 viewPager;
    private TabLayout indicator;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        viewPager = view.findViewById(R.id.pager);
        indicator = view.findViewById(R.id.indicator);



        try {
            mViewModel.getSliderList(getActivity());
        } catch (IOException e) {
            e.printStackTrace();
        }

        mViewModel.getSliderListResponse().observe(getViewLifecycleOwner(), new Observer<SliderResponse>() {
            @Override
            public void onChanged(SliderResponse sliderResponseItems) {
                viewPager.setAdapter(new ViewPagerAdapter(getActivity(), sliderResponseItems, true));
                new TabLayoutMediator(indicator, viewPager,
                        new TabLayoutMediator.TabConfigurationStrategy() {
                            @Override public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                                //tab.setText("Tab " + (position + 1));
                            }
                        }).attach();
            }
        });



        final ProductAdapter productAdapter = new ProductAdapter(getActivity() , 6);
        RecyclerView productList = view.findViewById(R.id.product_list);
        productList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        productList.setAdapter(productAdapter);
        try {
            mViewModel.getProductList(getActivity());
        } catch (IOException e) {
            e.printStackTrace();
        }

        mViewModel.getProductListResponse().observe(getViewLifecycleOwner(), new Observer<ProductListResponse>() {
            @Override
            public void onChanged(ProductListResponse productResponse) {
                productAdapter.addAll(productResponse);
            }
        });

        final ViewPagerAdapter bannerAdapter = new ViewPagerAdapter(getActivity(), false);
        RecyclerView bannerList = view.findViewById(R.id.slider_list);
        bannerList.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        bannerList.setAdapter(bannerAdapter);
        try {
            mViewModel.getBannerList(getActivity());
        } catch (IOException e) {
            e.printStackTrace();
        }

        mViewModel.getBannerListResponse().observe(getViewLifecycleOwner(), new Observer<BannerResponse>() {
            @Override
            public void onChanged(BannerResponse bannerResponse) {
                bannerAdapter.setData(bannerResponse);
            }
        });


        Button view_all = view.findViewById(R.id.view_all);
        view_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(HomeFragmentDirections.actionHomeFragmentToProductListFragment(mViewModel.getProductResponse()));
            }
        });

    }
}