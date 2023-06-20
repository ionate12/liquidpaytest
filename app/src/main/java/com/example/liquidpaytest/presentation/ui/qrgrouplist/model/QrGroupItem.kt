package com.example.liquidpaytest.presentation.ui.qrgrouplist.model

data class QrGroupItem(
    val id: Int,
    val position: Int,
    val title: String,
    val image: String?,
    val qrList: List<QrItem>
) {
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
