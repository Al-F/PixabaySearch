package com.example.pixabaysearch.model

import com.google.gson.annotations.SerializedName

class PixabayResponse(
    @SerializedName("total") val total : Int,
    @SerializedName("totalHits") val totalHits : Int,
    @SerializedName("hits") val hits : List<Hits>
)
