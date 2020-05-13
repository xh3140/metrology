package com.xh3140.metrology.formula.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.xh3140.metrology.R
import com.xh3140.metrology.formula.AlgorithmViewModel
import java.util.regex.Pattern


class ListDataAdapter(private val mViewModel: AlgorithmViewModel) :
    RecyclerView.Adapter<ListDataAdapter.ViewHolder>() {

    private val mPatternSub: Pattern = Pattern.compile("""^9+$""")
    private val mPatternAdd: Pattern = Pattern.compile("""^10+$""")

    fun checkSizeMutation(direction: Boolean) {
        val count = itemCount
        val countText = count.toString()
        if (direction) {
            if (mPatternAdd.matcher(countText).matches()) {
                notifyItemRangeChanged(0, count)
            }
        } else {
            if (mPatternSub.matcher(countText).matches()) {
                notifyItemRangeChanged(0, count)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.formula_item_data, parent, false)
        return ViewHolder(view, this)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val padding = itemCount.toString().length
        holder.textViewName.text = String.format("数据%0${padding}d", position + 1)
        holder.editTextValue.setText(mViewModel.items[position])
    }

    override fun getItemCount(): Int {
        return mViewModel.items.size
    }

    inner class ViewHolder(itemView: View, adapter: ListDataAdapter) :
        RecyclerView.ViewHolder(itemView) {

        val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        val editTextValue: EditText = itemView.findViewById(R.id.editTextValue)

        init {
            editTextValue.addTextChangedListener {
                val value = it.toString()
                val position = adapterPosition
                if (position >= 0 && position < adapter.itemCount) {
                    if (value != adapter.mViewModel.items[position]) {
                        adapter.mViewModel.setItemValue(position, value)
                    }
                }
            }
        }
    }

}

