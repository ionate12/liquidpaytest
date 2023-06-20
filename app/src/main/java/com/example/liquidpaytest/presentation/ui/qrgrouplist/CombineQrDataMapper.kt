package com.example.liquidpaytest.presentation.ui.qrgrouplist

import com.example.liquidpaytest.R
import com.example.liquidpaytest.domain.model.QrGroup
import com.example.liquidpaytest.domain.model.QrModel
import com.example.liquidpaytest.presentation.ui.qrgrouplist.model.QrGroupItem
import com.example.liquidpaytest.presentation.ui.qrgrouplist.model.QrItem
import com.example.liquidpaytest.presentation.utils.StringResolver
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * Sorting and grouping logic will operate in this useCase
 */
class CombineQrDataMapper : KoinComponent {
    private val stringResolver: StringResolver by inject()

    operator fun invoke(groups: List<QrGroup>, qrList: List<QrModel>): List<QrGroupItem> {
        val sortedQr = qrList
            .asSequence()
            .mapToItem()
            .sortedWith(qrItemComparator())
            .toList()
        val sortedGroup = groups
            .asSequence()
            .mapToItem(sortedQr)
            .filter { it.qrList.isNotEmpty() }
            .toSet() // remove duplications if needed
            .sortedWith(qrGroupItemComparator()) // sorted
        val allProviders = QrGroupItem(
            id = -1,
            position = -1,
            title = stringResolver.getString(R.string.all_providers),
            image = null,
            qrList = sortedQr
        )
        return listOf(allProviders) + sortedGroup
    }

    private fun Sequence<QrModel>.mapToItem(): Sequence<QrItem> = map {
        QrItem(
            id = it.qrId,
            position = it.boostPosition.toIntOrNull(),
            image = it.profileImage,
            name = it.qrName,
            qrGroup = it.qrGroup
        )
    }

    private fun Sequence<QrGroup>.mapToItem(qrItems: List<QrItem>): Sequence<QrGroupItem> = map {
        QrGroupItem(
            id = it.groupId,
            position = it.groupPosition,
            title = it.groupName,
            image = it.groupImage,
            qrList = qrItems.filter { qr -> qr.qrGroup.contains(it.groupId) }
        )
    }

    private fun qrItemComparator() = Comparator<QrItem> { first, sec ->
        val firstPosInt = first.position ?: Int.MAX_VALUE
        val secPosInt = sec.position ?: Int.MAX_VALUE
        when (val value = firstPosInt.compareTo(secPosInt)) {
            0 -> first.name.compareTo(sec.name)
            else -> value
        }
    }

    private fun qrGroupItemComparator() = Comparator<QrGroupItem> { first, sec ->
        first.position.compareTo(sec.position)
    }
}
