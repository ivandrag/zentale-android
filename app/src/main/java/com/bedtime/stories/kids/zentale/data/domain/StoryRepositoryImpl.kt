package com.bedtime.stories.kids.zentale.data.domain

import com.bedtime.stories.kids.zentale.data.remote.StoryRemoteDataSource
import com.bedtime.stories.kids.zentale.domain.StoryRepository

class StoryRepositoryImpl(
    private val storyRemoteDataSource: StoryRemoteDataSource
): StoryRepository {

    override suspend fun fetchDemoStory(id: String) = storyRemoteDataSource.fetchDemoStory(id)
}