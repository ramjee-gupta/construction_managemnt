package com.example.shopingdemo.koinmodules

import com.example.shopingdemo.dashboard.ui.viewmodels.DashboardViewModel
import com.example.shopingdemo.dashboard.data.ProductsRepo
import com.example.shopingdemo.server.retrofit.getGson
import com.example.shopingdemo.server.retrofit.getRetrofit
import com.example.shopingdemo.server.retrofit.provideRetrofitInstance
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val retrofitModule = module {

    single { getGson() }
    single { getRetrofit(get()) }

    single { provideRetrofitInstance(get()) }


    viewModel{
        DashboardViewModel(get())
    }

    factory {
        ProductsRepo(get())
    }


}

