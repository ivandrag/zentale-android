package com.bedtime.stories.kids.zentale.presentation.createStory

sealed interface CreateStoryEvent {
    data object OnImageUploadSuccess: CreateStoryEvent
    data object OnImageUploadFailed : CreateStoryEvent
}
