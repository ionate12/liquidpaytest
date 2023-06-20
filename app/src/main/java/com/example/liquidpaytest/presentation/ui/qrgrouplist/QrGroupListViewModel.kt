package com.example.liquidpaytest.presentation.ui.qrgrouplist

import com.example.liquidpaytest.R
import com.example.liquidpaytest.domain.Result
import com.example.liquidpaytest.domain.model.QrGroup
import com.example.liquidpaytest.domain.model.QrModel
import com.example.liquidpaytest.domain.usecase.GetQrGroupListUseCase
import com.example.liquidpaytest.domain.usecase.GetQrListUseCase
import com.example.liquidpaytest.presentation.core.UiState
import com.example.liquidpaytest.presentation.ui.base.BaseViewModel
import com.example.liquidpaytest.presentation.ui.qrgrouplist.model.QrGroupItem
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.inject

class QrGroupListViewModel : BaseViewModel() {
    private val getQrListUseCase: GetQrListUseCase by inject()
    private val getQrGroupListUseCase: GetQrGroupListUseCase by inject()
    private val combineQrDataMapper: CombineQrDataMapper by inject()

    private val _qrGroupDatasource: MutableStateFlow<List<QrGroupItem>> = MutableStateFlow(listOf())
    val qrGroupDatasource = _qrGroupDatasource.asStateFlow()

    fun loadData() = safeLaunch {
        _uiState.emit(UiState.Loading)
        val qrGroupTask = async { getQrGroupListUseCase() }
        val qrListTask = async { getQrListUseCase() }
        val qrGroupData: List<QrGroup>
        val qrListData: List<QrModel>
        when (val rs = qrGroupTask.await()) {
            is Result.Error -> {
                handleError(rs)
                qrListTask.cancel()
                return@safeLaunch
            }
            is Result.Success -> {
                qrGroupData = rs.data
            }
        }
        when (val rs = qrListTask.await()) {
            is Result.Error -> {
                handleError(rs)
                return@safeLaunch
            }
            is Result.Success -> {
                qrListData = rs.data
            }
        }
        processData(qrGroupData, qrListData)
    }

    private fun handleError(errorResult: Result.Error<Any>) {
        errorResult.throwable.printStackTrace() // Debug only
        _uiState.tryEmit(UiState.Error(getString(R.string.qr_group_error)))
    }

    private suspend fun processData(groups: List<QrGroup>, qr: List<QrModel>) {
        val dataSource = combineQrDataMapper(groups, qr)
        _uiState.emit(UiState.Success)
        _qrGroupDatasource.emit(dataSource)
    }
}
