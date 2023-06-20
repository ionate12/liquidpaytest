package com.example.liquidpaytest

import com.example.liquidpaytest.domain.model.QrGroup
import com.example.liquidpaytest.domain.model.QrModel

object MockData {
    val mockedQrGroup = listOf(
        QrGroup(groupId = 1, groupName = "Genaro Goff", groupPosition = 1, groupImage = "ante"),
        QrGroup(groupId = 2, groupName = "Rod Klein", groupPosition = 10, groupImage = "solet"),
        QrGroup(groupId = 3, groupName = "Test 3", groupPosition = 3, groupImage = "solet"),
        QrGroup(groupId = 4, groupName = "Test 4", groupPosition = 9, groupImage = "solet")
    )
    val mockedQrList = listOf(
        QrModel(
            refNo = 7008,
            merchantAccInfo = "liber",
            profileImage = "vis",
            qrId = "A",
            qrName = "Penelope O'Brien",
            status = "quam",
            scanType = "dicat",
            qrGroup = listOf(1, 2, 4),
            qrAlias = "ignota",
            boostPosition = "1",
            showConvertedAmountFlag = "eu"
        ),
        QrModel(
            refNo = 5629,
            merchantAccInfo = "postea",
            profileImage = "reque",
            qrId = "B",
            qrName = "Kerry Yang",
            status = "vivamus",
            scanType = "porta",
            qrGroup = listOf(),
            qrAlias = "verterem",
            boostPosition = "2",
            showConvertedAmountFlag = "periculis"
        ),
        QrModel(
            refNo = 8358,
            merchantAccInfo = "graeci",
            profileImage = "usu",
            qrId = "C",
            qrName = "Gina Steele",
            status = "curae",
            scanType = "idque",
            qrGroup = listOf(2, 4),
            qrAlias = "justo",
            boostPosition = "",
            showConvertedAmountFlag = "noluisse"
        )
    )
}
