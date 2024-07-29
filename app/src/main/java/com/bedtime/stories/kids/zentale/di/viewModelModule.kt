package com.bedtime.stories.kids.zentale.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.bedtime.stories.kids.zentale.presentation.login.LoginViewModel
import com.bedtime.stories.kids.zentale.presentation.MainViewModel
import com.bedtime.stories.kids.zentale.presentation.home.HomeViewModel
import com.bedtime.stories.kids.zentale.presentation.createStory.CreateStoryViewModel
import com.bedtime.stories.kids.zentale.presentation.story.StoryViewModel

val viewModelModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::MainViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::CreateStoryViewModel)
    viewModelOf(::StoryViewModel)
}