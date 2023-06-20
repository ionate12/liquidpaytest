package com.example.liquidpaytest.data.datasource.remote

import com.example.liquidpaytest.data.datasource.AppDataSource
import com.example.liquidpaytest.data.model.QrDto
import com.example.liquidpaytest.data.model.QrGroupDto
import com.example.liquidpaytest.data.remote.api.AppApi
import com.example.liquidpaytest.domain.Result
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class AppRemoteDataSourceImpl : AppDataSource, KoinComponent {
    private val appApi: AppApi by inject()
    override suspend fun getQrList(): Result<List<QrDto>> = handleRequest {
        appApi.getQrList().dataList
    }

    override suspend fun getQrGroup(): Result<List<QrGroupDto>> = handleRequest {
        appApi.getQrGroup()
    }

    private inline fun <T> handleRequest(block: () -> T): Result<T> {
        return try {
            Result.Success(block())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
