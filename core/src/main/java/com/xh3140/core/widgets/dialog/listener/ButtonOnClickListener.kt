package com.xh3140.core.widgets.dialog.listener

import com.xh3140.core.widgets.dialog.CircleDialog
import com.xh3140.core.widgets.dialog.view.ButtonView

interface ButtonOnClickListener<D : CircleDialog> {
    fun onClick(dialog: D, button: ButtonView, index: Int)
}