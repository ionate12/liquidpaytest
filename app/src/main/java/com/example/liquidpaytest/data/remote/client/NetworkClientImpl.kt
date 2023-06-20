package com.example.liquidpaytest.data.remote.client

import com.example.liquidpaytest.data.remote.BaseApi
import retrofit2.Retrofit
import kotlin.reflect.KClass

class NetworkClientImpl(private val retrofit: Retrofit) : NetworkClient {
    override fun <T : BaseApi> provideApi(clazz: KClass<T>): T {
        return retrofit.create(clazz.java)
    }
}
