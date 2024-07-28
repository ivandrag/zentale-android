package com.bedtime.stories.kids.zentale.presentation.createStory

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bedtime.stories.kids.zentale.domain.SavePhotoToGalleryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CreateStoryViewModel(
    private val savePhotoToGalleryRepository: SavePhotoToGalleryRepository
): ViewModel() {

    private val _state = MutableStateFlow(CreateStoryState())
    val state = _state.asStateFlow()

    fun storePhotoInGallery(bitmap: Bitmap) {
        viewModelScope.launch {
            savePhotoToGalleryRepository.savePhotoToGallery(bitmap)
            updateCapturedPhotoState(bitmap)
        }
    }

    fun updateCapturedPhotoState(updatedPhoto: Bitmap?) {
        _state.value.capturedImage?.recycle()
        _state.value = _state.value.copy(capturedImage = updatedPhoto)
        println("capturedImage ${_state.value.capturedImage}")
    }

    override fun onCleared() {
        _state.value.capturedImage?.recycle()
        super.onCleared()
    }
}
