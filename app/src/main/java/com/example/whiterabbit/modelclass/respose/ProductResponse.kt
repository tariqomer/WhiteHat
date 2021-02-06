package com.example.whiterabbit.modelclass.respose

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductResponse(
    var productId: Int? = null,
    var productName: String? = null,
    var productSubName: String? = null,
    var url: String? = null
) : Parcelable