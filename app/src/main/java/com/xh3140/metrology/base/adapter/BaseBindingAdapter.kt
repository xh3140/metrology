package com.xh3140.metrology.base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


abstract class BaseBindingAdapter<M, B : ViewDataBinding>(items: ObservableArrayList<M>) : RecyclerView.Adapter<BaseBindingAdapter.ViewHolder>() {

    protected val mItems: ObservableArrayList<M> = items

    protected var mRecyclerView: RecyclerView? = null

    private var mOnListChangedCallback: OnListChangedCallback? = null

    fun getItems(): ObservableArrayList<M> = mItems

    @LayoutRes
    protected abstract fun getLayoutResId(viewType: Int): Int

    protected abstract fun onBindItem(binding: B, item: M)

    override fun getItemCount(): Int = mItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutResId = getLayoutResId(viewType)
        val inflater = LayoutInflater.from(parent.context)
        val binding: B = DataBindingUtil.inflate(inflater, layoutResId, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        DataBindingUtil.getBinding<B>(holder.itemView)?.also { binding ->
            onBindItem(binding, mItems[position])
        }
    }

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

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private inner class OnListChangedCallback : ObservableList.OnListChangedCallback<ObservableArrayList<M>>() {

        override fun onChanged(sender: ObservableArrayList<M>) {
            this@BaseBindingAdapter.onChanged(sender)
        }

        override fun onItemRangeChanged(sender: ObservableArrayList<M>, positionStart: Int, itemCount: Int) {
            this@BaseBindingAdapter.onItemRangeChanged(sender, positionStart, itemCount)
        }

        override fun onItemRangeInserted(sender: ObservableArrayList<M>, positionStart: Int, itemCount: Int) {
            this@BaseBindingAdapter.onItemRangeInserted(sender, positionStart, itemCount)
        }

        override fun onItemRangeMoved(sender: ObservableArrayList<M>, fromPosition: Int, toPosition: Int, itemCount: Int) {
            this@BaseBindingAdapter.onItemRangeMoved(sender, fromPosition, toPosition, itemCount)
        }

        override fun onItemRangeRemoved(sender: ObservableArrayList<M>, positionStart: Int, itemCount: Int) {
            this@BaseBindingAdapter.onItemRangeRemoved(sender, positionStart, itemCount)
        }
    }
}