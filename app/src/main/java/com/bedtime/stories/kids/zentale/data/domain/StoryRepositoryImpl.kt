package com.bedtime.stories.kids.zentale.data.domain

import com.bedtime.stories.kids.zentale.data.local.StoryLocalDataSource
import com.bedtime.stories.kids.zentale.data.remote.StoryRemoteDataSource
import com.bedtime.stories.kids.zentale.domain.StoryRepository
import com.bedtime.stories.kids.zentale.presentation.shared.model.StoryType
import kotlinx.coroutines.flow.Flow

class StoryRepositoryImpl(
    private val storyRemoteDataSource: StoryRemoteDataSource,
    private val storyLocalDataSource: StoryLocalDataSource
): StoryRepository {

    override val story = storyLocalDataSource.storyType

    override suspend fun saveStoryType(type: StoryType) {
        storyLocalDataSource.saveStoryType(type)
    }

    override suspend fun fetchDemoStory(id: String) = storyRemoteDataSource.fetchDemoStory(id)
}
