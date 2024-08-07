package com.bedtime.stories.kids.zentale.data.model

import com.google.firebase.firestore.DocumentSnapshot

data class PaginatedResult(
    val stories: List<StoryResponse>,
    val lastVisibleStory: DocumentSnapshot?
)
