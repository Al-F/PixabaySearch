package com.example.pixabaysearch.data.api

import com.example.pixabaysearch.data.PixabayResponse

interface PixabayInteractor {
    suspend fun getListOfImages(searchQuery: String): PixabayResponse
}

enum class Status { SUCCESS, ERROR, LOADING }