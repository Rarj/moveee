package com.example.domain.repo.di

import com.example.domain.repo.home.HomeUseCase
import org.koin.dsl.module

val useCaseInjector = module {
    single { HomeUseCase() }
}