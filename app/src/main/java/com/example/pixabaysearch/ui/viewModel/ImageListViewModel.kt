package com.example.pixabaysearch.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pixabaysearch.data.Resource
import com.example.pixabaysearch.data.repository.NetworkHelper
import com.example.pixabaysearch.data.repository.PixabayRepository
import com.example.pixabaysearch.ui.uiModel.ImageModel
import com.example.pixabaysearch.ui.uiModel.ImageModelMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageListViewModel @Inject constructor(
    private val repository: PixabayRepository,
    private val modelMapper: ImageModelMapper,
    networkHelper: NetworkHelper
) : ViewModel() {
    private val _response = MutableLiveData<Resource<List<ImageModel>>>()
    val response: LiveData<Resource<List<ImageModel>>>
        get() = _response

    var selectedImage: ImageModel = ImageModel()

    init {
        _response.postValue(Resource.loading(emptyList()))

        if (networkHelper.isNetworkConnected()) {
            fetchImages("cat")
        } else {
            _response.postValue(
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
                _response.value = Resource.success(
                    data = modelMapper.map(repository.getListOfImages(searchQuery))
                )
            } catch (exception: Exception) {
                _response.value = Resource.error(
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
