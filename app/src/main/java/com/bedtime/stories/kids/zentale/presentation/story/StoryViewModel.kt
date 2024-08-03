package com.bedtime.stories.kids.zentale.presentation.story

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bedtime.stories.kids.zentale.domain.StoryRepository
import com.bedtime.stories.kids.zentale.presentation.shared.model.StoryType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StoryViewModel(
    private val storyRepository: StoryRepository
) : ViewModel() {
    private val _state = MutableStateFlow(StoryState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            storyRepository.story.collect {
                when (it) {
                    is StoryType.Create -> {

                    }

                    is StoryType.ViewDemo -> {
                        loadDemoStory(it.storyId)
                    }

                    is StoryType.ViewStory -> {

                    }

                    StoryType.Default -> {}
                }
            }
        }
    }

    private fun loadDemoStory(storyId: String?) = viewModelScope.launch {
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
