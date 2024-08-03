package com.bedtime.stories.kids.zentale.domain

import com.bedtime.stories.kids.zentale.domain.model.Story
import com.bedtime.stories.kids.zentale.presentation.shared.model.StoryType
import kotlinx.coroutines.flow.Flow

interface StoryRepository {
    val story: Flow<StoryType>

    suspend fun saveStoryType(type: StoryType)
    suspend fun fetchDemoStory(id: String): Story?
}
