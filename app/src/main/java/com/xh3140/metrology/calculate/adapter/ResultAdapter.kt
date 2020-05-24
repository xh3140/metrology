package com.xh3140.metrology.calculate.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xh3140.core.base.CD2D
import com.xh3140.metrology.R
import kotlinx.android.synthetic.main.item_calculate_result.view.*

class ResultAdapter : ListAdapter<CD2D<String>, ResultAdapter.ViewHolder>(DiffCallBack) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    object DiffCallBack : DiffUtil.ItemCallback<CD2D<String>>() {
        override fun areItemsTheSame(oldItem: CD2D<String>, newItem: CD2D<String>): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CD2D<String>, newItem: CD2D<String>): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_calculate_result, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.textViewName.text = getItem(position).value1
        holder.itemView.textViewValue.text = getItem(position).value2
    }
}