package com.example.liquidpaytest.presentation.utils

import android.content.Context

class StringResolverImpl(private val context: Context) : StringResolver {
    override fun getString(stringRes: Int): String = context.getString(stringRes)
}
