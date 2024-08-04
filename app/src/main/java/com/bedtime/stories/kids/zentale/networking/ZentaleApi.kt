package com.bedtime.stories.kids.zentale.networking

import com.bedtime.stories.kids.zentale.data.model.CreateStoryRequest
import com.bedtime.stories.kids.zentale.data.model.CreateStoryResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ZentaleApi {

    @POST(CREATE_STORY)
    suspend fun createStory(
        @Header(SHOULD_HAVE_AUTH_HEADERS) shouldHaveAuthHeaders: Boolean = true,
        @Body body: CreateStoryRequest
    ) : CreateStoryResponse
}
