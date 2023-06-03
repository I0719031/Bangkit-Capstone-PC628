package com.dicoding.myfirstsubmission.viewmodel
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.myfirstsubmission.api.ApiConfig
import com.dicoding.myfirstsubmission.data.DataUser
import com.dicoding.myfirstsubmission.data.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel :ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _listUser = MutableLiveData<ArrayList<DataUser>>()
    val listUser : LiveData<ArrayList<DataUser>> = _listUser

    companion object{
        private const val TAG = "MainViewModel"
    }

    fun SearchGithubUser(query : String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUsers(query)
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listUser.postValue(response.body()?.items)
                } else {
                    Log.e(TAG, "onFailure1: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG,"onFailure2: ${t.message}")
            }
        })
    }
}