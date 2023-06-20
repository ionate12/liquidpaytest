package com.example.liquidpaytest.data.remote.client

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

class RetrofitProvider {

    fun provide(): Retrofit = Retrofit.Builder()
        .baseUrl("https://my.api.mockaroo.com/") // This is not being used if in Api declaration we declare full url path.
        .client(httpClient())
        .addConverterFactory(converterFactory())
        .build()

    private fun httpClient(): OkHttpClient = OkHttpClient.Builder().build()

    private fun converterFactory(): Converter.Factory {
        val contentType = "application/json"
        return Json.asConverterFactory(MediaType.get(contentType))
    }
}
