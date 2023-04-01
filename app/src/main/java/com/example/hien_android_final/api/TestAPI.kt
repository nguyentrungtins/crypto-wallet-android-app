package com.example.hien_android_final.api

import retrofit2.Call
import retrofit2.http.GET

interface TestAPI {
    @GET("posts")
    fun gettestAPI(): Call<List<testAPIItem>>
}