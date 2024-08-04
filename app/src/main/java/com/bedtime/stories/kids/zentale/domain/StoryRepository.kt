package com.bedtime.stories.kids.zentale.domain

import com.bedtime.stories.kids.zentale.domain.model.Story
import com.bedtime.stories.kids.zentale.presentation.shared.model.StoryType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface StoryRepository {
    val storyType: Flow<StoryType>
    val story: StateFlow<Story?>

    suspend fun createStory(storyId: String, imageUrl: String, language: String)
    suspend fun saveStoryType(type: StoryType)
    suspend fun fetchStory(id: String)
    suspend fun fetchDemoStory(id: String)
}