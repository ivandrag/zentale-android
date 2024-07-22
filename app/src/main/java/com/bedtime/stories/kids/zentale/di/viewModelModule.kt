package com.bedtime.stories.kids.zentale.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.bedtime.stories.kids.zentale.presentation.login.LoginViewModel
import com.bedtime.stories.kids.zentale.presentation.MainViewModel

val viewModelModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::MainViewModel)
}