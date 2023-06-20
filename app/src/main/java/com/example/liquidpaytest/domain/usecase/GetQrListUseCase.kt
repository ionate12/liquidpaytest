package com.example.liquidpaytest.domain.usecase

import com.example.liquidpaytest.domain.Result
import com.example.liquidpaytest.domain.model.QrModel
import com.example.liquidpaytest.domain.repository.AppRepository
import org.koin.core.component.inject

class GetQrListUseCase : BaseUseCase() {
    private val repo: AppRepository by inject()
    suspend operator fun invoke(): Result<List<QrModel>> = repo.getQrList()
}
