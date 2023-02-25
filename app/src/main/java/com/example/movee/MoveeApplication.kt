package com.example.movee

import android.app.Application
import com.example.data.di.dataSourceInjector
import com.example.data.di.networkInjector
import com.example.data.di.repositoryInjector
import com.example.di.viewModelInjector
import com.example.domain.repo.di.useCaseInjector
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MoveeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MoveeApplication)
            modules(
                listOf(
                    networkInjector,
                    dataSourceInjector,
                    repositoryInjector,
                    useCaseInjector,
                    viewModelInjector,
                )
            )
        }
    }

}