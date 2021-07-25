package com.example.pixabaysearch.ui.model

import java.io.Serializable

data class ImageModel(
    var url: String = "",
    var fullSizeUrl: String = "",
    var likes: Int = 0,
    var tags: String = "",
    var userName: String = "",
    var favourites: Int = 0,
    var comments: Int = 0
) : Serializable