package com.example.liquidpaytest.domain.di

import com.example.liquidpaytest.domain.usecase.GetQrGroupListUseCase
import com.example.liquidpaytest.domain.usecase.GetQrListUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetQrGroupListUseCase() }
    factory { GetQrListUseCase() }
}
