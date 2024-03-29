package com.example.pixabaysearch.data.repository

import com.example.pixabaysearch.data.domain.PixabayResponseDomain
import com.example.pixabaysearch.data.network.PixabayInteractor
import javax.inject.Inject

class PixabayRepository @Inject constructor(
    private val apiInteractor: PixabayInteractor
){
    suspend fun getListOfImages(searchQuery: String): PixabayResponseDomain {
        return apiInteractor.getListOfImages(searchQuery)
    }
}
