package com.example.liquidpaytest.data.remote.api

import com.example.liquidpaytest.data.model.QrGroupDto
import com.example.liquidpaytest.data.model.QrListDtoResponse
import com.example.liquidpaytest.data.remote.BaseApi
import retrofit2.http.GET
import retrofit2.http.Headers

internal interface AppApi : BaseApi {

    @GET("https://my.api.mockaroo.com/qrlist?key=62d1d400")
    suspend fun getQrList(): QrListDtoResponse

    @Headers("$liquidApiKeyLabel: $liquidApiKey")
    @GET("https://liquidpay-dev.apigee.net/openapi/v1/lookup/qr/qrgroup")
    suspend fun getQrGroup(): List<QrGroupDto>

    companion object {
        private const val liquidApiKeyLabel = "Liquid-Api-Key"
        private const val liquidApiKey = "zxu3VGDG5MvH1nyNQR8QsrS4qVFGEfy8"
    }
}
