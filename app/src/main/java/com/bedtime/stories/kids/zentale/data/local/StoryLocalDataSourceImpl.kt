package com.bedtime.stories.kids.zentale.data.local

import com.bedtime.stories.kids.zentale.domain.model.Story
import com.bedtime.stories.kids.zentale.presentation.shared.model.StoryType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class StoryLocalDataSourceImpl : StoryLocalDataSource {
    private val _storyType = MutableStateFlow<StoryType>(StoryType.Default)
    override val storyType = _storyType.asStateFlow()

    private val _story = MutableStateFlow<Story?>(null)
    override val story = _story.asStateFlow()

    override suspend fun saveStoryType(type: StoryType) {
        _storyType.emit(type)
    }

    override suspend fun saveStory(story: Story?) {
        _story.emit(story)
    }
}
