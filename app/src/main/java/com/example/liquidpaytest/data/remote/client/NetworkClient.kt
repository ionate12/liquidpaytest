package com.example.liquidpaytest.data.remote.client

import com.example.liquidpaytest.data.remote.BaseApi
import kotlin.reflect.KClass

interface NetworkClient {
    fun <T : BaseApi> provideApi(clazz: KClass<T>): T
}
