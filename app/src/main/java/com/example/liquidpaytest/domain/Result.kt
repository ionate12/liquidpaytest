package com.example.liquidpaytest.domain

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error<out T>(val throwable: Throwable) : Result<T>()
}

// Mapping from T type result to another Type
inline fun <T, R> Result<T>.map(block: (T) -> R): Result<R> {
    return when (this) {
        is Result.Error -> Result.Error(this.throwable)
        is Result.Success -> Result.Success(block(this.data))
    }
}
