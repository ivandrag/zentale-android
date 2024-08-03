package com.bedtime.stories.kids.zentale.di

import com.bedtime.stories.kids.zentale.networking.provideHttpLoggingInterceptor
import com.bedtime.stories.kids.zentale.networking.provideOkHttp
import com.bedtime.stories.kids.zentale.networking.provideRetrofit
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import org.koin.core.qualifier.named
import org.koin.dsl.module

val zentaleStorageQualifier = named("ZentaleStorage")

val appModule = module {
    single { FirebaseAuth.getInstance() }
    single(zentaleStorageQualifier) {
        FirebaseStorage.getInstance(
            "gs://zentale.appspot.com"
        )
    }
    single { FirebaseFirestore.getInstance() }
    single { provideHttpLoggingInterceptor() }
    single { provideOkHttp(get(), get()) }
    single { provideRetrofit(get()) }
}
