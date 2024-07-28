package com.bedtime.stories.kids.zentale.di

import com.bedtime.stories.kids.zentale.data.domain.SavePhotoToGalleryRepositoryImpl
import com.bedtime.stories.kids.zentale.domain.SavePhotoToGalleryRepository
import org.koin.dsl.module

val domainModule = module {
    factory<SavePhotoToGalleryRepository> { SavePhotoToGalleryRepositoryImpl(get()) }
}
