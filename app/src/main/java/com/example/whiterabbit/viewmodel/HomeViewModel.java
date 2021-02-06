package com.example.whiterabbit.viewmodel;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.whiterabbit.R;
import com.example.whiterabbit.modelclass.respose.BannerResponse;
import com.example.whiterabbit.modelclass.respose.ProductListResponse;
import com.example.whiterabbit.modelclass.respose.ProductResponse;
import com.example.whiterabbit.modelclass.respose.SliderResponse;
import com.example.whiterabbit.utils.CommonUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<SliderResponse> sliderList = new MutableLiveData<>();
    private ProductListResponse productResponse;

    public MutableLiveData<SliderResponse> getSliderListResponse() {
        return sliderList;
    }

    public void getSliderList(FragmentActivity activity) throws IOException {
        String carouselString = CommonUtils.getStringfromRaw(activity, R.raw.slider);

        Type type = new TypeToken<SliderResponse>(){}.getType();
        SliderResponse corouselResponse = new Gson().fromJson(carouselString, type);

        sliderList.postValue(corouselResponse);
    }


    private MutableLiveData<ProductListResponse> productList = new MutableLiveData<>();
    public MutableLiveData<ProductListResponse> getProductListResponse() {
        return productList;
    }
    public void getProductList(FragmentActivity activity) throws IOException {
        String productString = CommonUtils.getStringfromRaw(activity, R.raw.product);

        Type type = new TypeToken<ProductListResponse>(){}.getType();
        ProductListResponse productResponse = new Gson().fromJson(productString, type);
        setProductList(productResponse);
        productList.postValue(productResponse);
    }

    private void setProductList(ProductListResponse productResponse) {
        this.productResponse = productResponse;
    }

    public ProductListResponse getProductResponse() {
        return productResponse;
    }

    private MutableLiveData<BannerResponse> bannerList = new MutableLiveData<>();
    public MutableLiveData<BannerResponse> getBannerListResponse() {
        return bannerList;
    }
    public void getBannerList(FragmentActivity activity) throws IOException {
        String bannerString = CommonUtils.getStringfromRaw(activity, R.raw.banner);

        Type type = new TypeToken<BannerResponse>(){}.getType();
        BannerResponse bannerResponse = new Gson().fromJson(bannerString, type);
        bannerList.postValue(bannerResponse);
    }
}