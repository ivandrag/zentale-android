package com.bedtime.stories.kids.zentale.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.bedtime.stories.kids.zentale.R

class HomeViewModel : ViewModel() {
    val allImages = mutableStateOf(mapOf<String, Int>())
    val selectedStoryId = mutableStateOf<String?>(null)
    val isStoryViewPresented = mutableStateOf(false)

    private val storyIds = listOf(
        "0112295a-2a5c-46a5-bdfb-b2bb10f06635",
        "d70100ec-0cf5-433e-b0d5-63824cfa4611",
        "3b2b8c55-4adf-4b84-8c80-6b2be0d7313a",
        "3a467e21-aca4-4f1e-9a1a-b4d2c16f6aa9",
        "d76e80a4-d48c-4cfb-9c5b-eedb7c1b115c"
    )

    init {
        loadImages()
    }

    private fun loadImages() {
        val images = storyIds.mapIndexed { index, storyId ->
            val imageResId = when (index + 1) {
                1 -> R.drawable.first
                2 -> R.drawable.second
                3 -> R.drawable.third
                4 -> R.drawable.fourth
                5 -> R.drawable.fifth
                else -> null
            }
            if (imageResId != null) storyId to imageResId else null
        }.filterNotNull().toMap()
        allImages.value = images
    }
}
