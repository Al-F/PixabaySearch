package com.example.pixabaysearch.data.api

import arrow.core.Either
import arrow.core.Left
import arrow.core.Right
import com.example.pixabaysearch.data.error.Failure
import retrofit2.Call

fun <T> Call<T>.sendRequest(): Either<Failure, T?> =
    this.execute().let {
        if (it.isSuccessful) Right(it.body())
        else Left(Failure.ServerError)
    }