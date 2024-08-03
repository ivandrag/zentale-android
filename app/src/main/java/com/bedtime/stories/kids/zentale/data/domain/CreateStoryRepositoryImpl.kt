package com.bedtime.stories.kids.zentale.data.domain

import com.bedtime.stories.kids.zentale.data.model.CreateStoryRequest
import com.bedtime.stories.kids.zentale.data.remote.CreateStoryRemoteDataSource
import com.bedtime.stories.kids.zentale.domain.CreateStoryRepository
import com.bedtime.stories.kids.zentale.presentation.shared.model.StoryType

class CreateStoryRepositoryImpl(
    private val createStoryRemoteDataSource: CreateStoryRemoteDataSource,
) : CreateStoryRepository {

    override suspend fun createStory(storyId: String, imageUrl: String, language: String) {
        val storyRequest = CreateStoryRequest(
            imageUrl = imageUrl,
            languageOfTheStory = language,
            storyId = storyId
        )
        createStoryRemoteDataSource.createStory(storyRequest)
    }
}
