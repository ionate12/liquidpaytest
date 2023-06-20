package com.example.liquidpaytest.domain.usecase

import com.example.liquidpaytest.domain.Result
import com.example.liquidpaytest.domain.model.QrGroup
import com.example.liquidpaytest.domain.repository.AppRepository
import org.koin.core.component.inject

class GetQrGroupListUseCase : BaseUseCase() {
    private val repo: AppRepository by inject()
    suspend operator fun invoke(): Result<List<QrGroup>> = repo.getQrGroup()
}
