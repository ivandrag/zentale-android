package com.bedtime.stories.kids.zentale.data.remote

import com.bedtime.stories.kids.zentale.data.model.CreateStoryRequest
import com.bedtime.stories.kids.zentale.data.model.CreateStoryResponse

interface CreateStoryRemoteDataSource {
    suspend fun createStory(storyRequest: CreateStoryRequest): Result<String>
}
