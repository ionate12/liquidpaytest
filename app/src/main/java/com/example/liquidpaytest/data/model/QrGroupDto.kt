package com.example.liquidpaytest.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class QrGroupDto(
    @SerialName("Group_Id")
    val groupId: Int,
    @SerialName("Group_Name")
    val groupName: String,
    @SerialName("Group_Position")
    val groupPosition: Int,
    @SerialName("Group_Image")
    val groupImage: String
)
