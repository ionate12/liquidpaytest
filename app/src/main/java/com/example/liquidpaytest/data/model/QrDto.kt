package com.example.liquidpaytest.data.model
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class QrDto(
    @SerialName("REFNO")
    val refNo: Int,
    @SerialName("MERCHANTACCINFO")
    val merchantAccInfo: String,
    @SerialName("PROFILEIMAGE")
    val profileImage: String,
    @SerialName("QRID")
    val qrId: String,
    @SerialName("QRNAME")
    val qrName: String,
    @SerialName("STATUS")
    val status: String,
    @SerialName("SCANTYPE")
    val scanType: String,
    @SerialName("QRGROUP")
    val qrGroup: String,
    @SerialName("QRALIAS")
    val qrAlias: String,
    @SerialName("BOOSTPOSITION")
    val boostPosition: String,
    @SerialName("SHOWCONVERTEDAMOUNTFLAG")
    val showConvertedAmountFlag: String
)
