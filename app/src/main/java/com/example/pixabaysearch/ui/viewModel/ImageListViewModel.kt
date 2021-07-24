package com.example.pixabaysearch.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pixabaysearch.data.PixabayInteractor
import com.example.pixabaysearch.data.api.PixabayService
import com.example.pixabaysearch.data.error.Failure
import com.example.pixabaysearch.model.PixabayResponse
import com.example.pixabaysearch.ui.uiModel.ImageModel
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ImageListViewModel @Inject constructor(private val interactor: PixabayInteractor) :
    ViewModel() {
    private val failure: MutableLiveData<Failure> = MutableLiveData()
    val images = MutableLiveData<List<ImageModel>>()

    fun observeFailure(): LiveData<Failure> = failure

    fun observeImages(): LiveData<List<ImageModel>> = images

    fun getImages(searchQuery: String) {
        val response = interactor.getListOfImages(searchQuery)
        handleSuccess(response)
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
