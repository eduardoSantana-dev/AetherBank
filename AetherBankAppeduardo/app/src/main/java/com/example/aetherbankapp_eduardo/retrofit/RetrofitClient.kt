package com.example.aetherbankapp_eduardo.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient{
    private const val baseUrl = "http://192.168.0.9:8080/"

    private val retrofit =Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
                val api: AetherApi = retrofit.create(AetherApi::class.java)

}