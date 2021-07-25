package com.example.pixabaysearch.data.api

import com.example.pixabaysearch.data.PixabayResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {
    @GET(RestConfig.SEARCH_END)
    suspend fun getImages(@Query("key") key: String, @Query("q") search: String): PixabayResponse
}