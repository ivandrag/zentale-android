package com.bedtime.stories.kids.zentale.domain.model

data class Story(
    val storyId: String = "",
    val storyImage: String = "",
    val storyTitle: String = "",
    val storyContent: String = "",
    val storyLanguage: String = "",
    val storyAudioUrl: String = "",
    val status: String = "loading"
)
