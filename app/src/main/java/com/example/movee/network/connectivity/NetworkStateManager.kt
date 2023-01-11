package com.example.movee.network.connectivity

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object NetworkStateManager {

    private var _state = MutableStateFlow(true)
    val state get() = _state.asStateFlow()

    suspend fun updateNetworkState(value: Boolean) {
        _state.emit(value)
    }


}