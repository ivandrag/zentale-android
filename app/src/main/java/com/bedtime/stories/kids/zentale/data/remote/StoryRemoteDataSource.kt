package com.bedtime.stories.kids.zentale.data.remote

import com.bedtime.stories.kids.zentale.data.model.StoryResponse
import com.bedtime.stories.kids.zentale.domain.model.Story
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.flow.Flow

interface StoryRemoteDataSource {
    suspend fun fetchDemoStory(id: String): Flow<Story?>
    suspend fun fetchStory(id: String): Flow<StoryResponse?>
    suspend fun fetchStories(): Flow<PaginatedResult>
    suspend fun fetchMoreStories(lastVisibleStory: DocumentSnapshot?): Flow<PaginatedResult>
}
