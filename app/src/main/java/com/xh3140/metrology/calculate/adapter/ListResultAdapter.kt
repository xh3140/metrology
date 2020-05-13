package com.xh3140.metrology.calculate.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xh3140.metrology.R
import kotlinx.android.synthetic.main.item_calculate_result.view.*

class ListResultAdapter :
    ListAdapter<ListResultAdapter.Item, ListResultAdapter.ViewHolder>(DiffCallBack) {

    data class Item(val name: String, val value: String)

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    object DiffCallBack : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.name == newItem.name && oldItem.value == newItem.value
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_calculate_result, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.textViewName.text = getItem(position).name
        holder.view.textViewValue.text = getItem(position).value
    }
}