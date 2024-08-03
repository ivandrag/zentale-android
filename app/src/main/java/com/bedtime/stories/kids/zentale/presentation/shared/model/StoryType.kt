package com.bedtime.stories.kids.zentale.presentation.shared.model

sealed interface StoryType {
    data object Default : StoryType
    data class Create(val language: String, val storyId: String, val imageUrl: String) : StoryType
    data class ViewDemo(val storyId: String) : StoryType
    data class ViewStory(val storyId: String) : StoryType
}
