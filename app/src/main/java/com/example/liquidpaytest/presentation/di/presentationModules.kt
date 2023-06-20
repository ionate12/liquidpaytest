package com.example.liquidpaytest.presentation.di

import com.example.liquidpaytest.presentation.ui.qrgrouplist.CombineQrDataMapper
import com.example.liquidpaytest.presentation.ui.qrgrouplist.QrGroupListViewModel
import com.example.liquidpaytest.presentation.utils.StringResolver
import com.example.liquidpaytest.presentation.utils.StringResolverImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModules = module {
    // view Model
    viewModel { QrGroupListViewModel() }

    factory { CombineQrDataMapper() }
    // helpers
    single<StringResolver> { StringResolverImpl(androidContext()) }
}
