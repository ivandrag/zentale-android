package com.bedtime.stories.kids.zentale.presentation.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bedtime.stories.kids.zentale.domain.StoryRepository
import com.bedtime.stories.kids.zentale.domain.model.PaginatedResultBO
import com.bedtime.stories.kids.zentale.domain.model.Story
import com.bedtime.stories.kids.zentale.presentation.shared.model.StoryType
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LibraryViewModel(
    private val storyRepository: StoryRepository
): ViewModel() {

    private val _state = MutableStateFlow(LibraryState())
    val state = _state.asStateFlow()

    private var lastVisibleStory: DocumentSnapshot? = null

    init {
        fetchInitialStories()
        viewModelScope.launch {
            storyRepository.allStories.collect { result ->
                _state.value = result.toLibraryState()
                lastVisibleStory = result.lastVisibleStory
            }
        }
    }

    private fun fetchInitialStories() = viewModelScope.launch {
        _state.value = LibraryState(isLoading = true)
        storyRepository.fetchStories()
    }

    fun fetchMoreStories() {
        viewModelScope.launch {
            storyRepository.fetchMoreStories(lastVisibleStory)
        }
    }

    private fun PaginatedResultBO.toLibraryState() = LibraryState(
        isLoading = false,
        isEmpty = stories.isEmpty(),
        stories = stories.map { it.toStoryPreview() }
    )

    private fun Story.toStoryPreview() = StoryPreview(
        storyId = storyId,
        storyImage = storyImage,
        storyTitle = storyTitle
    )

    fun setDemoStoryId(storyId: String) = viewModelScope.launch {
        storyRepository.saveStoryType(StoryType.ViewStory(storyId = storyId))
    }
}
