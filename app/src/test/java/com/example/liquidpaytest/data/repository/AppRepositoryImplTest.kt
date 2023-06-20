package com.example.liquidpaytest.data.repository

import com.example.liquidpaytest.BaseTest
import com.example.liquidpaytest.data.datasource.AppDataSource
import com.example.liquidpaytest.data.mapper.AppModelMapper
import com.example.liquidpaytest.data.model.QrDto
import com.example.liquidpaytest.data.model.QrGroupDto
import com.example.liquidpaytest.domain.Result
import com.example.liquidpaytest.domain.repository.AppRepository
import io.mockk.called
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.koin.dsl.ModuleDeclaration

@ExperimentalCoroutinesApi
internal class AppRepositoryImplTest : BaseTest<AppRepository>() {

    private val appDts: AppDataSource = mockk()
    private val mapper: AppModelMapper = spyk(AppModelMapper())

    override fun setupSut(): AppRepository = koinTestRule.koin.get()

    override fun declareModule(): ModuleDeclaration = {
        single { appDts }
        single { mapper }
        single<AppRepository> { AppRepositoryImpl() }
    }

    @Test
    fun getQrListReturnsSuccess() = runTest {
        // when
        coEvery { appDts.getQrList() } coAnswers {
            Result.Success(qrListDto)
        }
        // given
        val result = sut.getQrList()
        // then
        assert(result is Result.Success)
        assert((result as Result.Success).data.count() == 2)
        verify(atLeast = 1) { mapper.toQrModel(any()) }
    }

    @Test
    fun getQrListReturnsError() = runTest {
        // when
        val exp = Throwable("test")
        coEvery { appDts.getQrList() } coAnswers {
            Result.Error(exp)
        }
        // given
        val result = sut.getQrList()
        // then
        assert((result as? Result.Error)?.throwable?.message == "test")
        verify { mapper.toQrModel(any()) wasNot called }
    }

    @Test
    fun getQrGroupReturnsSuccess() = runTest {
        // when
        coEvery { appDts.getQrGroup() } coAnswers {
            Result.Success(qrGroupListDto)
        }
        // given
        val result = sut.getQrGroup()
        // then
        assert(result is Result.Success)
        assert((result as Result.Success).data.count() == 2)
        verify(atLeast = 1) { mapper.toQrGroupModel(any()) }
    }

    @Test
    fun getQrGroupReturnsError() = runTest {
        // when
        val exp = Throwable("test")
        coEvery { appDts.getQrGroup() } coAnswers {
            Result.Error(exp)
        }
        // given
        val result = sut.getQrGroup()
        // then
        assert((result as? Result.Error)?.throwable?.message == "test")
        verify { mapper.toQrGroupModel(any()) wasNot called }
    }

    companion object {
        val qrListDto: List<QrDto> = listOf(
            QrDto(
                refNo = 5653,
                merchantAccInfo = "conclusionemque",
                profileImage = "deserunt",
                qrId = "veniam",
                qrName = "Salvatore Powers",
                status = "donec",
                scanType = "definitiones",
                qrGroup = "adversarium",
                qrAlias = "morbi",
                boostPosition = "scripserit",
                showConvertedAmountFlag = "ludus"
            ),
            QrDto(
                refNo = 5615,
                merchantAccInfo = "persequeris",
                profileImage = "vivamus",
                qrId = "persecuti",
                qrName = "Shana Ramos",
                status = "cubilia",
                scanType = "consetetur",
                qrGroup = "tale",
                qrAlias = "ius",
                boostPosition = "imperdiet",
                showConvertedAmountFlag = "ei"
            )
        )
        val qrGroupListDto: List<QrGroupDto> = listOf(
            QrGroupDto(
                groupId = 5828,
                groupName = "Jan Steele",
                groupPosition = 4833,
                groupImage = "iaculis"
            ),
            QrGroupDto(
                groupId = 4671,
                groupName = "Federico Humphrey",
                groupPosition = 2429,
                groupImage = "sollicitudin"
            )
        )
    }
}
