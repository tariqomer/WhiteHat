package com.example.whiterabbit.modelclass.respose

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductListResponse(
    var productResponse: List<ProductResponse>? = null
) : Parcelable