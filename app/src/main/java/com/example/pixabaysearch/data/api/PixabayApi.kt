package com.example.pixabaysearch.data.api

import com.example.pixabaysearch.model.PixabayResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {
    @GET("/api/?")
    fun getImages(@Query("key") key: String, @Query("q") search: String): Call<PixabayResponse>
}
