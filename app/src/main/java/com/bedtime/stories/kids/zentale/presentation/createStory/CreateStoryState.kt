package com.bedtime.stories.kids.zentale.presentation.createStory

import android.graphics.Bitmap

data class CreateStoryState(
    val isLoading: Boolean = false,
    val capturedImage: Bitmap? = null,
    val language: String = "English"
)
