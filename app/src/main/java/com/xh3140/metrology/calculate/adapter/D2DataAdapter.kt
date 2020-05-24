package com.xh3140.metrology.calculate.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.ObservableArrayList
import com.xh3140.core.base.VD2D
import com.xh3140.metrology.R
import kotlinx.android.synthetic.main.item_calculate_2d_data.view.*

class D2DataAdapter(items: ObservableArrayList<VD2D<String>>) : DataAdapter<VD2D<String>>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_calculate_2d_data, parent, false)
        val viewHolder = ViewHolder(itemView)
        itemView.editTextValueX.addTextChangedListener {
            val position = viewHolder.adapterPosition
            val newValue = it.toString()
            if (position in 0 until mItems.size && mItems[position].value1 != newValue) {
                mIsUpdateValue = true
                mItems[position].value1 = newValue
                mIsUpdateValue = false
            }
        }
        itemView.editTextValueY.addTextChangedListener {
            val position = viewHolder.adapterPosition
            val newValue = it.toString()
            if (position in 0 until mItems.size && mItems[position].value2 != newValue) {
                mIsUpdateValue = true
                mItems[position].value2 = newValue
                mIsUpdateValue = false
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val padding = itemCount.toString().length
        holder.itemView.textViewNameX.text = String.format("数据X%0${padding}d", position + 1)
        holder.itemView.textViewNameY.text = String.format("数据Y%0${padding}d", position + 1)
        holder.itemView.editTextValueX.setText(mItems[position].value1)
        holder.itemView.editTextValueX.setText(mItems[position].value2)
    }
}

