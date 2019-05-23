package com.cardinal.cleanarchitecture

import android.app.Application
import com.cardinal.cleanarchitecture.di.domainModule
import com.cardinal.cleanarchitecture.di.repositoryModule
import com.cardinal.cleanarchitecture.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class CleanApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@CleanApp)
            modules(listOf(repositoryModule, domainModule, viewModule))
        }
    }
}
