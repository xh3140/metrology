package com.xh3140.metrology.appliance.widgets

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.setPadding
import com.xh3140.core.extensions.dp2px
import com.xh3140.metrology.R
import com.xh3140.metrology.appliance.document.JJGN1078Y2012Document
import com.xh3140.metrology.appliance.document.JJGN961Y2017Document
import com.xh3140.metrology.appliance.document.StandardDocument

class ItemTableView : LinearLayout {
    // 头部
    private var mHeaderTextSize: Float = 11f
    private var mHeaderTextColor: Int = Color.GRAY
    private var mHeaderBackgroundColor: Int = Color.WHITE

    // 内容
    private var mContentTextSize: Float = 10f
    private var mContentTextColor: Int = Color.GRAY
    private var mContentBackgroundColor: Int = Color.WHITE

    // 底部
    private var mFooterTextSize: Float = 10f
    private var mFooterTextColor: Int = Color.GRAY
    private var mFooterBackgroundColor: Int = Color.WHITE

    // 分割线
    private var mDividerColor: Int = Color.GRAY

    // 文档
    private var mDocument: StandardDocument? = null

    constructor(context: Context) : super(context) {
        rebuild()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.ItemTableView)
        // 头部
        mHeaderTextSize = a.getDimension(R.styleable.ItemTableView_header_textSize, mHeaderTextSize)
        mHeaderTextColor = a.getColor(R.styleable.ItemTableView_header_textColor, mHeaderTextColor)
        mHeaderBackgroundColor = a.getColor(R.styleable.ItemTableView_header_backgroundColor, mHeaderBackgroundColor)
        // 内容
        mContentTextSize = a.getDimension(R.styleable.ItemTableView_content_TextSize, mContentTextSize)
        mContentTextColor = a.getColor(R.styleable.ItemTableView_content_textColor, mContentTextColor)
        mContentBackgroundColor = a.getColor(R.styleable.ItemTableView_content_backgroundColor, mContentBackgroundColor)
        // 底部
        mFooterTextSize = a.getDimension(R.styleable.ItemTableView_footer_TextSize, mFooterTextSize)
        mFooterTextColor = a.getColor(R.styleable.ItemTableView_footer_textColor, mFooterTextColor)
        mFooterBackgroundColor = a.getColor(R.styleable.ItemTableView_footer_backgroundColor, mFooterBackgroundColor)
        // 分割线
        mDividerColor = a.getColor(R.styleable.ItemTableView_dividerColor, mDividerColor)
        // 文档
        mDocument = findDocument(a.getInt(R.styleable.ItemTableView_document, -1))
        rebuild()
        a.recycle()
    }

    fun setHeaderTextSize(textSize: Float): ItemTableView {
        mHeaderTextSize = textSize
        return this
    }

    fun setHeaderTextColor(color: Int): ItemTableView {
        mHeaderTextColor = color
        return this
    }

    fun setHeaderBackgroundColor(color: Int): ItemTableView {
        mHeaderBackgroundColor = color
        return this
    }

    fun setContentTextSize(textSize: Float): ItemTableView {
        mContentTextSize = textSize
        return this
    }

    fun setContentTextColor(color: Int): ItemTableView {
        mContentTextColor = color
        return this
    }

    fun setContentBackgroundColor(color: Int): ItemTableView {
        mContentBackgroundColor = color
        return this
    }

    fun setFooterTextSize(textSize: Float): ItemTableView {
        mFooterTextSize = textSize
        return this
    }

    fun setFooterTextColor(color: Int): ItemTableView {
        mFooterTextColor = color
        return this
    }

    fun setFooterBackgroundColor(color: Int): ItemTableView {
        mFooterBackgroundColor = color
        return this
    }

    fun setDividerColor(color: Int): ItemTableView {
        mDividerColor = color
        return this
    }

    fun setDocument(document: StandardDocument?): ItemTableView {
        mDocument = document
        return this
    }

    fun rebuild(): ItemTableView {
        mDocument?.let { buildTable(it) }
        return this
    }

    /**
     * 拓展函数，最大深度，即列数
     */
    private fun StandardDocument.getMaxDepth(): Int {
        var depth = 1
        for (item in items) {
            depth = maxOf(depth, item.getMaxDepth())
        }
        return depth
    }

    private fun StandardDocument.Item.getMaxDepth(): Int {
        var depth = 1
        if (subItems.isEmpty()) {
            return depth
        } else {
            for (item in subItems) {
                depth = maxOf(depth, item.getMaxDepth())
            }
        }
        return depth + 1
    }

    /**
     * 查找器具文档
     */
    private fun findDocument(index: Int): StandardDocument? {
        return when (index) {
            0 -> JJGN1078Y2012Document
            1 -> JJGN961Y2017Document
            else -> null
        }
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
        addHeaderRow(document)
        val depth = document.getMaxDepth()
        for (i in document.items.indices) {
            addContentRow(document, i, depth, document.items[i])
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
                tableRow.addRecursionView(0f, depth, item)
            }
            StandardDocument.Type.JJF -> {
                tableRow.addView(createContentCellView(2f, (index + 1).toString()))
                tableRow.addView(createContentCellView(7f, item.name))
            }
        }
        addView(tableRow)
    }

    private fun LinearLayout.addRecursionView(weight: Float, depth: Int, item: StandardDocument.Item) {
        if (orientation == HORIZONTAL) {
            if (item.subItems.isEmpty()) {
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
                for (subItem in item.subItems) {
                    layout.addRecursionView(weight + 3f / depth, depth, subItem)
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
            layout.addRecursionView(weight, depth, item)
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
                addView(createFooterCellView(document.itemsNotes))
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
            textSize = mHeaderTextSize
            setTextColor(mHeaderTextColor)
            setBackgroundColor(mHeaderBackgroundColor)
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
            textSize = mContentTextSize
            setTextColor(mContentTextColor)
            setBackgroundColor(mContentBackgroundColor)
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
            textSize = mFooterTextSize
            setTextColor(mFooterTextColor)
            setBackgroundColor(mFooterBackgroundColor)
            layoutParams = LayoutParams(0, LayoutParams.MATCH_PARENT, 1f).apply { gravity = Gravity.CENTER }
        }
    }

    /**
     * 创建分割线
     */
    private fun createTableDividerDrawable(): Drawable {
        return GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(mDividerColor)
            setSize(1, 1)
        }
    }
}