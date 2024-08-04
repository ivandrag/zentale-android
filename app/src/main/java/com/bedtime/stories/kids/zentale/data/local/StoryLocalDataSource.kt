package com.bedtime.stories.kids.zentale.data.local

import com.bedtime.stories.kids.zentale.domain.model.Story
import com.bedtime.stories.kids.zentale.presentation.shared.model.StoryType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface StoryLocalDataSource {

    val storyType: Flow<StoryType>
    val story: StateFlow<Story?>

    suspend fun saveStoryType(type: StoryType)

    suspend fun saveStory(story: Story?)
}