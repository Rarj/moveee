package com.example.movee.di

import com.example.movee.data.repository.caster.CasterDataSource
import com.example.movee.data.repository.caster.CasterRepositoryImpl
import com.example.movee.data.repository.detail.DetailMovieDataSource
import com.example.movee.data.repository.detail.DetailMovieRepositoryImpl
import com.example.movee.data.repository.discover.DiscoverMovieDataSource
import com.example.movee.data.repository.discover.DiscoverMovieRepositoryImpl
import com.example.movee.data.repository.genre.GenreDataSource
import com.example.movee.data.repository.genre.GenreRepositoryImpl
import com.example.movee.data.repository.review.ReviewDataSource
import com.example.movee.data.repository.review.ReviewRepositoryImpl
import com.example.movee.data.repository.video.VideoDataSource
import com.example.movee.data.repository.video.VideoRepositoryImpl
import com.example.movee.feature.caster.CasterViewModel
import com.example.movee.feature.caster.review.ReviewViewModel
import com.example.movee.feature.detail.DetailMovieViewModel
import com.example.movee.feature.discovermovie.DiscoverMovieViewModel
import com.example.movee.feature.genre.GenreViewModel
import com.example.movee.feature.video.VideoViewModel
import com.example.movee.network.NetworkBuilder
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    single { NetworkBuilder().provideRetrofit() }
    single { NetworkBuilder().provideService(get()) }
}

val viewModelModules = module {
    viewModel { GenreViewModel(get()) }
    viewModel { DiscoverMovieViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
    viewModel { CasterViewModel(get()) }
    viewModel { ReviewViewModel(get()) }
    viewModel { VideoViewModel(get()) }
}

val repositoryModules = module {
    single { GenreRepositoryImpl(get()) }
    single { DiscoverMovieRepositoryImpl(get()) }
    single { DetailMovieRepositoryImpl(get()) }
    single { CasterRepositoryImpl(get()) }
    single { ReviewRepositoryImpl(get()) }
    single { VideoRepositoryImpl(get()) }
}

val dataSourceModule = module {
    single { GenreDataSource(get()) }
    single { DiscoverMovieDataSource(get()) }
    single { DetailMovieDataSource(get()) }
    single { CasterDataSource(get()) }
    single { ReviewDataSource(get()) }
    single { VideoDataSource(get()) }
}