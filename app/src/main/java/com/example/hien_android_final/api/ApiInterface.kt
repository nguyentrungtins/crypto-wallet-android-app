package com.example.hien_android_final.api

import com.example.hien_android_final.api.CoinPriceItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("api/v3/coins/markets?vs_currency=usd")
    fun getData(): Call<List<CoinPriceItem>>

}