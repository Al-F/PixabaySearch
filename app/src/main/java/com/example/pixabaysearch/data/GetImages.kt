package com.example.pixabaysearch.data

import arrow.core.Either
import com.example.pixabaysearch.data.api.PixabayService
import com.example.pixabaysearch.data.api.sendRequest
import com.example.pixabaysearch.data.error.Failure
import com.example.pixabaysearch.model.PixabayResponse

class GetImages(private val service: PixabayService) {
    fun run(searchQuery: String): Either<Failure, PixabayResponse?> =
        service.listImages("15612596-c20b2282ebade3a0e69a30c78", searchQuery).sendRequest()
}
