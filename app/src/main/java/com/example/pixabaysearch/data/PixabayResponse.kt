package com.example.pixabaysearch.data

import com.example.pixabaysearch.model.Hits
import com.google.gson.annotations.SerializedName

class PixabayResponse(
    @SerializedName("total") val total : Int,
    @SerializedName("totalHits") val totalHits : Int,
    @SerializedName("hits") val hits : List<Hits>
)
