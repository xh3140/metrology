package com.xh3140.metrology.calculate.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.xh3140.metrology.R
import com.xh3140.metrology.base.adapter.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.item_calculate_1d_data.view.*


class ListDataAdapter(items: ObservableArrayList<String>) : BaseRecyclerViewAdapter<String, ListDataAdapter.ViewHolder>(items) {

    private var mIsUpdateValue: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_calculate_1d_data, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val padding = itemCount.toString().length
        holder.itemView.textViewIndex.text = String.format("数据%0${padding}d", position + 1)
        holder.itemView.editTextValue.setText(mItems[position].toString())
    }

    override fun getItemCount(): Int = mItems.size

    override fun onChanged(sender: ObservableArrayList<String>) {
        if (!mIsUpdateValue) {
            notifyItemRangeChanged(0, mItems.size)
        }
    }

    override fun onItemRangeChanged(sender: ObservableArrayList<String>, positionStart: Int, itemCount: Int) {
        if (!mIsUpdateValue) {
            notifyItemRangeChanged(positionStart, itemCount)
        }
    }

    override fun onItemRangeInserted(sender: ObservableArrayList<String>, positionStart: Int, itemCount: Int) {
        super.onItemRangeInserted(sender, positionStart, itemCount)
        if (Regex("""^10+$""").matches(sender.size.toString())) {
            notifyItemRangeChanged(0, sender.size)
        }
        mRecyclerView?.scrollToPosition(sender.size - 1)
    }

    override fun onItemRangeRemoved(sender: ObservableArrayList<String>, positionStart: Int, itemCount: Int) {
        super.onItemRangeRemoved(sender, positionStart, itemCount)
        if (Regex("""^9+$""").matches(sender.size.toString())) {
            notifyItemRangeChanged(0, sender.size)
        }
        mRecyclerView?.scrollToPosition(sender.size - 1)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.editTextValue.addTextChangedListener {
                val position = adapterPosition
                val newValue = it.toString()
                if (position in 0 until mItems.size && mItems[position] != newValue) {
                    mIsUpdateValue = true
                    mItems[position] = newValue
                    mIsUpdateValue = false
                }
            }
        }
    }
}

