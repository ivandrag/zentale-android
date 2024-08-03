package com.bedtime.stories.kids.zentale.data.remote

import com.bedtime.stories.kids.zentale.data.model.CreateStoryRequest
import com.bedtime.stories.kids.zentale.networking.ZentaleApi

class CreateStoryRemoteDataSourceImpl(
    private val zentaleApi: ZentaleApi
): CreateStoryRemoteDataSource {

    override suspend fun createStory(storyRequest: CreateStoryRequest) = zentaleApi.createStory(
        body = storyRequest
    )
}
