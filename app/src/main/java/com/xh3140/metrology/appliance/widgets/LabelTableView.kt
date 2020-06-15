package com.xh3140.metrology.appliance.widgets

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioGroup
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatRadioButton
import com.xh3140.core.extensions.dp2px
import com.xh3140.metrology.appliance.document.StandardDocument

class LabelTableView : LinearLayout {

    private var mLabels: Int = StandardDocument.LABEL_ALL

    private var mOnLabelsChangedListener: OnLabelsChangedListener? = null

    private val mRadioButtonOr: AppCompatRadioButton by lazy { AppCompatRadioButton(context) }

    private val mRadioButtonAnd: AppCompatRadioButton by lazy { AppCompatRadioButton(context) }

    private val mCheckBoxList: MutableList<AppCompatCheckBox> = ArrayList()

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    init {
        orientation = VERTICAL
        addLogicRow()
        addDividerView()
        var layout: LinearLayout? = null
        val labels = StandardDocument.Label.values()
        for ((index, label) in labels.withIndex()) {
            if (index % 4 == 0) {
                layout = LinearLayout(context).apply {
                    orientation = HORIZONTAL
                    layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                }
                addView(layout)
            }
            layout?.addView(createLabelCheckBox(label))
        }
    }


    private fun addLogicRow() {
        val layout = LinearLayout(context).apply {
            orientation = HORIZONTAL
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        }
        layout.addView(View(context), LayoutParams(0, LayoutParams.MATCH_PARENT, 1f))
        val radioGroup = RadioGroup(context).apply {
            orientation = HORIZONTAL
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        }
        radioGroup.setOnCheckedChangeListener { _, _ ->
            mOnLabelsChangedListener?.onChanged(mRadioButtonOr.isChecked, mLabels)
        }
        mRadioButtonOr.apply {
            text = "逻辑或"
            id = hashCode()
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT).apply { marginEnd = dp2px(50) }
        }
        radioGroup.addView(mRadioButtonOr)
        mRadioButtonAnd.apply {
            text = "逻辑与"
            id = hashCode()
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT).apply { marginStart = dp2px(50) }
        }
        radioGroup.addView(mRadioButtonAnd)
        radioGroup.check(mRadioButtonOr.hashCode())
        layout.addView(radioGroup)
        layout.addView(View(context), LayoutParams(0, LayoutParams.MATCH_PARENT, 1f))
        addView(layout)
    }

    private fun addDividerView() {
        val divider = View(context).apply {
            setBackgroundColor(Color.GRAY)
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, 1)
        }
        addView(divider)
    }

    private fun createLabelCheckBox(label: StandardDocument.Label): AppCompatCheckBox {
        val checkBox = AppCompatCheckBox(context).apply {
            text = label.text
            isChecked = label.value and mLabels != 0
            layoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f)
        }
        mCheckBoxList.add(checkBox)
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            val newLabels: Int
            if (label == StandardDocument.Label.ALL) {
                for (box in mCheckBoxList) {
                    box.isChecked = isChecked
                }
                newLabels = if (isChecked) label.value else label.value.inv()
            } else {
                newLabels = if (isChecked) mLabels or label.value else mLabels xor label.value
                if (mLabels != newLabels) {
                    mLabels = newLabels
                    mOnLabelsChangedListener?.onChanged(mRadioButtonOr.isChecked, mLabels)
                }
            }
            if (mLabels != newLabels) {
                mLabels = newLabels
                mOnLabelsChangedListener?.onChanged(mRadioButtonOr.isChecked, mLabels)
            }
        }
        return checkBox
    }

    /**
     * 设置标签改变监听器
     */
    fun setOnLabelsChangedListener(listener: OnLabelsChangedListener) {
        mOnLabelsChangedListener = listener
    }

    /**
     * 标签改变监听器接口
     */
    interface OnLabelsChangedListener {
        fun onChanged(logicOr: Boolean, labels: Int)
    }
}