package com.bedtime.stories.kids.zentale.presentation.library

data class LibraryState(
    val isLoading: Boolean = false,
    val isEmpty: Boolean = false,
    val stories: List<StoryPreview> = emptyList()
)

data class StoryPreview(
    val storyId: String,
    val storyImage: String,
    val storyTitle: String
)