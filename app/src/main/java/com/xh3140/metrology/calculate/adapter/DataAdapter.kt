package com.xh3140.metrology.calculate.adapter

import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.xh3140.metrology.base.adapter.BaseRecyclerViewAdapter

abstract class DataAdapter<M>(items: ObservableArrayList<M>) : BaseRecyclerViewAdapter<M, DataAdapter.ViewHolder>(items) {

    protected var mIsUpdateValue: Boolean = false
    private val mRegexSub: Regex = Regex("""^9+$""")
    private val mRegexAdd: Regex = Regex("""^10+$""")

    override fun onChanged(sender: ObservableArrayList<M>) {
        if (!mIsUpdateValue) {
            notifyItemRangeChanged(0, mItems.size)
        }
    }

    override fun onItemRangeChanged(sender: ObservableArrayList<M>, positionStart: Int, itemCount: Int) {
        if (!mIsUpdateValue) {
            notifyItemRangeChanged(positionStart, itemCount)
        }
    }

    override fun onItemRangeInserted(sender: ObservableArrayList<M>, positionStart: Int, itemCount: Int) {
        super.onItemRangeInserted(sender, positionStart, itemCount)
        if (mRegexAdd.matches(sender.size.toString())) {
            notifyItemRangeChanged(0, sender.size)
        }
        mRecyclerView?.scrollToPosition(sender.size - 1)
    }

    override fun onItemRangeRemoved(sender: ObservableArrayList<M>, positionStart: Int, itemCount: Int) {
        super.onItemRangeRemoved(sender, positionStart, itemCount)
        if (mRegexSub.matches(sender.size.toString())) {
            notifyItemRangeChanged(0, sender.size)
        }
        mRecyclerView?.scrollToPosition(sender.size - 1)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

