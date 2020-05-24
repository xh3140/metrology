package com.xh3140.metrology.calculate.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.ObservableArrayList
import com.xh3140.core.base.VD1D
import com.xh3140.metrology.R
import kotlinx.android.synthetic.main.item_calculate_1d_data.view.*

class D1DataAdapter(items: ObservableArrayList<VD1D<String>>) : DataAdapter<VD1D<String>>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_calculate_1d_data, parent, false)
        val viewHolder = ViewHolder(itemView)
        itemView.editTextValue.addTextChangedListener {
            val position = viewHolder.adapterPosition
            val newValue = it.toString()
            if (position in 0 until mItems.size && mItems[position].value != newValue) {
                mIsUpdateValue = true
                mItems[position].value = newValue
                mIsUpdateValue = false
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val padding = itemCount.toString().length
        holder.itemView.textViewName.text = String.format("数据%0${padding}d", position + 1)
        holder.itemView.editTextValue.setText(mItems[position].value)
    }
}

