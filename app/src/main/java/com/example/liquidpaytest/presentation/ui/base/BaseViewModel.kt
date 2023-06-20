package com.example.liquidpaytest.presentation.ui.base

import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.liquidpaytest.domain.util.Dispatchers
import com.example.liquidpaytest.presentation.core.UiState
import com.example.liquidpaytest.presentation.utils.StringResolver
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseViewModel : ViewModel(), KoinComponent {
    private val stringResolver: StringResolver by inject()
    private val dispatchers: Dispatchers by inject()

    private val coroutineHandler = dispatchers.io() + CoroutineExceptionHandler { _, exception ->
        Log.e("Coroutine Exception", "Err: ${exception.stackTraceToString()}")
    }

    protected val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getString(@StringRes stringId: Int): String = stringResolver.getString(stringId)

    protected fun safeLaunch(block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch(
        context = coroutineHandler,
        block = block
    )
}
