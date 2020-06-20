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

class ItemsView(context: Context) : LinearLayout(context) {

    fun build(document: StandardDocument?): ItemsView {
        document?.let { buildTable(it) }
        return this
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
        addHeaderRow(document)
        for ((index, item) in document.items.withIndex()) {
            addContentRow(document, index, depth, item)
        }
        addFooterRow(document)
    }

    /**
     * 添加表格头部行
     */
    private fun addHeaderRow(document: StandardDocument) {
        val headerRow = LinearLayout(context).apply {
            orientation = HORIZONTAL
            dividerDrawable = createTableDividerDrawable()
            showDividers = SHOW_DIVIDER_BEGINNING or SHOW_DIVIDER_MIDDLE or SHOW_DIVIDER_END
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            when (document.type) {
                StandardDocument.Type.JJG -> {
                    addView(createHeaderCellView(3f, "检定项目"))
                    addView(createHeaderCellView(2f, "首次检定"))
                    addView(createHeaderCellView(2f, "后续检定"))
                    addView(createHeaderCellView(2f, "使用中检查"))
                }
                StandardDocument.Type.JJF -> {
                    addView(createHeaderCellView(2f, "序号"))
                    addView(createHeaderCellView(7f, "校准项目"))
                }
            }
        }
        addView(headerRow)
    }

    /**
     * 添加表格内容行
     */
    private fun addContentRow(document: StandardDocument, index: Int, depth: Int, item: StandardDocument.Item) {
        val tableRow = LinearLayout(context).apply {
            orientation = HORIZONTAL
            dividerDrawable = createTableDividerDrawable()
            showDividers = SHOW_DIVIDER_BEGINNING or SHOW_DIVIDER_MIDDLE or SHOW_DIVIDER_END
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        }
        when (document.type) {
            StandardDocument.Type.JJG -> {
                tableRow.addTreeView(0f, depth, item)
            }
            StandardDocument.Type.JJF -> {
                tableRow.addView(createContentCellView(2f, (index + 1).toString()))
                tableRow.addView(createContentCellView(7f, item.name))
            }
        }
        addView(tableRow)
    }

    /**
     * 递归添加树
     */
    private fun LinearLayout.addTreeView(weight: Float, depth: Int, item: StandardDocument.Item) {
        if (orientation == HORIZONTAL) {
            if (item.children.isEmpty()) {
                addView(createContentCellView(3f - weight, item.name))
                addView(createContentCellView(2f, matchSymbols(StandardDocument.Item.FIRST, item.type)))
                addView(createContentCellView(2f, matchSymbols(StandardDocument.Item.SUBSEQUENT, item.type)))
                addView(createContentCellView(2f, matchSymbols(StandardDocument.Item.USING, item.type)))
            } else {
                addView(createContentCellView(3f / depth, item.name))
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
     * 添加表格底部行
     */
    private fun addFooterRow(document: StandardDocument) {
        if (document.itemsNotes.isNotEmpty()) {
            val footerRow = LinearLayout(context).apply {
                orientation = HORIZONTAL
                dividerDrawable = createTableDividerDrawable()
                showDividers = SHOW_DIVIDER_BEGINNING or SHOW_DIVIDER_MIDDLE or SHOW_DIVIDER_END
                layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                addView(createFooterCellView(document.itemsNotes.joinToString("\n")))
            }
            addView(footerRow)
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
    private fun createContentCellView(weight: Float, content: String): AppCompatTextView {
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