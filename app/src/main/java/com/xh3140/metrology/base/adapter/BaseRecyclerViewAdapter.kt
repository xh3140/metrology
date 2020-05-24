package com.xh3140.metrology.base.adapter


import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<M, H : RecyclerView.ViewHolder>(items: ObservableArrayList<M>) : RecyclerView.Adapter<H>() {
    protected val mItems: ObservableArrayList<M> = items
    protected var mRecyclerView: RecyclerView? = null
    private var mOnListChangedCallback: OnListChangedCallback? = null

    fun getItems(): ObservableArrayList<M> = mItems

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
        mOnListChangedCallback = OnListChangedCallback()
        mItems.addOnListChangedCallback(mOnListChangedCallback)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        mItems.removeOnListChangedCallback(mOnListChangedCallback)
        mOnListChangedCallback = null
        mRecyclerView = null
    }

    protected open fun onChanged(sender: ObservableArrayList<M>) {
        notifyItemRangeChanged(0, mItems.size)
    }

    protected open fun onItemRangeChanged(sender: ObservableArrayList<M>, positionStart: Int, itemCount: Int) {
        notifyItemRangeChanged(positionStart, itemCount)
    }

    protected open fun onItemRangeInserted(sender: ObservableArrayList<M>, positionStart: Int, itemCount: Int) {
        notifyItemRangeInserted(positionStart, itemCount)
    }

    protected open fun onItemRangeMoved(sender: ObservableArrayList<M>, fromPosition: Int, toPosition: Int, itemCount: Int) {
        for (i in 0 until itemCount) {
            notifyItemMoved(fromPosition + i, toPosition + i)
        }
    }

    protected open fun onItemRangeRemoved(sender: ObservableArrayList<M>, positionStart: Int, itemCount: Int) {
        notifyItemRangeRemoved(positionStart, itemCount)
    }

    private inner class OnListChangedCallback : ObservableList.OnListChangedCallback<ObservableArrayList<M>>() {

        override fun onChanged(sender: ObservableArrayList<M>) {
            this@BaseRecyclerViewAdapter.onChanged(sender)
        }

        override fun onItemRangeChanged(sender: ObservableArrayList<M>, positionStart: Int, itemCount: Int) {
            this@BaseRecyclerViewAdapter.onItemRangeChanged(sender, positionStart, itemCount)
        }

        override fun onItemRangeInserted(sender: ObservableArrayList<M>, positionStart: Int, itemCount: Int) {
            this@BaseRecyclerViewAdapter.onItemRangeInserted(sender, positionStart, itemCount)
        }

        override fun onItemRangeMoved(sender: ObservableArrayList<M>, fromPosition: Int, toPosition: Int, itemCount: Int) {
            this@BaseRecyclerViewAdapter.onItemRangeMoved(sender, fromPosition, toPosition, itemCount)
        }

        override fun onItemRangeRemoved(sender: ObservableArrayList<M>, positionStart: Int, itemCount: Int) {
            this@BaseRecyclerViewAdapter.onItemRangeRemoved(sender, positionStart, itemCount)
        }
    }
}