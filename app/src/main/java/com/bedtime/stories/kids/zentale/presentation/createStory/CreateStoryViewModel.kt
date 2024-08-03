package com.bedtime.stories.kids.zentale.presentation.createStory

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bedtime.stories.kids.zentale.domain.SavePhotoToGalleryRepository
import com.bedtime.stories.kids.zentale.domain.StoryRepository
import com.bedtime.stories.kids.zentale.domain.UploadPhotoRepository
import com.bedtime.stories.kids.zentale.presentation.shared.model.StoryType
import com.bedtime.stories.kids.zentale.presentation.utils.extensions.bitmapToByteArray
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class CreateStoryViewModel(
    private val savePhotoToGalleryRepository: SavePhotoToGalleryRepository,
    private val uploadPhotoRepository: UploadPhotoRepository,
    private val storyRepository: StoryRepository
): ViewModel() {

    private val _event = MutableSharedFlow<CreateStoryEvent>()
    val event = _event.asSharedFlow()

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
    }

    fun uploadImage() = viewModelScope.launch {
        _state.value = _state.value.copy(isLoading = true)
        val storyId = UUID.randomUUID().toString()
        val imageBytes = _state.value.capturedImage?.bitmapToByteArray()
        val language = _state.value.language
        runCatching {
            uploadPhotoRepository.uploadAndGetPublicUrl(imageBytes!!)
        }.onSuccess {
            _state.value = _state.value.copy(isLoading = false)
            storyRepository.saveStoryType(StoryType.Create(language, storyId, it))
            _event.emit(CreateStoryEvent.OnImageUploadSuccess)
        }.onFailure {
            _state.value = _state.value.copy(isLoading = false)
            _event.emit(CreateStoryEvent.OnImageUploadFailed)
        }
    }

    override fun onCleared() {
        _state.value.capturedImage?.recycle()
        super.onCleared()
    }
}
