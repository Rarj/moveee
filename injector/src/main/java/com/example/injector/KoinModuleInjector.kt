package com.example.injector

import org.koin.dsl.module

val appModules = module {
//    single { NetworkBuilder().provideRetrofit() }
//    single { NetworkBuilder().provideService(get()) }
}

val viewModelModules = module {
//    viewModel { VideoViewModel(get()) }
}

val repositoryModules = module {
//    single { VideoRepositoryImpl(get()) }
}

val dataSourceModule = module {
//    single { VideoDataSource(get()) }
}