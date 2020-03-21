package com.example.pixabaysearch.data.api

import com.example.pixabaysearch.model.PixabayResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayService {
    @GET("/api/?")
    fun listImages(@Query("key") key: String, @Query("q") search: String) : Call<PixabayResponse>
}
