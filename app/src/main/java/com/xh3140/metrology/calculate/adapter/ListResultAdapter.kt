package com.xh3140.metrology.calculate.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xh3140.metrology.R
import com.xh3140.metrology.calculate.ResultItem
import kotlinx.android.synthetic.main.item_calculate_result.view.*

class ListResultAdapter : ListAdapter<ResultItem, ListResultAdapter.ViewHolder>(DiffCallBack) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    object DiffCallBack : DiffUtil.ItemCallback<ResultItem>() {
        override fun areItemsTheSame(oldItem: ResultItem, newItem: ResultItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ResultItem, newItem: ResultItem): Boolean {
            return oldItem.name == newItem.name && oldItem.value == newItem.value
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_calculate_result, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.textViewName.text = getItem(position).name
        holder.itemView.textViewValue.text = getItem(position).value
    }
}