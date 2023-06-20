package com.example.liquidpaytest.presentation.ui.qrgrouplist

import app.cash.turbine.test
import com.example.liquidpaytest.BaseTest
import com.example.liquidpaytest.MockData
import com.example.liquidpaytest.domain.Result
import com.example.liquidpaytest.domain.usecase.GetQrGroupListUseCase
import com.example.liquidpaytest.domain.usecase.GetQrListUseCase
import com.example.liquidpaytest.presentation.core.UiState
import com.example.liquidpaytest.presentation.ui.qrgrouplist.model.QrGroupItem
import com.example.liquidpaytest.presentation.utils.StringResolver
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.koin.dsl.ModuleDeclaration

internal class QrGroupListViewModelTest : BaseTest<QrGroupListViewModel>() {
    private val getQrListUseCase: GetQrListUseCase = mockk()
    private val getQrGroupListUseCase: GetQrGroupListUseCase = mockk()
    private val combineQrDataMapper: CombineQrDataMapper = mockk()

    private val stringResolver: StringResolver = mockk()

    override fun setupSut(): QrGroupListViewModel = koinTestRule.koin.get()

    override fun declareModule(): ModuleDeclaration = {
        factory { getQrListUseCase }
        factory { getQrGroupListUseCase }
        factory { combineQrDataMapper }
        factory { stringResolver }
        factory { QrGroupListViewModel() }
    }

    @Test
    fun testLoadDataReturnsSuccess() = runTest {
        val mockedQrGroupItem = listOf(
            QrGroupItem(
                id = 6603,
                position = 9728,
                title = "delenit",
                image = null,
                qrList = listOf()
            )
        )
        // when
        coEvery { getQrGroupListUseCase() } coAnswers { Result.Success(MockData.mockedQrGroup) }
        coEvery { getQrListUseCase() } coAnswers { Result.Success(MockData.mockedQrList) }
        every { combineQrDataMapper(any(), any()) } answers { mockedQrGroupItem }

        sut.uiState.test {
            sut.loadData()
            val state = awaitItem()
            val state2 = awaitItem()
            assert(state is UiState.Loading)
            assert(state2 is UiState.Success)
            assert(sut.qrGroupDatasource.value == mockedQrGroupItem)
        }

        coVerify(atLeast = 1) { getQrGroupListUseCase() }
        coVerify(atLeast = 1) { getQrListUseCase() }
        verify(atLeast = 1) { combineQrDataMapper(any(), any()) }

        // then
    }

    @Test
    fun testLoadDataReturnsError() = runTest {
        val mockedErrorMessage = "ERROR MESSAGE"
        coEvery { getQrGroupListUseCase() } coAnswers { Result.Error(Throwable()) }
        coEvery { getQrListUseCase() } coAnswers { Result.Success(MockData.mockedQrList) }
        every { combineQrDataMapper(any(), any()) } answers { listOf() }
        every { stringResolver.getString(any()) } answers { mockedErrorMessage }

        // test

        sut.uiState.test {
            sut.loadData()
            val state = awaitItem()
            val state2 = awaitItem()
            assert(state is UiState.Loading)
            assert(state2 is UiState.Error)
            assert((state2 as UiState.Error).errorMessage == mockedErrorMessage)
        }

        coVerify(atLeast = 1) { getQrGroupListUseCase() }
        coVerify(atLeast = 1) { getQrListUseCase() }
    }
}
