package com.example.liquidpaytest.presentation.utils

import androidx.annotation.StringRes

interface StringResolver {
    fun getString(@StringRes stringRes: Int): String
}
