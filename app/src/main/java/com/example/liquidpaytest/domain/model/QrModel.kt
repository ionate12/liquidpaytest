package com.example.liquidpaytest.domain.model

data class QrModel(
    val refNo: Int,
    val merchantAccInfo: String,
    val profileImage: String,
    val qrId: String,
    val qrName: String,
    val status: String,
    val scanType: String,
    val qrGroup: List<Int>,
    val qrAlias: String,
    val boostPosition: String,
    val showConvertedAmountFlag: String
)
