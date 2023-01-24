package com.example.data.di

import com.example.data.network.NetworkBuilder
import com.example.data.repository.HomeRepositoryImpl
import com.example.data.source.HomeDataSource
import com.example.domain.repo.home.HomeRepository
import org.koin.dsl.module


val networkInjector = module {
    single { NetworkBuilder().provideRetrofit() }
    single { NetworkBuilder().provideService(get()) }
}

val repositoryInjector = module {
    single<HomeRepository> { HomeRepositoryImpl(get()) }
}

val dataSourceInjector = module {
    single { HomeDataSource(get()) }
}