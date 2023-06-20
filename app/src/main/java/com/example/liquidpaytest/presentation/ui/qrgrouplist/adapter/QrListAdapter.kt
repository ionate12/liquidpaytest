package com.example.liquidpaytest.presentation.ui.qrgrouplist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.liquidpaytest.databinding.ItemQrBinding
import com.example.liquidpaytest.presentation.ui.qrgrouplist.model.QrItem
import com.example.liquidpaytest.presentation.utils.DiffedListRVAdapter

class QrListAdapter(list: List<QrItem>) : DiffedListRVAdapter<QrItem, QrListAdapter.ViewHolder>(list) {

    override fun areItemsTheSame(oldItem: QrItem, newItem: QrItem): Boolean {
        return oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemQrBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemsList[position])
    }

    class ViewHolder(private val binding: ItemQrBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: QrItem) {
            binding.data = data
        }
    }
}
