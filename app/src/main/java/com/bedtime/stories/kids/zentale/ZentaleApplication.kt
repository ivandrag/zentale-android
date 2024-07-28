package com.bedtime.stories.kids.zentale

import android.app.Application
import com.bedtime.stories.kids.zentale.di.appModule
import com.bedtime.stories.kids.zentale.di.dataModule
import com.bedtime.stories.kids.zentale.di.domainModule
import com.bedtime.stories.kids.zentale.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ZentaleApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ZentaleApplication)
            modules(appModule, viewModelModule, domainModule, dataModule)
        }
    }
}