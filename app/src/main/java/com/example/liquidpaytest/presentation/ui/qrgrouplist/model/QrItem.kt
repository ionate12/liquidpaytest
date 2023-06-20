package com.example.liquidpaytest.presentation.ui.qrgrouplist.model

data class QrItem(
    val id: String,
    val position: Int?,
    val image: String,
    val name: String,
    val qrGroup: List<Int>
)
