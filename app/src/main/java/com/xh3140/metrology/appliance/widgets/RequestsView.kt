package com.xh3140.metrology.appliance.widgets

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.setPadding
import com.xh3140.core.extensions.dp2px
import com.xh3140.metrology.appliance.document.StandardDocument


/**
 * @Author : xh3140
 * @Time : 2020/6/20 19:15
 * @File : TechRequestsView.kt
 */

class RequestsView(context: Context?) : LinearLayout(context) {

    private var mOnLongClickRequestListener: OnLongClickRequestListener? = null

    interface OnLongClickRequestListener {
        fun onLongClickRequest(request: String)
    }

    fun build(document: StandardDocument?): RequestsView {
        document?.let { buildTable(it) }
        return this
    }

    fun setOnLongClickRequestListener(listener: OnLongClickRequestListener?) {
        mOnLongClickRequestListener = listener
    }

    /**
     * 拓展函数，最大深度
     */
    private fun StandardDocument.Item.getDepth(): Int {
        var depth = 1
        if (children.isEmpty()) {
            return depth
        } else {
            for (item in children) {
                depth = maxOf(depth, item.getDepth())
            }
        }
        return depth + 1
    }

    /**
     * 匹配符号
     */
    private fun matchSymbols(source: Int, target: Int): String {
        return when {
            target == 0 -> "／"
            source and target != 0 -> "＋"
            else -> "－"
        }
    }

    /**
     * 构建表格
     */
    private fun buildTable(document: StandardDocument) {
        orientation = VERTICAL
        dividerDrawable = createTableDividerDrawable()
        showDividers = SHOW_DIVIDER_BEGINNING or SHOW_DIVIDER_MIDDLE or SHOW_DIVIDER_END
        if (childCount > 0) {
            removeAllViews()
        }
        var depth = 1
        for (item in document.items) {
            depth = maxOf(depth, item.getDepth())
        }
        addHeaderRow()
        for (item in document.items) {
            addContentRow(depth, item)
        }
        addFooterRow(document)
    }

    /**
     * 添加表格头部行
     */
    private fun addHeaderRow() {
        val headerRow = LinearLayout(context).apply {
            orientation = HORIZONTAL
            dividerDrawable = createTableDividerDrawable()
            showDividers = SHOW_DIVIDER_BEGINNING or SHOW_DIVIDER_MIDDLE or SHOW_DIVIDER_END
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            addView(createHeaderCellView(3f, "项目名称"))
            addView(createHeaderCellView(6f, "技术要求"))
        }
        addView(headerRow)
    }

    /**
     * 添加表格内容行
     */
    private fun addContentRow(depth: Int, item: StandardDocument.Item) {
        val tableRow = LinearLayout(context).apply {
            orientation = HORIZONTAL
            dividerDrawable = createTableDividerDrawable()
            showDividers = SHOW_DIVIDER_BEGINNING or SHOW_DIVIDER_MIDDLE or SHOW_DIVIDER_END
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        }
        tableRow.addTreeView(0f, depth, item)
        addView(tableRow)
    }

    /**
     * 递归添加树
     */
    private fun LinearLayout.addTreeView(weight: Float, depth: Int, item: StandardDocument.Item) {
        if (orientation == HORIZONTAL) {
            if (item.children.isEmpty()) {
                addView(createNameCellView(3f - weight, item.name))
                addView(createRequestCellView(item.requests.joinToString("\n")))
            } else {
                addView(createNameCellView(3f / depth, item.name))
                val layout = LinearLayout(context).apply {
                    orientation = VERTICAL
                    dividerDrawable = createTableDividerDrawable()
                    showDividers = SHOW_DIVIDER_MIDDLE
                    layoutParams = LayoutParams(0, LayoutParams.MATCH_PARENT, 9f - weight - 3f / depth)
                }
                for (subItem in item.children) {
                    layout.addTreeView(weight + 3f / depth, depth, subItem)
                }
                addView(layout)
            }
        } else {
            val layout = LinearLayout(context).apply {
                orientation = HORIZONTAL
                dividerDrawable = createTableDividerDrawable()
                showDividers = SHOW_DIVIDER_MIDDLE
                layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            }
            layout.addTreeView(weight, depth, item)
            addView(layout)
        }
    }


    /**
     * 创建头部单元视图
     */
    private fun createHeaderCellView(weight: Float, title: String): AppCompatTextView {
        return AppCompatTextView(context).apply {
            setPadding(dp2px(8))
            gravity = Gravity.CENTER
            text = title
            textSize = 11f
            setTextColor(Color.GRAY)
            setBackgroundColor(Color.WHITE)
            layoutParams = LayoutParams(0, LayoutParams.MATCH_PARENT, weight).apply { gravity = Gravity.CENTER }
        }
    }

    /**
     * 创建内容单元视图
     */
    private fun createNameCellView(weight: Float, content: String): AppCompatTextView {
        return AppCompatTextView(context).apply {
            setPadding(dp2px(8))
            gravity = Gravity.CENTER
            text = content
            textSize = 10f
            setTextColor(Color.GRAY)
            setBackgroundColor(Color.WHITE)
            layoutParams = LayoutParams(0, LayoutParams.MATCH_PARENT, weight).apply { gravity = Gravity.CENTER }
        }
    }

    /**
     * 创建内容单元视图
     */
    private fun createRequestCellView(requests: String): AppCompatTextView {
        return AppCompatTextView(context).apply {
            setPadding(dp2px(8))
            gravity = Gravity.CENTER_VERTICAL
            text = requests
            textSize = 10f
            setTextColor(Color.GRAY)
            setBackgroundColor(Color.WHITE)
            layoutParams = LayoutParams(0, LayoutParams.MATCH_PARENT, 6f).apply { gravity = Gravity.CENTER }
            if (requests.isNotEmpty()) {
                setOnLongClickListener {
                    mOnLongClickRequestListener?.onLongClickRequest(requests)
                    return@setOnLongClickListener true
                }
            }
        }
    }

    /**
     * 添加表格底部行
     */
    private fun addFooterRow(document: StandardDocument) {
        if (document.requestsNotes.isNotEmpty()) {
            val footerRow = LinearLayout(context).apply {
                orientation = HORIZONTAL
                dividerDrawable = createTableDividerDrawable()
                showDividers = SHOW_DIVIDER_BEGINNING or SHOW_DIVIDER_MIDDLE or SHOW_DIVIDER_END
                layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                addView(createFooterCellView(document.requestsNotes.joinToString("\n")))
            }
            addView(footerRow)
        }
    }

    /**
     * 创建底部单元视图
     */
    private fun createFooterCellView(message: String): AppCompatTextView {
        return AppCompatTextView(context).apply {
            setPadding(dp2px(8))
            text = message
            textSize = 10f
            setTextColor(Color.GRAY)
            setBackgroundColor(Color.WHITE)
            layoutParams = LayoutParams(0, LayoutParams.MATCH_PARENT, 1f).apply { gravity = Gravity.CENTER }
        }
    }

    /**
     * 创建分割线
     */
    private fun createTableDividerDrawable(): Drawable {
        return GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(Color.GRAY)
            setSize(1, 1)
        }
    }
}