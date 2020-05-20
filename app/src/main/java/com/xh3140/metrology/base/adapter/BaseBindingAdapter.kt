package com.xh3140.metrology.base.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


abstract class BaseBindingAdapter<M, B : ViewDataBinding>(context: Context) : RecyclerView.Adapter<BaseBindingAdapter.BaseBindingViewHolder>() {

    private var mItemDataList: ObservableArrayList<M> = ObservableArrayList()

    private val mLayoutInflater: LayoutInflater by lazy { LayoutInflater.from(context) }

    private val mOnListChangedCallback: OnListChangedCallback by lazy { OnListChangedCallback() }

    fun getItemDataList(): ObservableArrayList<M> = mItemDataList

    @LayoutRes
    protected abstract fun getLayoutResId(viewType: Int): Int

    protected abstract fun onBindItem(binding: B, item: M)

    class BaseBindingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemCount(): Int = mItemDataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindingViewHolder {
        val layoutResId = getLayoutResId(viewType)
        val binding: B = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false)
        return BaseBindingViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: BaseBindingViewHolder, position: Int) {
        DataBindingUtil.getBinding<B>(holder.itemView)?.also { binding ->
            onBindItem(binding, mItemDataList[position])
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mItemDataList.addOnListChangedCallback(mOnListChangedCallback)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        mItemDataList.removeOnListChangedCallback(mOnListChangedCallback)
    }

    private inner class OnListChangedCallback : ObservableList.OnListChangedCallback<ObservableArrayList<M>>() {

        override fun onChanged(sender: ObservableArrayList<M>) {
            mItemDataList = sender
            notifyItemRangeChanged(0, mItemDataList.size)
        }

        override fun onItemRangeChanged(sender: ObservableArrayList<M>, positionStart: Int, itemCount: Int) {
            mItemDataList = sender
            notifyItemRangeChanged(positionStart, itemCount)
        }

        override fun onItemRangeInserted(sender: ObservableArrayList<M>, positionStart: Int, itemCount: Int) {
            mItemDataList = sender
            notifyItemRangeInserted(positionStart, itemCount)
        }

        override fun onItemRangeMoved(sender: ObservableArrayList<M>, fromPosition: Int, toPosition: Int, itemCount: Int) {
            mItemDataList = sender
            for (i in 0 until itemCount) {
                notifyItemMoved(fromPosition + i, toPosition + i)
            }
        }

        override fun onItemRangeRemoved(sender: ObservableArrayList<M>, positionStart: Int, itemCount: Int) {
            mItemDataList = sender
            notifyItemRangeRemoved(positionStart, itemCount)
        }
    }
}