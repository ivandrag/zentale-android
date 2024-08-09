package com.bedtime.stories.kids.zentale.data.domain

import com.bedtime.stories.kids.zentale.data.local.StoryLocalDataSource
import com.bedtime.stories.kids.zentale.data.model.CreateStoryRequest
import com.bedtime.stories.kids.zentale.data.model.StoryResponse
import com.bedtime.stories.kids.zentale.data.remote.CreateStoryRemoteDataSource
import com.bedtime.stories.kids.zentale.data.model.PaginatedResult
import com.bedtime.stories.kids.zentale.data.remote.StoryRemoteDataSource
import com.bedtime.stories.kids.zentale.domain.StoryRepository
import com.bedtime.stories.kids.zentale.domain.model.PaginatedResultBO
import com.bedtime.stories.kids.zentale.domain.model.Story
import com.bedtime.stories.kids.zentale.domain.model.StoryType
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.delay

class StoryRepositoryImpl(
    private val createStoryRemoteDataSource: CreateStoryRemoteDataSource,
    private val storyRemoteDataSource: StoryRemoteDataSource,
    private val storyLocalDataSource: StoryLocalDataSource
) : StoryRepository {

    override val storyType = storyLocalDataSource.storyType
    override val story = storyLocalDataSource.story
    override val allStories = storyLocalDataSource.allStories

    override suspend fun createStory(storyId: String, imageUrl: String, language: String) {
        storyLocalDataSource.saveStory(Story(status = "loading"))
        val storyRequest = CreateStoryRequest(
            imageUrl = imageUrl,
            languageOfTheStory = language,
            storyId = storyId
        )
        delay(10000)
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

    override suspend fun fetchStories() {
        storyRemoteDataSource.fetchStories().collect {
            storyLocalDataSource.saveAllStories(it.toPaginatedResultBO())
        }
    }
    override suspend fun fetchMoreStories(lastVisibleStory: DocumentSnapshot?) {
        storyRemoteDataSource.fetchMoreStories(lastVisibleStory).collect {
            storyLocalDataSource.saveAllStories(it.toPaginatedResultBO())
        }
    }

    private fun StoryResponse.toStory() = Story(
        storyId = storyId ?: "",
        storyImage = storyImage ?: "",
        storyTitle = storyTitle ?: "",
        storyContent = storyContent ?: "",
        status = status ?: "",
    )

    private fun PaginatedResult.toPaginatedResultBO() = PaginatedResultBO(
        stories = stories.map { it.toStory() },
        lastVisibleStory = lastVisibleStory
    )
}
