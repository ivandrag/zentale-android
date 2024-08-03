package com.bedtime.stories.kids.zentale.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateStoryRequest(
    @SerialName("imageUrl") val imageUrl: String,
    @SerialName("languageOfTheStory") val languageOfTheStory: String,
    @SerialName("storyId") val storyId: String
)
