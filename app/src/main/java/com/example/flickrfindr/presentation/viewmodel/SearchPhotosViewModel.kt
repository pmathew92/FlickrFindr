package com.example.flickrfindr.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flickrfindr.domain.model.Photo
import com.example.flickrfindr.domain.usecase.GetPhotosBySearchUseCase
import com.example.flickrfindr.domain.util.DispatcherProvider
import com.example.flickrfindr.domain.util.Response
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


sealed class PhotosUiState {
    object Idle : PhotosUiState()
    object Loading : PhotosUiState()
    data class Success(val photoList: List<Photo>) : PhotosUiState()
    data class Error(val errorMessage: String?) : PhotosUiState()
}

class SearchPhotosViewModel(
    private val getPhotosBySearchUseCase: GetPhotosBySearchUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _photoUiState = mutableStateOf<PhotosUiState>(PhotosUiState.Idle)

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _photoUiState.value = PhotosUiState.Error(throwable.message)
    }

    val photosUiState: State<PhotosUiState> = _photoUiState

    fun fetchPhotos(searchText: String) {
        _photoUiState.value = PhotosUiState.Loading
        viewModelScope.launch(exceptionHandler + dispatcherProvider.ioDispatcher()) {
            val uiState =
                when (val response = getPhotosBySearchUseCase(searchText = searchText, 1)) {
                    is Response.Success -> {
                        Log.d("PLING", "fetchPhotos: ${response.data}")
                        PhotosUiState.Success(response.data)
                    }

                    is Response.Failure -> {
                        PhotosUiState.Error(response.throwable?.message)
                    }
                }
            withContext(dispatcherProvider.mainDispatcher()) {
                _photoUiState.value = uiState
            }
        }
    }

}