package com.example.liquidpaytest.presentation.ui.qrgrouplist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.liquidpaytest.databinding.ItemQrGroupBinding
import com.example.liquidpaytest.presentation.ui.qrgrouplist.model.QrGroupItem
import com.example.liquidpaytest.presentation.utils.DiffedListRVAdapter

class QrGroupListAdapter(list: List<QrGroupItem> = listOf()) :
    DiffedListRVAdapter<QrGroupItem, QrGroupListAdapter.ViewHolder>(list) {

    override fun areItemsTheSame(oldItem: QrGroupItem, newItem: QrGroupItem): Boolean {
        return oldItem == newItem // this can be enhanced
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemQrGroupBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemsList[position])
    }

    class ViewHolder(private val binding: ItemQrGroupBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: QrGroupItem) {
            binding.data = data
            binding.qrRv.adapter = QrListAdapter(data.qrList)
        }
    }
}
