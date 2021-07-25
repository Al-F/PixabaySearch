package com.example.pixabaysearch.data.network

import com.example.pixabaysearch.data.domain.PixabayResponseDomain

interface PixabayInteractor {
    suspend fun getListOfImages(searchQuery: String): PixabayResponseDomain
}

enum class Status { SUCCESS, ERROR, LOADING }