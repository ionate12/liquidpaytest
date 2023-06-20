package com.example.liquidpaytest.domain.util

import kotlinx.coroutines.CoroutineDispatcher

class DispatchersImpl : Dispatchers {
    override fun io(): CoroutineDispatcher = kotlinx.coroutines.Dispatchers.IO

    override fun main(): CoroutineDispatcher = kotlinx.coroutines.Dispatchers.Main

    override fun default(): CoroutineDispatcher = kotlinx.coroutines.Dispatchers.Default
}
