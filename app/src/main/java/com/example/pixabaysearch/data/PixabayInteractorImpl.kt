package com.example.pixabaysearch.data

import com.example.pixabaysearch.data.api.PixabayApi
import com.example.pixabaysearch.model.PixabayResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class PixabayInteractorImpl @Inject constructor(private val retrofit: Retrofit): PixabayInteractor {
    override fun getListOfImages(searchQuery: String): PixabayResponse {
        val call = retrofit.create(PixabayApi::class.java)
            .getImages("15612596-c20b2282ebade3a0e69a30c78", searchQuery)
        var responseBody: PixabayResponse? = null
        call.clone().enqueue(object : Callback<PixabayResponse> {
            override fun onResponse(
                call: Call<PixabayResponse>,
                response: Response<PixabayResponse>
            ) {
                if (response.code() == 200) {
                    responseBody = response.body()
                }
            }

            override fun onFailure(call: Call<PixabayResponse>, t: Throwable) {

            }
        })
        return responseBody ?: PixabayResponse(0, 0, emptyList())
    }
}