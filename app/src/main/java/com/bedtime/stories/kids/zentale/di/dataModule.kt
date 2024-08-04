package com.bedtime.stories.kids.zentale.di

import com.bedtime.stories.kids.zentale.data.local.SavePhotoLocalDataSource
import com.bedtime.stories.kids.zentale.data.local.SavePhotoLocalDataSourceImpl
import com.bedtime.stories.kids.zentale.data.local.StoryLocalDataSource
import com.bedtime.stories.kids.zentale.data.local.StoryLocalDataSourceImpl
import com.bedtime.stories.kids.zentale.data.remote.CreateStoryRemoteDataSource
import com.bedtime.stories.kids.zentale.data.remote.CreateStoryRemoteDataSourceImpl
import com.bedtime.stories.kids.zentale.data.remote.StoryRemoteDataSource
import com.bedtime.stories.kids.zentale.data.remote.StoryRemoteDataSourceImpl
import org.koin.dsl.module

val dataModule = module {
    factory<SavePhotoLocalDataSource> { SavePhotoLocalDataSourceImpl(get()) }
    factory<StoryRemoteDataSource> { StoryRemoteDataSourceImpl(get(), get()) }
    factory<CreateStoryRemoteDataSource> { CreateStoryRemoteDataSourceImpl(get()) }
    single<StoryLocalDataSource> { StoryLocalDataSourceImpl() }
}
