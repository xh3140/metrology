package com.xh3140.core.widgets.button

import android.widget.CompoundButton
import android.widget.RadioButton


/**
 * @Author : xh3140
 * @Time : 2020/7/19 21:35
 * @File : FreeRadioGroup.kt
 */

class FreeRadioGroup(vararg buttons: RadioButton) : CompoundButton.OnCheckedChangeListener {

    private val mRadioButtons: MutableList<RadioButton> = ArrayList()
    private var mOnCheckedChangeListener: OnCheckedChangeListener? = null

    init {
        addRadioButtonsInner(buttons)
    }

    interface OnCheckedChangeListener {
        fun onCheckedChanged(buttonView: RadioButton?, isChecked: Boolean)
    }

    fun setOnCheckedChangeListener(listener: OnCheckedChangeListener?) {
        mOnCheckedChangeListener = listener
    }

    fun setOnCheckedChangeListener(listener: (buttonView: RadioButton?, isChecked: Boolean) -> Unit) {
        mOnCheckedChangeListener = object : OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: RadioButton?, isChecked: Boolean) {
                listener(buttonView, isChecked)
            }
        }
    }

    fun addRadioButtons(vararg buttons: RadioButton) {
        addRadioButtonsInner(buttons)
    }

    private fun addRadioButtonsInner(buttons: Array<out RadioButton>) {
        for (button in buttons) {
            if (!mRadioButtons.contains(button)) {
                mRadioButtons.add(button)
                button.setOnCheckedChangeListener(this)
            }
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if (isChecked) {
            if (mRadioButtons.contains(buttonView)) {
                for (button in mRadioButtons) {
                    if (button.isChecked && button != buttonView) {
                        button.isChecked = false
                    }
                }
            } else {
                buttonView?.setOnCheckedChangeListener(null)
            }
        }
        if (buttonView is RadioButton) {
            mOnCheckedChangeListener?.onCheckedChanged(buttonView, isChecked)
        }
    }
}