package com.bedtime.stories.kids.zentale.data.local

import com.bedtime.stories.kids.zentale.presentation.shared.model.StoryType
import kotlinx.coroutines.flow.Flow

interface StoryLocalDataSource {

    val storyType: Flow<StoryType>

    suspend fun saveStoryType(type: StoryType)
}
