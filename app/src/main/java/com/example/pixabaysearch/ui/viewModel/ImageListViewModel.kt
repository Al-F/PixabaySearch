package com.example.pixabaysearch.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pixabaysearch.data.api.PixabayService
import com.example.pixabaysearch.data.error.Failure
import com.example.pixabaysearch.model.PixabayResponse
import com.example.pixabaysearch.ui.uiModel.ImageModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageListViewModel : ViewModel() {
    private val failure: MutableLiveData<Failure> = MutableLiveData()
    val images = MutableLiveData<List<ImageModel>>()

    fun observeFailure(): LiveData<Failure> = failure

    fun observeImages(): LiveData<List<ImageModel>> = images

    fun getImages(searchQuery: String) {
        val call = PixabayService.retrofit.create(PixabayService::class.java)
            .getImages("15612596-c20b2282ebade3a0e69a30c78", searchQuery)
        call.clone().enqueue(object : Callback<PixabayResponse> {
            override fun onResponse(call: Call<PixabayResponse>, response: Response<PixabayResponse>) {
                if (response.code() == 200) {
                    handleSuccess(response.body())
                }
            }

            override fun onFailure(call: Call<PixabayResponse>, t: Throwable) {
                handleFailure(Failure.ServerError)
            }
        })
    }

    private fun handleSuccess(response: PixabayResponse?) {
        val listOfImages =
            response?.hits?.map {
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
        this.images.value = listOfImages
    }

    private fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }
}
