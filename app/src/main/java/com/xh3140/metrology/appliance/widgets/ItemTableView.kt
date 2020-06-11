package com.xh3140.metrology.appliance.widgets

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.setPadding
import com.xh3140.core.extensions.dp2px
import com.xh3140.metrology.R
import com.xh3140.metrology.appliance.document.JJGN1078Y2012Document
import com.xh3140.metrology.appliance.document.StandardDocument

class ItemTableView : TableLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ItemTableView)
        val documentIndex = typedArray.getInt(R.styleable.ItemTableView_document, -1)
        typedArray.recycle()
        post {
            removeAllViews()
            addTableHeaderRow()
            when (documentIndex) {
                0 -> {
                    addTableApplianceItemRows(JJGN1078Y2012Document)
                    Log.d("xh3140", "3")
                }
            }
            addTableFooterRow()
        }
    }

    init {
        orientation = VERTICAL
        dividerDrawable = createTableDividerDrawable(VERTICAL)
        showDividers = SHOW_DIVIDER_BEGINNING or SHOW_DIVIDER_MIDDLE or SHOW_DIVIDER_END
    }

    private fun addTableHeaderRow() {
        val headerRow = TableRow(context).apply {
            orientation = HORIZONTAL
            dividerDrawable = createTableDividerDrawable(HORIZONTAL)
            showDividers = SHOW_DIVIDER_BEGINNING or SHOW_DIVIDER_MIDDLE or SHOW_DIVIDER_END
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1f)
            addView(createTableTextView(3f, context.getString(R.string.appliance_jjg_item)))
            addView(createTableTextView(2f, context.getString(R.string.appliance_jjg_first)))
            addView(createTableTextView(2f, context.getString(R.string.appliance_jjg_subsequent)))
            addView(createTableTextView(2f, context.getString(R.string.appliance_jjg_using)))
        }
        addView(headerRow)
    }

    private fun addTableFooterRow() {
        val footerRow = TableRow(context).apply {
            orientation = HORIZONTAL
            dividerDrawable = createTableDividerDrawable(HORIZONTAL)
            showDividers = SHOW_DIVIDER_BEGINNING or SHOW_DIVIDER_MIDDLE or SHOW_DIVIDER_END
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1f)
            addView(createTableTextView(1f, context.getString(R.string.appliance_jjg_item_note)))
        }
        addView(footerRow)
    }


    private fun addTableApplianceItemRows(document: StandardDocument) {
        for (item in document.items) {
            addTableItemRow(item)
        }
    }

    private fun addTableItemRow(item: StandardDocument.Item) {
        val tableRow = TableRow(context).apply {
            orientation = HORIZONTAL
            dividerDrawable = createTableDividerDrawable(HORIZONTAL)
            showDividers = SHOW_DIVIDER_BEGINNING or SHOW_DIVIDER_MIDDLE or SHOW_DIVIDER_END
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1f)
            addView(createTableTextView(3f, item.name))
            addView(createTableTextView(2f, if (item.isFirst) "＋" else "－"))
            addView(createTableTextView(2f, if (item.isSubsequent) "＋" else "－"))
            addView(createTableTextView(2f, if (item.isUsing) "＋" else "－"))
        }
        addView(tableRow)
    }

    private fun createTableTextView(weight: Float, text: String): AppCompatTextView {
        return AppCompatTextView(context).apply {
            setPadding(dp2px(8))
            this.text = text
            gravity = Gravity.CENTER
            layoutParams = LayoutParams(0, LayoutParams.MATCH_PARENT, weight).apply { gravity = Gravity.CENTER }
        }
    }

    private fun createTableDividerDrawable(orientation: Int): GradientDrawable {
        return GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(Color.parseColor("#808080"))
            if (orientation == VERTICAL) {
                setSize(dp2px(1), 0)
            } else {
                setSize(0, dp2px(1))
            }
        }
    }
}