package com.example.shopingdemo.dashboard.data

import com.example.shopingdemo.dashboard.model.MainDataModel
import com.example.shopingdemo.dashboard.model.ProfileModel
import com.example.shopingdemo.server.retrofit.ApiService
import org.json.JSONObject

class ProductsRepo(private val api: ApiService) {
    suspend fun getProducts(): MainDataModel = api.getProducts()
    suspend fun getProfile() : ProfileModel = api.getUserProfile()
    suspend fun signUp(params: JSONObject) : String = api.signUp(params)
}