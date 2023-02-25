package com.example.data.di

import com.example.data.network.ApiInterface
import com.example.data.network.NetworkBuilder
import com.example.data.repository.HomeRepositoryImpl
import com.example.data.source.HomeDataSource
import com.example.domain.repo.home.HomeRepository
import org.koin.dsl.module
import retrofit2.Retrofit


val networkInjector = module {
    single<Retrofit> { NetworkBuilder().provideRetrofit() }
    single<ApiInterface> { NetworkBuilder().provideService(get()) }
}

val repositoryInjector = module {
    single<HomeRepository> { HomeRepositoryImpl(get()) }
}

val dataSourceInjector = module {
    single { HomeDataSource() }
}