package com.example.pixabaysearch.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pixabaysearch.data.PixabayResponse
import com.example.pixabaysearch.data.Resource
import com.example.pixabaysearch.data.repository.NetworkHelper
import com.example.pixabaysearch.data.repository.PixabayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageListViewModel @Inject constructor(
    private val repository: PixabayRepository,
    networkHelper: NetworkHelper
) : ViewModel() {
    private val _response = MutableLiveData<Resource<PixabayResponse>>()
    val response: LiveData<Resource<PixabayResponse>>
        get() = _response

    init {
        _response.postValue(Resource.loading(null))

        if (networkHelper.isNetworkConnected()) {
            fetchImages("cat")
        } else {
            _response.postValue(
                Resource.error(
                    data = null,
                    message = CONNECTION_ERROR_MSG))
        }
    }

    fun fetchImages(searchQuery: String) {
        viewModelScope.launch {
            try {
                _response.value = Resource.success(
                    data = repository.getListOfImages(searchQuery))
            } catch (exception: Exception) {
                _response.value = Resource.error(
                    data = null,
                    message = exception.message ?: GENERAL_ERROR_MSG
                )
            }
        }
    }

    companion object {
        private val TAG = "MYLOG MainVM"
        const val CONNECTION_ERROR_MSG = "Network is down. Please check your internet connection!"
        const val GENERAL_ERROR_MSG = "Error Occurred!"

        fun LOGGING_DEBUG(s: String) = Log.d(TAG, s)
        fun LOGGING_ERROR(s: String) = Log.e(TAG, s)
        fun LOGGING_INFO(s: String) = Log.i(TAG, s)
    }
}
