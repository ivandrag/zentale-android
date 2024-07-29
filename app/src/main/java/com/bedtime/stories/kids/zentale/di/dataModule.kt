package com.bedtime.stories.kids.zentale.di

import com.bedtime.stories.kids.zentale.data.local.SavePhotoLocalDataSource
import com.bedtime.stories.kids.zentale.data.local.SavePhotoLocalDataSourceImpl
import com.bedtime.stories.kids.zentale.data.remote.StoryRemoteDataSource
import com.bedtime.stories.kids.zentale.data.remote.StoryRemoteDataSourceImpl
import org.koin.dsl.module

val dataModule = module {
    factory<SavePhotoLocalDataSource> { SavePhotoLocalDataSourceImpl(get()) }
    factory<StoryRemoteDataSource> { StoryRemoteDataSourceImpl() }
}