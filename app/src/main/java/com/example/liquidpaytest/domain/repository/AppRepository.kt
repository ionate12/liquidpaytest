package com.example.liquidpaytest.domain.repository

import com.example.liquidpaytest.domain.Result
import com.example.liquidpaytest.domain.model.QrGroup
import com.example.liquidpaytest.domain.model.QrModel

interface AppRepository {
    suspend fun getQrList(): Result<List<QrModel>>
    suspend fun getQrGroup(): Result<List<QrGroup>>
}
