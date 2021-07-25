package com.example.pixabaysearch.data.api

import com.example.pixabaysearch.domain.PixabayResponseDomain

interface PixabayInteractor {
    suspend fun getListOfImages(searchQuery: String): PixabayResponseDomain
}

enum class Status { SUCCESS, ERROR, LOADING }