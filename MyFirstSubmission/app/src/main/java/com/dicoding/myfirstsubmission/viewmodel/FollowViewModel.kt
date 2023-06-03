package com.dicoding.myfirstsubmission.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.myfirstsubmission.api.ApiConfig
import com.dicoding.myfirstsubmission.data.DataUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowViewModel: ViewModel() {
    private val _followers = MutableLiveData<ArrayList<DataUser>>()
    val followers : LiveData<ArrayList<DataUser>> = _followers

    private val _following = MutableLiveData<ArrayList<DataUser>>()
    val following : LiveData<ArrayList<DataUser>> = _following

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        private const val TAG = "FollowViewModel"
    }

    fun setListFollowers(username : String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowers(username)
        client.enqueue(object : Callback<ArrayList<DataUser>> {
            override fun onResponse(
                call: Call<ArrayList<DataUser>>,
                response: Response<ArrayList<DataUser>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _followers.postValue(response.body())
                } else {
                    Log.e(TAG, "onFailure1: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<ArrayList<DataUser>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG,"onFailure2: ${t.message}")
            }
        })
    }


    fun setListFollownig(username : String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowing(username)
        client.enqueue(object : Callback<ArrayList<DataUser>> {
            override fun onResponse(
                call: Call<ArrayList<DataUser>>,
                response: Response<ArrayList<DataUser>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _following.postValue(response.body())
                } else {
                    Log.e(TAG, "onFailure1: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<ArrayList<DataUser>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG,"onFailure2: ${t.message}")
            }
        })
    }
}