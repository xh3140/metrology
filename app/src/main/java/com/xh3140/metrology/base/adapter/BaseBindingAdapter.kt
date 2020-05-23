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


abstract class BaseBindingAdapter<M, B : ViewDataBinding> : RecyclerView.Adapter<BaseBindingAdapter.BaseBindingViewHolder>() {

    private var mItems: ObservableArrayList<M> = ObservableArrayList()

    private val mOnListChangedCallback: OnListChangedCallback by lazy { OnListChangedCallback() }

    fun getItems(): ObservableArrayList<M> = mItems

    @LayoutRes
    protected abstract fun getLayoutResId(viewType: Int): Int

    protected abstract fun onBindItem(binding: B, item: M)

    class BaseBindingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemCount(): Int = mItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindingViewHolder {
        val layoutResId = getLayoutResId(viewType)
        val inflater = LayoutInflater.from(parent.context)
        val binding: B = DataBindingUtil.inflate(inflater, layoutResId, parent, false)
        return BaseBindingViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: BaseBindingViewHolder, position: Int) {
        DataBindingUtil.getBinding<B>(holder.itemView)?.also { binding ->
            onBindItem(binding, mItems[position])
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mItems.addOnListChangedCallback(mOnListChangedCallback)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        mItems.removeOnListChangedCallback(mOnListChangedCallback)
    }

    private inner class OnListChangedCallback : ObservableList.OnListChangedCallback<ObservableArrayList<M>>() {

        override fun onChanged(sender: ObservableArrayList<M>) {
            mItems = sender
            notifyItemRangeChanged(0, mItems.size)
        }

        override fun onItemRangeChanged(sender: ObservableArrayList<M>, positionStart: Int, itemCount: Int) {
            mItems = sender
            notifyItemRangeChanged(positionStart, itemCount)
        }

        override fun onItemRangeInserted(sender: ObservableArrayList<M>, positionStart: Int, itemCount: Int) {
            mItems = sender
            notifyItemRangeInserted(positionStart, itemCount)
        }

        override fun onItemRangeMoved(sender: ObservableArrayList<M>, fromPosition: Int, toPosition: Int, itemCount: Int) {
            mItems = sender
            for (i in 0 until itemCount) {
                notifyItemMoved(fromPosition + i, toPosition + i)
            }
        }

        override fun onItemRangeRemoved(sender: ObservableArrayList<M>, positionStart: Int, itemCount: Int) {
            mItems = sender
            notifyItemRangeRemoved(positionStart, itemCount)
        }
    }
}