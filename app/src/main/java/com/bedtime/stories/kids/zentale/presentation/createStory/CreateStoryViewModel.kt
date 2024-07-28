package com.bedtime.stories.kids.zentale.presentation.createStory

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CreateStoryViewModel: ViewModel() {

    private val _state = MutableStateFlow(CreateStoryState())
    val state = _state.asStateFlow()

    fun storePhotoInGallery(bitmap: Bitmap) {
        viewModelScope.launch {
//            savePhotoToGalleryUseCase.call(bitmap)
            updateCapturedPhotoState(bitmap)
        }
    }

    private fun updateCapturedPhotoState(updatedPhoto: Bitmap?) {
        _state.value.capturedImage?.recycle()
        _state.value = _state.value.copy(capturedImage = updatedPhoto)
    }

    override fun onCleared() {
        _state.value.capturedImage?.recycle()
        super.onCleared()
    }
}
