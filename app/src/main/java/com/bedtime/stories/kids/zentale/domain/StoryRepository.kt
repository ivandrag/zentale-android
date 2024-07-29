package com.bedtime.stories.kids.zentale.domain

import com.bedtime.stories.kids.zentale.domain.model.Story

interface StoryRepository {
    suspend fun fetchDemoStory(id: String): Story?
}
