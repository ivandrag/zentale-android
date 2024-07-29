package com.bedtime.stories.kids.zentale.data.remote

import com.bedtime.stories.kids.zentale.domain.model.Story

interface StoryRemoteDataSource {
    suspend fun fetchDemoStory(id: String): Story?
}
