package com.example.liquidpaytest.data.di

import com.example.liquidpaytest.data.datasource.AppDataSource
import com.example.liquidpaytest.data.datasource.remote.AppRemoteDataSourceImpl
import com.example.liquidpaytest.data.mapper.AppModelMapper
import com.example.liquidpaytest.data.remote.api.AppApi
import com.example.liquidpaytest.data.remote.client.NetworkClient
import com.example.liquidpaytest.data.remote.client.NetworkClientImpl
import com.example.liquidpaytest.data.remote.client.RetrofitProvider
import com.example.liquidpaytest.data.repository.AppRepositoryImpl
import com.example.liquidpaytest.domain.repository.AppRepository
import org.koin.dsl.module

val dataModule = module {
    single<NetworkClient> { NetworkClientImpl(RetrofitProvider().provide()) }

    // dataSource + api
    single<AppApi> { get<NetworkClient>().provideApi(AppApi::class) }
    single<AppDataSource> { AppRemoteDataSourceImpl() }

    // mapper
    single { AppModelMapper() }

    // repository
    single<AppRepository> { AppRepositoryImpl() }
}
