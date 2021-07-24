package com.example.pixabaysearch.data

import com.example.pixabaysearch.model.PixabayResponse

interface PixabayInteractor {
    fun getListOfImages(searchQuery: String): PixabayResponse
}