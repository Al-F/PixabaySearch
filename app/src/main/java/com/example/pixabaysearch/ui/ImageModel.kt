package com.example.pixabaysearch.ui

data class ImageModel(
    var url: String,
    var fullSizeUrl: String,
    var likes: Int,
    var tags: String,
    var userName: String,
    var favourites: Int,
    var comments: Int
)