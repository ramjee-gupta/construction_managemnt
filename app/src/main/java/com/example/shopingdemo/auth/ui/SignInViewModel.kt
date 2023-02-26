package com.example.shopingdemo.auth.ui

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopingdemo.dashboard.data.ProductsRepo
import com.example.shopingdemo.database.User
import com.example.shopingdemo.database.UserDao
import com.example.shopingdemo.server.retrofit.Resource
import kotlinx.coroutines.launch
import org.json.JSONObject


class SignInViewModel(private val db: UserDao, private val repo: ProductsRepo) : ViewModel() {

    private val _saveUser = MutableLiveData<Resource<Unit>>()
    val saveUser: LiveData<Resource<Unit>> = _saveUser

    private val _signUpUser = MutableLiveData<Resource<String>>()
    val signUpUser: LiveData<Resource<String>> = _signUpUser

    private val _signIn = MutableLiveData<Resource<User?>>()
    val signIn: LiveData<Resource<User?>> = _signIn

    private val _alreadyLogin = MutableLiveData<Resource<Int?>>()
    val alreadyLogin: LiveData<Resource<Int?>> = _alreadyLogin


    fun signUp(name: String, email: String, password: String) = viewModelScope.launch {

        _saveUser.postValue(Resource.Loading())
        try {
            _saveUser.postValue(Resource.Success(db.insert(User(email, name, password))))
        } catch (exception: NetworkErrorException) {
            _saveUser.postValue(Resource.Error("Please connect to network"))
        } catch (exception: Exception) {
            _saveUser.postValue(Resource.Error(exception.message.toString()))
        }
    }


    fun signIn(email: String, password: String) = viewModelScope.launch {
        _signIn.postValue(Resource.Loading())

        try {
            val user = db.getUser(email, password)
            _signIn.postValue(Resource.Success(user))
        } catch (exception: NetworkErrorException) {
            _signIn.postValue(Resource.Error("Please connect to network"))
        } catch (exception: Exception) {
            _signIn.postValue(Resource.Error(exception.message.toString()))
        }
    }

    fun getUser() = viewModelScope.launch {
        _alreadyLogin.postValue(Resource.Loading())

        try {
            val count = db.getCount()
            _alreadyLogin.postValue(Resource.Success(count))
        } catch (exception: NetworkErrorException) {
            _alreadyLogin.postValue(Resource.Error("Please connect to network"))
        } catch (exception: Exception) {
            _alreadyLogin.postValue(Resource.Error(exception.message.toString()))
        }
    }

    fun signUpUser(params: JSONObject) = viewModelScope.launch {
        Log.e("TAG", "signUpUser: "+params )
        _signUpUser.postValue(Resource.Loading())
        try {
            _signUpUser.postValue(Resource.Success(repo.signUp(params)))
        } catch (exception: NetworkErrorException) {
            _signUpUser.postValue(Resource.Error("Please connect to network"))
        } catch (exception: Exception) {
            _signUpUser.postValue(Resource.Error(exception.message.toString()))
        }
    }


}