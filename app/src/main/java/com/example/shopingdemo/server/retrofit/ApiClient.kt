package com.example.shopingdemo.server.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "http://vm1.dev.anarock.com:3221/"



fun getGson(): Gson = GsonBuilder()
    .setLenient()
    .create()

fun getRetrofit(gson: Gson): Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()

fun provideRetrofitInstance(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)