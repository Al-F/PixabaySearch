package com.example.pixabaysearch.data.network

import com.example.pixabaysearch.data.domain.PixabayResponseDomain
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {
    @GET(RestConfig.SEARCH_END)
    suspend fun getImages(@Query("key") key: String, @Query("q") search: String): PixabayResponseDomain
}