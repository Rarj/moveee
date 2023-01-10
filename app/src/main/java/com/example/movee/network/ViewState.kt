package com.example.movee.network

sealed class ViewState<out T> {
    data class Loading<T>(val data: T?): ViewState<T>()
    data class Success<T>(val data: T): ViewState<T>()
    data class Error<T>(val data: T?, val message: String): ViewState<T>()
}
