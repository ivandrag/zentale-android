package com.bedtime.stories.kids.zentale.di

import com.bedtime.stories.kids.zentale.data.domain.SavePhotoToGalleryRepositoryImpl
import com.bedtime.stories.kids.zentale.data.domain.StoryRepositoryImpl
import com.bedtime.stories.kids.zentale.domain.SavePhotoToGalleryRepository
import com.bedtime.stories.kids.zentale.domain.StoryRepository
import org.koin.dsl.module

val domainModule = module {
    factory<SavePhotoToGalleryRepository> { SavePhotoToGalleryRepositoryImpl(get()) }
    factory<StoryRepository> { StoryRepositoryImpl(get()) }
}
