package com.example.liquidpaytest.presentation

import android.app.Application
import com.example.liquidpaytest.domain.di.domainModules
import com.example.liquidpaytest.presentation.di.presentationModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        // init DI
        startKoin {
            androidContext(this@App)
            modules(domainModules + presentationModules)
        }
    }
}
