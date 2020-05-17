package com.xh3140.core.widget.dialog.listener

import android.view.View
import com.xh3140.core.widget.dialog.CircleDialog

interface ButtonOnClickListener {
    fun onClick(dialog: CircleDialog, view: View, index: Int)
}