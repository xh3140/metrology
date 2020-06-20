package com.xh3140.core.widgets.dialog.view

import android.content.Context
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xh3140.core.extensions.dp2px
import com.xh3140.core.widgets.common.RecyclerViewDivider
import com.xh3140.core.widgets.dialog.params.ListParams

class BodyListView(context: Context) : RecyclerView(context) {

    private var mListParams: ListParams = ListParams()

    private var mItemList: List<Item> = ArrayList()

    private var mCheckedIndex: Int = -1

    data class Item(val text: String, var checked: Boolean = false)

    fun getCheckedIndex(): Int {
        if (mListParams.isMultipleChoice) {
            return -1
        }
        return mCheckedIndex
    }

    fun getCheckedIndexList(): List<Int> {
        if (mListParams.isMultipleChoice) {
            val list: MutableList<Int> = ArrayList()
            for (i in mItemList.indices) {
                if (mItemList[i].checked) {
                    list.add(i)
                }
            }
            return list.toList()
        }
        return ArrayList()
    }

    fun setListItemParams(params: ListParams): BodyListView {
        mListParams = params
        mItemList = List(params.items.size) { Item(params.items[it], it == params.checkedIndex) }
        mCheckedIndex = params.checkedIndex
        return this
    }

    fun configView(): BodyListView {
        configListView(mListParams)
        return this
    }

    private fun configListView(params: ListParams) {
        setHasFixedSize(true)
        layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1F)
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = ListItemAdapter()
        if (params.isDividerList) {
            addItemDecoration(RecyclerViewDivider(context))
        }
    }

    private fun configItemView(button: CompoundButton) {
        button.textSize = mListParams.textSize
        button.setTextColor(mListParams.textColor)
        button.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
            marginStart = dp2px(11)
            marginEnd = dp2px(11)
        }
        mListParams.padding?.also { padding ->
            button.setPadding(dp2px(padding[0]), dp2px(padding[1]), dp2px(padding[2]), dp2px(padding[3]))
        }
    }

    /**
     * 列表适配器
     */
    inner class ListItemAdapter : Adapter<ListItemAdapter.ViewHolder>() {

        override fun getItemCount(): Int = mListParams.items.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val button: CompoundButton = if (mListParams.isMultipleChoice)
                AppCompatCheckBox(parent.context)
            else
                AppCompatRadioButton(parent.context)
            configItemView(button)
            return ViewHolder(button)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.button.text = mItemList[position].text
            holder.button.isChecked = mItemList[position].checked
        }

        inner class ViewHolder(val button: CompoundButton) : RecyclerView.ViewHolder(button) {

            init {
                when (button) {
                    is AppCompatRadioButton -> configRadioButton(button)
                    is AppCompatCheckBox -> configCheckBox(button)
                }
            }

            /**
             * 配置单选按钮
             */
            private fun configRadioButton(button: CompoundButton) {
                button.setOnCheckedChangeListener { _, isChecked ->
                    val position = adapterPosition
                    if (isChecked && position != mCheckedIndex) {
                        if (mCheckedIndex in mListParams.items.indices) {
                            mItemList[mCheckedIndex].checked = false
                        }
                        mItemList[position].checked = true
                        if (isComputingLayout) {
                            post {
                                notifyItemChanged(mCheckedIndex)
                                mCheckedIndex = position
                            }
                        } else {
                            notifyItemChanged(mCheckedIndex)
                            mCheckedIndex = position
                        }
                    }
                }
            }

            /**
             * 配置多选按钮
             */
            private fun configCheckBox(button: CompoundButton) {
                button.setOnCheckedChangeListener { _, isChecked ->
                    val position = adapterPosition
                    if (position in mListParams.items.indices) {
                        mItemList[position].checked = isChecked
                    }
                }
            }
        }
    }
}