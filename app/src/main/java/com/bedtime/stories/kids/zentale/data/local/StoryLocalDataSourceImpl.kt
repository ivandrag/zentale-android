package com.bedtime.stories.kids.zentale.data.local

import com.bedtime.stories.kids.zentale.presentation.shared.model.StoryType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class StoryLocalDataSourceImpl : StoryLocalDataSource {
    private val _storyType = MutableStateFlow<StoryType>(StoryType.Default)
    override val storyType = _storyType.asStateFlow()

    override suspend fun saveStoryType(type: StoryType) {
        _storyType.emit(type)
    }
}