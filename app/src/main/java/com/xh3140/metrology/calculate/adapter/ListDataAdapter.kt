package com.xh3140.metrology.calculate.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.recyclerview.widget.RecyclerView
import com.xh3140.metrology.R
import com.xh3140.metrology.calculate.AlgorithmViewModel
import kotlinx.android.synthetic.main.item_calculate_data.view.*


class ListDataAdapter(viewModel: AlgorithmViewModel) : RecyclerView.Adapter<ListDataAdapter.ViewHolder>() {

    private var mIsUpdateValue: Boolean = false

    private var mRecyclerView: RecyclerView? = null

    private val mItems: ObservableArrayList<String> = viewModel.mItems.value ?: ObservableArrayList()

    private val mOnListChangedCallback: OnListChangedCallback by lazy { OnListChangedCallback() }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
        mItems.addOnListChangedCallback(mOnListChangedCallback)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        mRecyclerView = null
        mItems.removeOnListChangedCallback(mOnListChangedCallback)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_calculate_data, parent, false)
        return ViewHolder(itemView).apply {
            itemView.editTextValue.addTextChangedListener {
                mIsUpdateValue = true
                mItems[adapterPosition] = it.toString()
                mIsUpdateValue = false
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val padding = itemCount.toString().length
        holder.itemView.textViewIndex.text = String.format("数据%0${padding}d", position + 1)
        holder.itemView.editTextValue.setText(mItems[position].toString())
    }

    override fun getItemCount(): Int = mItems.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private inner class OnListChangedCallback : ObservableList.OnListChangedCallback<ObservableArrayList<String>>() {

        private val mRegexSub: Regex = Regex("""^9+$""")

        private val mRegexAdd: Regex = Regex("""^10+$""")

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
            notifyItemRangeInserted(positionStart, itemCount)
            if (mRegexAdd.matches(sender.size.toString())) {
                notifyItemRangeChanged(0, sender.size)
            }
            mRecyclerView?.scrollToPosition(sender.size - 1)
        }

        override fun onItemRangeMoved(sender: ObservableArrayList<String>, fromPosition: Int, toPosition: Int, itemCount: Int) {
            for (i in 0 until itemCount) {
                notifyItemMoved(fromPosition + i, toPosition + i)
            }
        }

        override fun onItemRangeRemoved(sender: ObservableArrayList<String>, positionStart: Int, itemCount: Int) {
            notifyItemRangeRemoved(positionStart, itemCount)
            if (mRegexSub.matches(sender.size.toString())) {
                notifyItemRangeChanged(0, sender.size)
            }
            mRecyclerView?.scrollToPosition(sender.size - 1)
        }
    }
}

