package com.bedtime.stories.kids.zentale.di

import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

val appModule = module {
    single { FirebaseAuth.getInstance() }
}
