package com.example.movee

import android.app.Application
import com.example.movee.di.appModules
import com.example.movee.di.dataSourceModule
import com.example.movee.di.repositoryModules
import com.example.movee.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MoveeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MoveeApplication)
            modules(appModules, viewModelModules, repositoryModules, dataSourceModule)
        }
    }

}