package com.example.liquidpaytest.presentation.core

sealed class UiState {
    object Loading : UiState()
    object Success : UiState()
    data class Error(val errorMessage: String) : UiState()
}
