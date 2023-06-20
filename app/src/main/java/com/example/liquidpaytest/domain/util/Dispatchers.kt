package com.example.liquidpaytest.domain.util

import kotlinx.coroutines.CoroutineDispatcher

interface Dispatchers {
    fun io(): CoroutineDispatcher
    fun main(): CoroutineDispatcher
    fun default(): CoroutineDispatcher
}
