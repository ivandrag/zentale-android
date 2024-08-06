package com.bedtime.stories.kids.zentale.domain.model

import com.google.firebase.firestore.DocumentSnapshot

data class PaginatedResultBO(
    val stories: List<Story>,
    val lastVisibleStory: DocumentSnapshot?
)
