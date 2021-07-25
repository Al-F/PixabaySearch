package com.example.pixabaysearch.data.api

import com.example.pixabaysearch.data.PixabayResponse
import javax.inject.Inject

class PixabayInteractorNetworkImpl @Inject constructor(
    private val apiService: PixabayApi
) : PixabayInteractor {
    override suspend fun getListOfImages(searchQuery: String): PixabayResponse =
        apiService.getImages(RestConfig.API_TOKEN, searchQuery)
}
