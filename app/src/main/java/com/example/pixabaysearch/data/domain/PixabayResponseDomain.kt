package com.example.pixabaysearch.data.domain

import com.google.gson.annotations.SerializedName

class PixabayResponseDomain(
    @SerializedName("total") val total : Int,
    @SerializedName("totalHits") val totalHits : Int,
    @SerializedName("hits") val hits : List<HitsDomain>
)
