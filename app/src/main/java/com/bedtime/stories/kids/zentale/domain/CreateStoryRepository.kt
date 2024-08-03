package com.bedtime.stories.kids.zentale.domain

interface CreateStoryRepository {

    suspend fun createStory(storyId: String, imageUrl: String, language: String)
}
