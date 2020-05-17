package com.xh3140.core.widget.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.FloatRange
import androidx.annotation.StyleRes
import com.xh3140.core.widget.dialog.listener.ButtonOnClickListener
import com.xh3140.core.widget.dialog.params.BuilderParams
import com.xh3140.core.widget.dialog.view.RootView


abstract class CircleDialog(params: BuilderParams) : BaseCircleDialog(params.dialogParams) {
    protected val mParams: BuilderParams = params

    protected abstract fun initRootView(context: Context, dialog: CircleDialog?): RootView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val context = context ?: return null
        return initRootView(context, this)
    }

    abstract class Builder<D : CircleDialog>(buttonCount: Int) {

        protected val mParams: BuilderParams = BuilderParams(buttonCount)

        abstract fun create(): D

        /**
         * 设置对话框属性
         * @see{BuilderParams}
         * @see{DialogParams}
         */

        fun setDialogGravity(gravity: Int): Builder<D> {
            mParams.dialogParams.gravity = gravity
            return this
        }

        fun setDialogCanceledOnTouchOutside(cancel: Boolean): Builder<D> {
            mParams.dialogParams.canceledOnTouchOutside = cancel
            return this
        }

        fun setDialogCanceledBack(cancel: Boolean): Builder<D> {
            mParams.dialogParams.cancelable = cancel
            return this
        }

        fun setDialogWidth(@FloatRange(from = 0.0, to = 1.0) width: Float): Builder<D> {
            mParams.dialogParams.width = width
            return this
        }

        fun setDialogMaxHeight(
            @FloatRange(from = 0.0, to = 1.0) maxHeight: Float
        ): Builder<D> {
            mParams.dialogParams.maxHeight = maxHeight
            return this
        }

        fun setDialogPadding(left: Int, top: Int, right: Int, bottom: Int): Builder<D> {
            mParams.dialogParams.padding = intArrayOf(left, top, right, bottom)
            return this
        }

        fun setDialogAnimations(@StyleRes animation: Int): Builder<D> {
            mParams.dialogParams.animationStyle = animation
            return this
        }

        fun setDialogIsDimEnabled(enabled: Boolean): Builder<D> {
            mParams.dialogParams.isDimEnabled = enabled
            return this
        }

        fun setDialogBackgroundColor(@ColorInt color: Int): Builder<D> {
            mParams.dialogParams.backgroundColor = color
            return this
        }

        fun setDialogRadius(radius: Int): Builder<D> {
            mParams.dialogParams.radius = radius
            return this
        }

        fun setDialogAlpha(@FloatRange(from = 0.0, to = 1.0) alpha: Float): Builder<D> {
            mParams.dialogParams.alpha = alpha
            return this
        }

        fun setDialogX(x: Int): Builder<D> {
            mParams.dialogParams.xOffset = x
            return this
        }

        fun setDialogY(y: Int): Builder<D> {
            mParams.dialogParams.yOffset = y
            return this
        }

        fun setDialogSystemUiVisibility(systemUiVisibility: Int): Builder<D> {
            mParams.dialogParams.systemUiVisibility = systemUiVisibility
            return this
        }

        /**
         * 设置首部视图属性
         * @see{BuilderParams}
         * @see{HeaderParams}
         * @see{TitleParams}
         * @see{SummaryParams}
         */


        /**
         * 设置首部图标属性
         * @see{HeaderParams}
         */

        fun setHeaderIcon(icon: Int): Builder<D> {
            mParams.headerParams.icon = icon
            return this
        }

        /**
         * 设置标题属性
         * @see{TitleParams}
         */

        fun setTitleText(text: String?): Builder<D> {
            mParams.headerParams.titleParams.text = text
            return this
        }

        fun setTitleTextSize(textSize: Int): Builder<D> {
            mParams.headerParams.titleParams.textSize = textSize.toFloat()
            return this
        }

        fun setTitleTextColor(@ColorInt textColor: Int): Builder<D> {
            mParams.headerParams.titleParams.textColor = textColor
            return this
        }

        fun setTitleTextStyle(textStyle: Int): Builder<D> {
            mParams.headerParams.titleParams.textStyle = textStyle
            return this
        }

        fun setTitleGravity(gravity: Int): Builder<D> {
            mParams.headerParams.titleParams.gravity = gravity
            return this
        }

        fun setTitleHeight(height: Int): Builder<D> {
            mParams.headerParams.titleParams.height = height
            return this
        }

        fun setTitlePadding(padding: IntArray?): Builder<D> {
            mParams.headerParams.titleParams.padding = padding
            return this
        }

        fun setTitlePadding(left: Int, top: Int, right: Int, bottom: Int): Builder<D> {
            mParams.headerParams.titleParams.padding = intArrayOf(left, top, right, bottom)
            return this
        }

        fun setTitleBackgroundColor(@ColorInt backgroundColor: Int): Builder<D> {
            mParams.headerParams.titleParams.backgroundColor = backgroundColor
            return this
        }

        /**
         * 设置副标题属性
         * @see{SummaryParams}
         */

        fun setSummaryText(text: String?): Builder<D> {
            mParams.headerParams.summaryParams.text = text
            return this
        }

        fun setSummaryTextSize(textSize: Float): Builder<D> {
            mParams.headerParams.summaryParams.textSize = textSize
            return this
        }

        fun setSummaryTextColor(@ColorInt textColor: Int): Builder<D> {
            mParams.headerParams.summaryParams.textColor = textColor
            return this
        }

        fun setSummaryTextStyle(textStyle: Int): Builder<D> {
            mParams.headerParams.summaryParams.textStyle = textStyle
            return this
        }

        fun setSummaryGravity(gravity: Int): Builder<D> {
            mParams.headerParams.summaryParams.gravity = gravity
            return this
        }

        fun setSummaryHeight(height: Int): Builder<D> {
            mParams.headerParams.summaryParams.height = height
            return this
        }

        fun setSummaryPadding(padding: IntArray?): Builder<D> {
            mParams.headerParams.summaryParams.padding = padding
            return this
        }

        fun setSummaryPadding(left: Int, top: Int, right: Int, bottom: Int): Builder<D> {
            mParams.headerParams.summaryParams.padding = intArrayOf(left, top, right, bottom)
            return this
        }

        fun setSummaryBackgroundColor(@ColorInt backgroundColor: Int): Builder<D> {
            mParams.headerParams.summaryParams.backgroundColor = backgroundColor
            return this
        }

        /**
         * 设置底部视图属性
         * @see{BuilderParams}
         * @see{FooterParams}
         * @see{ButtonParams}
         */

        fun setButtonText(index: Int, text: String?): Builder<D> {
            mParams.footerParams.buttonParamsList[index].text = text
            return this
        }

        fun setButtonTextSize(index: Int, textSize: Int): Builder<D> {
            mParams.footerParams.buttonParamsList[index].textSize = textSize.toFloat()
            return this
        }

        fun setButtonDisableTextColor(index: Int, @ColorInt textColor: Int): Builder<D> {
            mParams.footerParams.buttonParamsList[index].textColorDisable = textColor
            return this
        }

        fun setButtonTextStyle(index: Int, textStyle: Int): Builder<D> {
            mParams.footerParams.buttonParamsList[index].textStyle = textStyle
            return this
        }

        fun setButtonHeight(index: Int, height: Int): Builder<D> {
            mParams.footerParams.buttonParamsList[index].height = height.toFloat()
            return this
        }

        fun setButtonBackgroundColor(
            index: Int,
            @ColorInt backgroundColor: Int
        ): Builder<D> {
            mParams.footerParams.buttonParamsList[index].backgroundColor = backgroundColor
            return this
        }

        fun setButtonPressedBackgroundColor(
            index: Int,
            @ColorInt backgroundColor: Int
        ): Builder<D> {
            mParams.footerParams.buttonParamsList[index].backgroundColorPressed = backgroundColor
            return this
        }

        fun setButtonOnClickListener(listener: ButtonOnClickListener?): Builder<D> {
            mParams.footerParams.buttonOnClickListener = listener
            return this
        }

        fun setButtonOnClickListener(listener: ((CircleDialog, View, Int) -> Unit)): Builder<D> {
            mParams.footerParams.buttonOnClickListener = object : ButtonOnClickListener {
                override fun onClick(dialog: CircleDialog, view: View, index: Int) {
                    listener.invoke(dialog, view, index)
                }
            }
            return this
        }


        /**
         * 设置内容对话框属性
         * @see{ContentParams}
         */

        fun setContentText(text: String?): Builder<D> {
            mParams.bodyParams.contentParams.text = text
            return this
        }

        fun setContentTextSize(textSize: Float): Builder<D> {
            mParams.bodyParams.contentParams.textSize = textSize
            return this
        }

        fun setContentTextColor(@ColorInt textColor: Int): Builder<D> {
            mParams.bodyParams.contentParams.textColor = textColor
            return this
        }

        fun setContentTextStyle(textStyle: Int): Builder<D> {
            mParams.bodyParams.contentParams.textStyle = textStyle
            return this
        }

        fun setContentGravity(gravity: Int): Builder<D> {
            mParams.bodyParams.contentParams.gravity = gravity
            return this
        }

        fun setContentHeight(height: Int): Builder<D> {
            mParams.bodyParams.contentParams.height = height
            return this
        }

        fun setContentPadding(padding: IntArray?): Builder<D> {
            mParams.bodyParams.contentParams.padding = padding
            return this
        }

        fun setContentPadding(left: Int, top: Int, right: Int, bottom: Int): Builder<D> {
            mParams.bodyParams.contentParams.padding = intArrayOf(left, top, right, bottom)
            return this
        }

        fun setContentBackgroundColor(@ColorInt backgroundColor: Int): Builder<D> {
            mParams.bodyParams.contentParams.backgroundColor = backgroundColor
            return this
        }

        /**
         * 设置列表对话框属性
         * @see{ListParams}
         */

        fun setListItems(items: List<String>): Builder<D> {
            mParams.bodyParams.listParams.items = items
            return this
        }

        fun setListItems(index: Int, items: List<String>): Builder<D> {
            mParams.bodyParams.listParams.checkedIndex = index
            mParams.bodyParams.listParams.items = items
            return this
        }

        fun setListItems(indexList: List<Int>, items: List<String>): Builder<D> {
            mParams.bodyParams.listParams.checkedIndexList = indexList
            mParams.bodyParams.listParams.items = items
            return this
        }

        fun setCheckedIndex(index: Int): Builder<D> {
            mParams.bodyParams.listParams.checkedIndex = index
            return this
        }

        fun setCheckedIndexList(indexList: List<Int>): Builder<D> {
            mParams.bodyParams.listParams.checkedIndexList = indexList
            return this
        }

        fun setListItemIsMultiChoice(flag: Boolean): Builder<D> {
            mParams.bodyParams.listParams.isMultiChoice = flag
            return this
        }

        fun setIsDividerList(flag: Boolean): Builder<D> {
            mParams.bodyParams.listParams.isDividerList = flag
            return this
        }

        fun setListItemTextSize(textSize: Float): Builder<D> {
            mParams.bodyParams.listParams.textSize = textSize
            return this
        }

        fun setListItemTextColor(@ColorInt textColor: Int): Builder<D> {
            mParams.bodyParams.listParams.textColor = textColor
            return this
        }

        fun setListItemPadding(padding: IntArray?): Builder<D> {
            mParams.bodyParams.listParams.padding = padding
            return this
        }

        fun setListItemPadding(left: Int, top: Int, right: Int, bottom: Int): Builder<D> {
            mParams.bodyParams.listParams.padding = intArrayOf(left, top, right, bottom)
            return this
        }

        /**
         * 设置输入对话框属性
         * @see{InputParams}
         */
        fun setInputType(type: Int): Builder<D> {
            mParams.bodyParams.inputParams.type = type
            return this
        }

        fun setInputText(text: String?): Builder<D> {
            mParams.bodyParams.inputParams.text = text
            return this
        }

        fun setInputTextSize(textSize: Float): Builder<D> {
            mParams.bodyParams.inputParams.textSize = textSize
            return this
        }

        fun setInputTextColor(@ColorInt textColor: Int): Builder<D> {
            mParams.bodyParams.inputParams.textColor = textColor
            return this
        }

        fun setInputTextStyle(textStyle: Int): Builder<D> {
            mParams.bodyParams.inputParams.textStyle = textStyle
            return this
        }

        fun setInputGravity(gravity: Int): Builder<D> {
            mParams.bodyParams.inputParams.gravity = gravity
            return this
        }

        fun setInputHintText(text: String?): Builder<D> {
            mParams.bodyParams.inputParams.hint = text
            return this
        }

        fun setInputHintTextColor(@ColorInt textColor: Int): Builder<D> {
            mParams.bodyParams.inputParams.hintColor = textColor
            return this
        }

        fun setInputSingleLine(flag: Boolean): Builder<D> {
            mParams.bodyParams.inputParams.isSingleLine = flag
            return this
        }

        fun setInputMaxLines(lines: Int): Builder<D> {
            mParams.bodyParams.inputParams.maxLines = lines
            return this
        }

        fun setInputMaxEms(ems: Int): Builder<D> {
            mParams.bodyParams.inputParams.maxEms = ems
            return this
        }

        fun setInputMaxLength(length: Int): Builder<D> {
            mParams.bodyParams.inputParams.maxLength = length
            return this
        }

        fun setInputHeight(height: Int): Builder<D> {
            mParams.bodyParams.inputParams.height = height
            return this
        }

        fun setInputStrokeWidth(width: Int): Builder<D> {
            mParams.bodyParams.inputParams.strokeWidth = width
            return this
        }

        fun setInputStrokeColor(@ColorInt color: Int): Builder<D> {
            mParams.bodyParams.inputParams.strokeColor = color
            return this
        }

        fun setInputBackgroundResourceId(@DrawableRes resourceId: Int): Builder<D> {
            mParams.bodyParams.inputParams.backgroundResourceId = resourceId
            return this
        }

        fun setInputBackgroundColor(@ColorInt color: Int): Builder<D> {
            mParams.bodyParams.inputParams.backgroundColor = color
            return this
        }

        fun setInputMargins(margins: IntArray?): Builder<D> {
            mParams.bodyParams.inputParams.margins = margins
            return this
        }

        fun setInputMargins(left: Int, top: Int, right: Int, bottom: Int): Builder<D> {
            mParams.bodyParams.inputParams.margins = intArrayOf(left, top, right, bottom)
            return this
        }

        fun setInputPadding(padding: IntArray?): Builder<D> {
            mParams.bodyParams.inputParams.padding = padding
            return this
        }

        fun setInputPadding(left: Int, top: Int, right: Int, bottom: Int): Builder<D> {
            mParams.bodyParams.inputParams.padding = intArrayOf(left, top, right, bottom)
            return this
        }
    }
}

