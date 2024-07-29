package com.bedtime.stories.kids.zentale.presentation.story

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bedtime.stories.kids.zentale.domain.StoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StoryViewModel(
    private val storyRepository: StoryRepository
) : ViewModel() {
    private val _state = MutableStateFlow(StoryState())
    val state = _state.asStateFlow()

    fun loadStory(storyId: String?) = viewModelScope.launch {
        if (storyId != null) {
            val story = storyRepository.fetchDemoStory(storyId)
            if (story == null) {
                _state.value = state.value.copy(status = "failed")
                return@launch
            }
            _state.value = state.value.copy(
                storyId = story.storyId,
                storyImage = story.storyImage,
                storyTitle = story.storyTitle,
                storyContent = story.storyContent,
                status = story.status
            )
        }
    }
}
