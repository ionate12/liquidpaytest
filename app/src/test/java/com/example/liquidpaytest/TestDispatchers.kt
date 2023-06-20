package com.example.liquidpaytest

import com.example.liquidpaytest.domain.util.Dispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@OptIn(ExperimentalCoroutinesApi::class)
class TestDispatchers : Dispatchers {
    override fun io(): CoroutineDispatcher = UnconfinedTestDispatcher()
    override fun main(): CoroutineDispatcher = UnconfinedTestDispatcher()
    override fun default(): CoroutineDispatcher = UnconfinedTestDispatcher()
}
