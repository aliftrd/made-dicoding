package com.github.aliftrd.core.data.lib

sealed class RemoteResponse<out R> {
    data class Success<out T>(val data: T) : RemoteResponse<T>()
    data class Error(val errorMessage: String) : RemoteResponse<Nothing>()
    object Loading : RemoteResponse<Nothing>()
    object Empty : RemoteResponse<Nothing>()
}