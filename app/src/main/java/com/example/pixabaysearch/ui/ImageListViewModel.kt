package com.example.pixabaysearch.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pixabaysearch.data.repository.Resource
import com.example.pixabaysearch.data.repository.NetworkHelper
import com.example.pixabaysearch.data.repository.PixabayRepository
import com.example.pixabaysearch.ui.model.ImageModel
import com.example.pixabaysearch.ui.model.ImageModelMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageListViewModel @Inject constructor(
    private val repository: PixabayRepository,
    private val modelMapper: ImageModelMapper,
    networkHelper: NetworkHelper
) : ViewModel() {
    private val _images = MutableLiveData<Resource<List<ImageModel>>>()
    val images: LiveData<Resource<List<ImageModel>>>
        get() = _images

    var selectedImage: ImageModel = ImageModel()

    init {
        _images.postValue(Resource.loading(emptyList()))

        if (networkHelper.isNetworkConnected()) {
            fetchImages("cat")
        } else {
            _images.postValue(
                Resource.error(
                    data = emptyList(),
                    message = CONNECTION_ERROR_MSG
                )
            )
        }
    }

    fun fetchImages(searchQuery: String) {
        viewModelScope.launch {
            try {
                _images.value = Resource.success(
                    data = modelMapper.map(repository.getListOfImages(searchQuery))
                )
            } catch (exception: Exception) {
                _images.value = Resource.error(
                    data = emptyList(),
                    message = exception.message ?: GENERAL_ERROR_MSG
                )
            }
        }
    }

    companion object {
        const val CONNECTION_ERROR_MSG = "Network is down. Please check your internet connection!"
        const val GENERAL_ERROR_MSG = "Error Occurred!"
    }
}
