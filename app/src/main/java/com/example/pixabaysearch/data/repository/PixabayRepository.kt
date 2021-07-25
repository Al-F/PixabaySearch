package com.example.pixabaysearch.data.repository

import com.example.pixabaysearch.data.PixabayResponse
import com.example.pixabaysearch.data.api.PixabayInteractor
import javax.inject.Inject
import javax.inject.Singleton

class PixabayRepository @Inject constructor(
    private val apiInteractor: PixabayInteractor
){
    suspend fun getListOfImages(searchQuery: String): PixabayResponse {
        return apiInteractor.getListOfImages(searchQuery)
    }
}
