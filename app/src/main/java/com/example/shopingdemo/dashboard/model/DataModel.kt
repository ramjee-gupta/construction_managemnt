package com.example.shopingdemo.dashboard.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataModel(

    @SerializedName("products") var products: ArrayList<ProductsModel> = arrayListOf()

) : Parcelable
