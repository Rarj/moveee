package com.example.movee.feature.video

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movee.data.repository.video.Video
import com.example.movee.data.repository.video.VideoRepositoryImpl
import com.example.movee.network.ViewState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class VideoViewModel(
    private val repository: VideoRepositoryImpl
) : ViewModel() {

    private var _video = MutableSharedFlow<ViewState<Video?>>()
    val video get() = _video.asSharedFlow()

    suspend fun trailer(movieId: Int) {
        _video.emit(ViewState.Loading(null))
        viewModelScope.launch {
            val response = repository.video(movieId)
            response.collectLatest { videoResponse ->
                _video.emit(ViewState.Success(videoResponse.takeIf { it.results.isNotEmpty() }?.results?.first()))
            }
            response.catch { throwable ->
                _video.emit(ViewState.Error(null, throwable.message.toString()))
            }
        }
    }

}