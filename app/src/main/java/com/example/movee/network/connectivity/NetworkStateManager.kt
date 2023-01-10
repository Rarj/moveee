package com.example.movee.network.connectivity

import android.util.Log
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object NetworkStateManager {

    private var _state = MutableSharedFlow<Boolean>()
    val state get() =  _state.asSharedFlow()

    suspend fun updateNetworkState(value : Boolean) {
        Log.e( "updateNetworkState: ", value.toString())
        _state.emit(value)
    }



}