package com.example.liquidpaytest.data.mapper

import com.example.liquidpaytest.data.model.QrDto
import com.example.liquidpaytest.data.model.QrGroupDto
import com.example.liquidpaytest.domain.model.QrGroup
import com.example.liquidpaytest.domain.model.QrModel

internal class AppModelMapper {

    fun toQrGroupModel(dto: QrGroupDto): QrGroup = with(dto) {
        QrGroup(
            groupId = groupId,
            groupName = groupName,
            groupPosition = groupPosition,
            groupImage = groupImage
        )
    }

    fun toQrModel(dto: QrDto): QrModel = with(dto) {
        QrModel(
            refNo = refNo,
            merchantAccInfo = merchantAccInfo,
            profileImage = profileImage,
            qrId = qrId,
            qrName = qrName,
            status = status,
            scanType = scanType,
            qrGroup = qrGroup.splitGroupId(),
            qrAlias = qrAlias,
            boostPosition = boostPosition,
            showConvertedAmountFlag = showConvertedAmountFlag
        )
    }

    private fun String.splitGroupId(): List<Int> {
        return this.split(",").mapNotNull { it.toIntOrNull() }
    }
}
