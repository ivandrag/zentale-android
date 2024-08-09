package com.bedtime.stories.kids.zentale.presentation.story

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bedtime.stories.kids.zentale.domain.StoryRepository
import com.bedtime.stories.kids.zentale.domain.model.Story
import com.bedtime.stories.kids.zentale.domain.model.StoryType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class StoryViewModel(
    private val storyRepository: StoryRepository
) : ViewModel() {
    private val _state = MutableStateFlow(
        generateState(storyRepository.story.value)
    )
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            storyRepository.storyType.collect {
                when (it) {
                    is StoryType.Create -> {
                        createStory(it.storyId, it.imageUrl, it.language)
                    }

                    is StoryType.ViewDemo -> {
                        loadDemoStory(it.storyId)
                    }

                    is StoryType.ViewStory -> {
                        loadStory(it.storyId)
                    }

                    StoryType.Default -> {}
                }
            }
        }
        viewModelScope.launch {
            storyRepository.story.collect { story ->
                _state.value = generateState(story)
            }
        }
    }

    private fun createStory(storyId: String, imageUrl: String, language: String) =
        viewModelScope.launch {
            runCatching {
                storyRepository.createStory(storyId, imageUrl, language)
            }.onFailure {
                _state.value = _state.value.copy(status = null)
            }
        }

    private fun loadStory(storyId: String) = viewModelScope.launch {
        runCatching {
            storyRepository.fetchStory(storyId)
        }.onFailure {
            _state.value = _state.value.copy(status = null)
        }
    }

    private fun loadDemoStory(storyId: String?) = viewModelScope.launch {
        if (storyId != null) {
            runCatching {
                storyRepository.fetchDemoStory(storyId)
            }.onFailure {
                _state.value = _state.value.copy(status = null)
            }
        }
    }

    private fun generateState(story: Story?): StoryState {
        return StoryState(
            storyId = story?.storyId,
            storyImage = story?.storyImage ?: "",
            storyTitle = story?.storyTitle ?: "",
            storyContent = story?.storyContent ?: "",
            status = story?.status
        )
    }
}
