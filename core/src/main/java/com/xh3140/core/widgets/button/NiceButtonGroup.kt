package com.xh3140.core.widgets.button

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.children


/**
 * @Author : xh3140
 * @Time : 2020/7/19 16:33
 * @File : NiceButtonGroup.kt
 */

class NiceButtonGroup : LinearLayout {

    init {
        orientation = HORIZONTAL
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
    }

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context?, orientation: Int, vararg buttons: NiceButton) : super(context) {
        when (orientation) {
            HORIZONTAL -> configHorizontal(buttons)
            VERTICAL -> configVertical(buttons)
        }
    }

    private fun configHorizontal(buttons: Array<out NiceButton>) {
        orientation = HORIZONTAL
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        for ((index, button) in buttons.withIndex()) {
            when (index) {
                0 -> addView(
                    button.setLocation(NiceButton.Location.LEFT),
                    LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f)
                )
                buttons.size - 1 -> addView(
                    button.setLocation(NiceButton.Location.RIGHT),
                    LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f)
                )
                else -> addView(
                    button.setLocation(NiceButton.Location.HORIZONTAL_BETWEEN),
                    LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f)
                )
            }
        }
    }

    private fun configVertical(buttons: Array<out NiceButton>) {
        orientation = VERTICAL
        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT)
        for ((index, button) in buttons.withIndex()) {
            when (index) {
                0 -> addView(
                    button.setLocation(NiceButton.Location.TOP),
                    LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f)
                )
                buttons.size - 1 -> addView(
                    button.setLocation(NiceButton.Location.BOTTOM),
                    LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f)
                )
                else -> addView(
                    button.setLocation(NiceButton.Location.VERTICAL_BETWEEN),
                    LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f)
                )
            }
        }
    }

    private fun configButtons() {
        val buttons: MutableList<NiceButton> = ArrayList()
        for (child in children) {
            if (child !is NiceButton) {
                throw IllegalArgumentException("Cannot add a not NiceButton view to a NiceButtonGroup")
            }
            child.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f)
            buttons.add(child)
        }
        if (buttons.isNotEmpty()) {
            if (buttons.size == 1) {
                buttons.first().setLocation(NiceButton.Location.NULL)
            } else {
                when (orientation) {
                    HORIZONTAL ->
                        for ((index, button) in buttons.withIndex()) {
                            when (index) {
                                0 -> button.setLocation(NiceButton.Location.LEFT)
                                buttons.size - 1 -> button.setLocation(NiceButton.Location.RIGHT)
                                else -> button.setLocation(NiceButton.Location.HORIZONTAL_BETWEEN)
                            }
                        }
                    VERTICAL -> for ((index, button) in buttons.withIndex()) {
                        when (index) {
                            0 -> button.setLocation(NiceButton.Location.TOP)
                            buttons.size - 1 -> button.setLocation(NiceButton.Location.BOTTOM)
                            else -> button.setLocation(NiceButton.Location.VERTICAL_BETWEEN)
                        }
                    }
                }
            }
        }
    }

    fun addButton(button: NiceButton): NiceButtonGroup {
        super.addView(button)
        return this
    }

    fun addButton(button: NiceButton, index: Int): NiceButtonGroup {
        super.addView(button, index)
        return this
    }

    fun addButton(button: NiceButton, params: ViewGroup.LayoutParams?): NiceButtonGroup {
        super.addView(button, params)
        return this
    }

    fun addButton(button: NiceButton, width: Int, height: Int): NiceButtonGroup {
        super.addView(button, width, height)
        return this
    }

    fun addButton(button: NiceButton, width: Int, height: Int, weight: Float): NiceButtonGroup {
        super.addView(button, LayoutParams(width, height, weight))
        return this
    }

    fun addButton(button: NiceButton, index: Int, params: ViewGroup.LayoutParams?): NiceButtonGroup {
        super.addView(button, index, params)
        return this
    }

    fun addButtonInLayout(button: NiceButton, index: Int, params: ViewGroup.LayoutParams?): Boolean {
        return super.addViewInLayout(button, index, params)
    }

    fun addButtonInLayout(button: NiceButton, index: Int, params: ViewGroup.LayoutParams?, preventRequestLayout: Boolean): Boolean {
        return super.addViewInLayout(button, index, params, preventRequestLayout)
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        if (child !is NiceButton) {
            throw IllegalArgumentException("Cannot add a not NiceButton view to a NiceButtonGroup")
        }
        super.addView(child, index, params)
        configButtons()
    }

    override fun addViewInLayout(child: View?, index: Int, params: ViewGroup.LayoutParams?): Boolean {
        if (child !is NiceButton) {
            throw IllegalArgumentException("Cannot add a not NiceButton view to a NiceButtonGroup")
        }
        val result = super.addViewInLayout(child, index, params)
        configButtons()
        return result
    }

    override fun addViewInLayout(child: View?, index: Int, params: ViewGroup.LayoutParams?, preventRequestLayout: Boolean): Boolean {
        if (child !is NiceButton) {
            throw IllegalArgumentException("Cannot add a not NiceButton view to a NiceButtonGroup")
        }
        val result = super.addViewInLayout(child, index, params, preventRequestLayout)
        configButtons()
        return result
    }
}