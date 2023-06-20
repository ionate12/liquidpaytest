package com.example.liquidpaytest.presentation.utils

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

// Integrate diff callback to Adapter
abstract class DiffedListRVAdapter<T, VH : RecyclerView.ViewHolder>(
    protected var itemsList: List<T>
) : RecyclerView.Adapter<VH>() {

    fun setItemList(list: List<T>) {
        val oldList = itemsList
        val diffResult = DiffUtil.calculateDiff(DiffCallback(oldList, list))
        itemsList = list
        diffResult.dispatchUpdatesTo(this)
    }

    abstract fun areItemsTheSame(oldItem: T, newItem: T): Boolean
    open fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    internal inner class DiffCallback(
        private var oldList: List<T>,
        private var newList: List<T>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return this@DiffedListRVAdapter.areItemsTheSame(
                oldList[oldItemPosition],
                newList[newItemPosition]
            )
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return this@DiffedListRVAdapter.areContentsTheSame(
                oldList[oldItemPosition],
                newList[newItemPosition]
            )
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
}
