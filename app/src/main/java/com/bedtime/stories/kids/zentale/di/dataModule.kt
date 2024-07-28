package com.bedtime.stories.kids.zentale.di

import com.bedtime.stories.kids.zentale.data.local.SavePhotoLocalDataSource
import com.bedtime.stories.kids.zentale.data.local.SavePhotoLocalDataSourceImpl
import org.koin.dsl.module

val dataModule = module {
    factory<SavePhotoLocalDataSource> { SavePhotoLocalDataSourceImpl(get()) }
}