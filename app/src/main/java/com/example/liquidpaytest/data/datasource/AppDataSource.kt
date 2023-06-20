package com.example.liquidpaytest.data.datasource

import com.example.liquidpaytest.data.model.QrDto
import com.example.liquidpaytest.data.model.QrGroupDto
import com.example.liquidpaytest.domain.Result

internal interface AppDataSource {
    suspend fun getQrList(): Result<List<QrDto>>
    suspend fun getQrGroup(): Result<List<QrGroupDto>>
}
