package com.example.shopingdemo.server.retrofit

import com.example.shopingdemo.dashboard.model.MainDataModel
import com.example.shopingdemo.dashboard.model.ProductsModel
import com.example.shopingdemo.dashboard.model.ProfileModel
import org.json.JSONObject
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("v3/bc09a745-4346-4025-9611-9da76366dbbc")
    suspend fun getProducts(): MainDataModel

    @GET("v3/aaf97364-eedc-46a5-8f9e-56eb4b3cedd2")
    suspend fun getUserProfile() : ProfileModel

    @FormUrlEncoded
    @POST("users/create")
    suspend fun signUp(@Field("data") data: JSONObject) : String
}