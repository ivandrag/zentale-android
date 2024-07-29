package com.bedtime.stories.kids.zentale.presentation.story

data class StoryState(
    val storyId: String? = "",
    val storyImage: String = "",
    val storyTitle: String = "",
    val storyContent: String = "",
    val status: String? = null
)
