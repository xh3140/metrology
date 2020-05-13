package com.xh3140.core.widget.dialog.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.LinearLayout
import android.widget.RadioButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xh3140.core.widget.dialog.params.ListParams
import com.xh3140.core.widget.utils.PixelUtil
import kotlin.math.roundToInt


class BodyListView(context: Context) : RecyclerView(context) {

    private var mListParams: ListParams = ListParams()

    private var mItemList: List<Item> = ArrayList()

    private var mCheckedIndex: Int = -1

    data class Item(val text: String, var checked: Boolean = false)

    fun getCheckedIndex(): Int {
        if (mListParams.isMultiChoice) {
            return -1
        }
        return mCheckedIndex
    }

    fun getCheckedIndexList(): List<Int> {
        if (mListParams.isMultiChoice) {
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
        id = android.R.id.list
        layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT, 1F
        )
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL, false
        )
        adapter = ListItemAdapter()
        if (params.isDividerList) {
            addItemDecoration(VerticalItemDecoration(context))
        }
        if (params.padding != null) {
            val padding = params.padding as IntArray
            setPadding(
                PixelUtil.dp2px(context, padding[0]),
                PixelUtil.dp2px(context, padding[1]),
                PixelUtil.dp2px(context, padding[2]),
                PixelUtil.dp2px(context, padding[3])
            )
        }
    }

    private fun configItemView(button: CompoundButton) {
        button.textSize = mListParams.textSize
        button.setTextColor(mListParams.textColor)
        button.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        if (mListParams.padding != null) {
            val padding = mListParams.padding as IntArray
            button.setPadding(
                PixelUtil.dp2px(context, padding[0]),
                PixelUtil.dp2px(context, padding[1]),
                PixelUtil.dp2px(context, padding[2]),
                PixelUtil.dp2px(context, padding[3])
            )
        }
    }

    /**
     * 列表适配器
     */
    inner class ListItemAdapter : Adapter<ListItemAdapter.ViewHolder>() {

        override fun getItemCount(): Int = mListParams.items.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val button = if (mListParams.isMultiChoice) {
                CheckBox(parent.context)
            } else {
                RadioButton(parent.context)
            }
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
                    is RadioButton -> {
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
                    is CheckBox -> {
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
    }

    /**
     * 分割线
     */
    inner class VerticalItemDecoration(context: Context) : ItemDecoration() {

        private val mBounds = Rect()

        private val mDivider: Drawable?

        init {
            val array = intArrayOf(android.R.attr.listDivider)
            val typedArray = context.obtainStyledAttributes(array)
            mDivider = typedArray.getDrawable(0)
            typedArray.recycle()
        }

        override fun onDraw(canvas: Canvas, parent: RecyclerView, state: State) {
            mDivider?.also { divider ->
                canvas.save()
                val left: Int
                val right: Int
                if (!parent.clipToPadding) {
                    left = 0
                    right = parent.width
                } else {
                    left = parent.paddingLeft
                    right = parent.width - parent.paddingRight
                    val top = parent.paddingTop
                    val bottom = parent.height - parent.paddingBottom
                    canvas.clipRect(left, top, right, bottom)
                }
                for (i in 0 until parent.childCount - 1) {
                    val child = parent.getChildAt(i)
                    parent.getDecoratedBoundsWithMargins(child, mBounds)
                    val bottom: Int = mBounds.bottom + child.translationY.roundToInt()
                    val top = bottom - divider.intrinsicHeight
                    divider.setBounds(left, top, right, bottom)
                    divider.draw(canvas)
                }
                canvas.restore()
            }
        }
    }
}