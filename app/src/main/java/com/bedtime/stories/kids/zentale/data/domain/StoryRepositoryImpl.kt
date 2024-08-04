package com.bedtime.stories.kids.zentale.data.domain

import com.bedtime.stories.kids.zentale.data.local.StoryLocalDataSource
import com.bedtime.stories.kids.zentale.data.model.CreateStoryRequest
import com.bedtime.stories.kids.zentale.data.model.StoryResponse
import com.bedtime.stories.kids.zentale.data.remote.CreateStoryRemoteDataSource
import com.bedtime.stories.kids.zentale.data.remote.StoryRemoteDataSource
import com.bedtime.stories.kids.zentale.domain.StoryRepository
import com.bedtime.stories.kids.zentale.domain.model.Story
import com.bedtime.stories.kids.zentale.presentation.shared.model.StoryType

class StoryRepositoryImpl(
    private val createStoryRemoteDataSource: CreateStoryRemoteDataSource,
    private val storyRemoteDataSource: StoryRemoteDataSource,
    private val storyLocalDataSource: StoryLocalDataSource
) : StoryRepository {

    override val storyType = storyLocalDataSource.storyType
    override val story = storyLocalDataSource.story

    override suspend fun createStory(storyId: String, imageUrl: String, language: String) {
        storyLocalDataSource.saveStory(null)
        val storyRequest = CreateStoryRequest(
            imageUrl = imageUrl,
            languageOfTheStory = language,
            storyId = storyId
        )
        createStoryRemoteDataSource.createStory(storyRequest)
            .onSuccess {
                fetchStory(storyId)
            }.onFailure {
                storyLocalDataSource.saveStory(null)
            }
    }

    override suspend fun saveStoryType(type: StoryType) {
        storyLocalDataSource.saveStoryType(type)
    }

    override suspend fun fetchDemoStory(id: String) {
        storyRemoteDataSource.fetchDemoStory(id).collect {
            storyLocalDataSource.saveStory(it!!)
        }
    }

    override suspend fun fetchStory(id: String) {
        storyRemoteDataSource.fetchStory(id).collect {
            val story = it?.toStory()
            storyLocalDataSource.saveStory(story)
        }
    }

    private fun StoryResponse.toStory() = Story(
        storyId = storyId ?: "",
        storyImage = storyImage ?: "",
        storyTitle = storyTitle ?: "",
        storyContent = storyContent ?: "",
        status = status ?: "",
    )
}
