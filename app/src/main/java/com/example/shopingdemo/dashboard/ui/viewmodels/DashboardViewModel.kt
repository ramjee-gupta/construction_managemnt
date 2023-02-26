package com.example.shopingdemo.dashboard.ui.viewmodels

import android.accounts.NetworkErrorException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopingdemo.dashboard.data.ProductsRepo
import com.example.shopingdemo.dashboard.model.MainDataModel
import com.example.shopingdemo.dashboard.model.ProfileModel
import com.example.shopingdemo.server.retrofit.Resource
import kotlinx.coroutines.launch

class DashboardViewModel(private val repo: ProductsRepo) : ViewModel() {

    private val _products = MutableLiveData<Resource<MainDataModel>>()
    val products: LiveData<Resource<MainDataModel>> = _products

    private val _profile = MutableLiveData<Resource<ProfileModel>>()
    val profile: LiveData<Resource<ProfileModel>> = _profile

    fun getProduct() = viewModelScope.launch {
        _products.postValue(Resource.Loading())
        try {
            val res = repo.getProducts()
            _products.postValue(Resource.Success(res))
        } catch (exception: NetworkErrorException) {
            _products.postValue(Resource.Error("Please connect to network"))
        } catch (exception: Exception) {
            _products.postValue(Resource.Error(exception.message.toString()))
        }
    }

    fun getProfile() = viewModelScope.launch {

        _profile.postValue(Resource.Loading())
        try {
            val res = repo.getProfile()
            _profile.postValue(Resource.Success(res))
        } catch (exception: NetworkErrorException) {
            _profile.postValue(Resource.Error("Please connect to network"))
        } catch (exception: Exception) {
            _profile.postValue(Resource.Error(exception.message.toString()))
        }
    }
}