package com.example.pixabaysearch.ui.model

import com.example.pixabaysearch.data.domain.PixabayResponseDomain
import javax.inject.Inject

class ImageModelMapper @Inject constructor() {
    fun map(response: PixabayResponseDomain): List<ImageModel> {
        return response.hits.map {
            ImageModel(
                url = it.previewURL,
                fullSizeUrl = it.largeImageURL,
                likes = it.likes,
                favourites = it.favorites,
                userName = it.user,
                tags = it.tags,
                comments = it.comments
            )
        }
    }
}