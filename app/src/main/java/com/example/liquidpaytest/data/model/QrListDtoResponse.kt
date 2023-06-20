package com.example.liquidpaytest.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class QrListDtoResponse(
    @SerialName("CODE")
    val code: Int,
    @SerialName("DESCRIPTION")
    val description: String,
    @SerialName("DATALIST")
    val dataList: List<QrDto>,
    @SerialName("Internal_message")
    val internalMessage: String
)
