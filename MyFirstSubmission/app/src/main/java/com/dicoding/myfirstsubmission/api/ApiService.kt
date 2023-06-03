package com.dicoding.myfirstsubmission.api

import com.dicoding.myfirstsubmission.data.DataUser
import com.dicoding.myfirstsubmission.data.DetailUserResponse
import com.dicoding.myfirstsubmission.data.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    fun getUsers(
        @Query("q") query :String
    ): Call<UserResponse>

    @GET("users/{username}")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String): Call<ArrayList<DataUser>>

    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String): Call<ArrayList<DataUser>>
}