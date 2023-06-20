package com.example.liquidpaytest.presentation.ui.qrgrouplist

import com.example.liquidpaytest.BaseTest
import com.example.liquidpaytest.MockData
import com.example.liquidpaytest.MockData.mockedQrGroup
import com.example.liquidpaytest.MockData.mockedQrList
import com.example.liquidpaytest.presentation.ui.qrgrouplist.model.QrGroupItem
import com.example.liquidpaytest.presentation.utils.StringResolver
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Test
import org.koin.dsl.ModuleDeclaration

internal class CombineQrDataMapperTest : BaseTest<CombineQrDataMapper>() {
    @MockK
    private lateinit var stringResolver: StringResolver
    private val mockedString = "All Providers"
    private lateinit var result: List<QrGroupItem>
    override fun setupSut(): CombineQrDataMapper = koinTestRule.koin.get()

    override fun declareModule(): ModuleDeclaration = {
        single { stringResolver }
        factory { CombineQrDataMapper() }
    }

    override fun setup() {
        super.setup()
        every { stringResolver.getString(any()) } answers { mockedString }
        result = sut(MockData.mockedQrGroup, MockData.mockedQrList)
    }

    // Test groupQr logic.

    // 1.
    @Test
    fun qrGroupItemHasNoEmptyQrList() {
        assert(!result.any { it.qrList.isEmpty() })
        // 2. order should be by `position`
    }

    // 2.
    @Test
    fun qrGroupItemIsSortedByPosition() {
        val expectedOrder = listOf(1, 4, 2)
        assert(result.drop(1).map { it.id } == expectedOrder)
    }

    @Test
    fun qrGroupHasAllProvidersAtFirst() {
        assert(result.first().title == mockedString)
        verify(atLeast = 1) { stringResolver.getString(any()) }
        assert(result.first().qrList.count() == mockedQrList.count())
    }

    // Test data mapping
    @Test
    fun qrGroupToQrGroupItemMappingTest() {
        val groupItem = result[1]
        val groupModel = mockedQrGroup.first { groupItem.id == it.groupId }
        assert(groupModel.groupImage == groupItem.image)
        assert(groupModel.groupPosition == groupItem.position)
        assert(groupModel.groupName == groupItem.title)
    }

    @Test
    fun qrModelToQrItemMappingTest() {
        val qrItem = result[0].qrList[0]
        val qrModel = mockedQrList.first { qrItem.id == it.qrId }
        assert(qrItem.qrGroup == qrModel.qrGroup)
        assert(qrItem.position == qrModel.boostPosition.toIntOrNull())
        assert(qrItem.name == qrModel.qrName)
        assert(qrItem.image == qrModel.profileImage)
    }

    // Test Qr List

    @Test
    fun qrListAreGroupedCorrectly() {
        assert(result.drop(1).all { group -> group.qrList.map { it.qrGroup }.flatten().contains(group.id) })
        assert(result.first { it.id == 2 }.qrList.map { it.id } == listOf("A", "C"))
    }

    @Test
    fun qrListAreSortedByBoostPositionThenName() {
        assert(result[0].qrList.map { it.id } == listOf("A", "B", "C"))
    }
}
