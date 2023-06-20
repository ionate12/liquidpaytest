package com.example.liquidpaytest.domain.di

import com.example.liquidpaytest.data.di.dataModule
import com.example.liquidpaytest.domain.util.Dispatchers
import com.example.liquidpaytest.domain.util.DispatchersImpl
import org.koin.dsl.module

val domainModules = dataModule + useCaseModule + module {
    single<Dispatchers> { DispatchersImpl() }
}
