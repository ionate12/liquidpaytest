package com.example.liquidpaytest.data.repository

import com.example.liquidpaytest.data.datasource.AppDataSource
import com.example.liquidpaytest.data.mapper.AppModelMapper
import com.example.liquidpaytest.domain.Result
import com.example.liquidpaytest.domain.map
import com.example.liquidpaytest.domain.model.QrGroup
import com.example.liquidpaytest.domain.model.QrModel
import com.example.liquidpaytest.domain.repository.AppRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * AppRepository Concrete class, where all of the mapping logic
 */
internal class AppRepositoryImpl : AppRepository, KoinComponent {
    private val appDts: AppDataSource by inject()
    private val mapper: AppModelMapper by inject()
    override suspend fun getQrList(): Result<List<QrModel>> {
        return appDts.getQrList().map { list -> list.map { mapper.toQrModel(it) } }
    }

    override suspend fun getQrGroup(): Result<List<QrGroup>> {
        return appDts.getQrGroup().map { list -> list.map { mapper.toQrGroupModel(it) } }
    }
}
