package com.xh3140.core.widget.dialog

import android.app.Activity
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.Window
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.xh3140.core.widget.dialog.params.DialogParams
import com.xh3140.core.widget.utils.DrawableUtil
import com.xh3140.core.widget.utils.PixelUtil


abstract class BaseCircleDialog(params: DialogParams) : DialogFragment() {

    private val mDialogParams: DialogParams = params

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设置对话框风格，无标题，无边框
        setStyle(STYLE_NO_TITLE, 0)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 设置对话框背景
        context?.also { context ->
            val radius = PixelUtil.dp2px(context, mDialogParams.radius)
            view.background = DrawableUtil.getGradientDrawable(
                mDialogParams.backgroundColor, intArrayOf(radius, radius, radius, radius)
            )
        }
        // 设置对话框透明度
        view.alpha = mDialogParams.alpha
    }

    override fun onStart() {
        val dialog = dialog ?: return
        val activity = activity ?: return
        // 调整对话框高度
        setDialogMaxHeight(activity)
        // 设置对话框外部触摸关闭
        dialog.setCanceledOnTouchOutside(mDialogParams.canceledOnTouchOutside)
        // 设置对话框返回键关闭
        dialog.setCancelable(mDialogParams.cancelable)
        val window = dialog.window ?: return
        // 设置对话框窗口属性
        setDialogWindowParams(activity, window)
        // 设置系统UI可视性
        if (mDialogParams.systemUiVisibility != 0) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
            )
        }
        super.onStart()
        // 设置系统UI可视性
        if (mDialogParams.systemUiVisibility != 0) {
            window.decorView.systemUiVisibility = mDialogParams.systemUiVisibility
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
        }
    }

    private fun setDialogMaxHeight(activity: Activity) {
        // 调整对话框高度
        val view = view ?: return
        if (mDialogParams.maxHeight > 0F && mDialogParams.maxHeight <= 1F) {
            view.viewTreeObserver?.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    val screenHeight: Int = PixelUtil.getScreenHeight(activity)
                    val maximumHeight = (screenHeight * mDialogParams.maxHeight).toInt()
                    if (view.height > maximumHeight) {
                        view.viewTreeObserver.removeOnGlobalLayoutListener(this)
                        view.layoutParams = FrameLayout.LayoutParams(
                            FrameLayout.LayoutParams.MATCH_PARENT, maximumHeight
                        )
                    }
                }
            })
        }
    }

    private fun setDialogWindowParams(activity: Activity, window: Window) {
        // 设置透明背景
        window.setBackgroundDrawableResource(android.R.color.transparent)
        // 设置位置
        window.attributes.also { params ->
            params.gravity = mDialogParams.gravity
            params.x = mDialogParams.xOffset
            params.y = mDialogParams.yOffset
            // 设置外边距
            if (mDialogParams.padding != null) {
                val padding = mDialogParams.padding as IntArray
                params.width = WindowManager.LayoutParams.MATCH_PARENT
                window.decorView.setPadding(padding[0], padding[1], padding[2], padding[3])
            } else {
                // 设置宽度
                if (mDialogParams.width > 1) {
                    params.width = mDialogParams.width.toInt()
                } else {
                    val screenWidth: Int = PixelUtil.getScreenWidth(activity)
                    params.width = (mDialogParams.width * screenWidth).toInt()
                }
            }
            window.attributes = params
        }
        // 动画
        if (mDialogParams.animationStyle != 0) {
            window.setWindowAnimations(mDialogParams.animationStyle)
        }
        // 背景昏暗
        if (mDialogParams.isDimEnabled) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        // 删除对话框
        fragmentManager?.beginTransaction()?.also { transaction ->
            transaction.remove(this)
            transaction.addToBackStack(null)
        }
    }

    override fun show(manager: FragmentManager, tag: String?) {
        val transaction = manager.beginTransaction()
        if (isAdded) {
            transaction.remove(this).commit()
        }
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.add(this, tag)
        transaction.commitAllowingStateLoss()
    }


    /**
     * 设置显示键盘模式
     */
    protected fun setSoftInputMode() {
        dialog?.window?.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
                    or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
        )
    }

    /**
     * 设置位置
     */
    protected fun setLocation(x: Int, y: Int) {
        val window = dialog?.window ?: return
        val params = window.attributes ?: return
        mDialogParams.xOffset = x
        mDialogParams.yOffset = y
        params.x = x
        params.y = y
        window.attributes = params
    }

    /**
     * 重置大小
     */
    protected fun resize() {
        val activity = activity ?: return
        val window = dialog?.window ?: return
        val params = window.attributes ?: return
        val average: Int = PixelUtil.getScreenWidth(activity) / 3
        if (mDialogParams.width < average) {
            params.width = average
        } else {
            params.width = mDialogParams.width.toInt()
        }
        window.attributes = params
    }
}