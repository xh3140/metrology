package com.xh3140.core.widgets.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.FloatRange
import androidx.annotation.StyleRes
import com.xh3140.core.widgets.dialog.listener.ButtonOnClickListener
import com.xh3140.core.widgets.dialog.params.BuilderParams
import com.xh3140.core.widgets.dialog.params.DialogParams
import com.xh3140.core.widgets.dialog.view.ButtonView


abstract class CircleDialog(params: DialogParams) : BaseCircleDialog(params) {

    protected abstract fun initRootView(context: Context): View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val context = context ?: return null
        return initRootView(context)
    }

    abstract class Builder<D : CircleDialog>(buttonCount: Int) {

        protected val mParams: BuilderParams<D> = BuilderParams(buttonCount)

        abstract fun create(): D

        /**
         * 设置对话框属性
         * @see{BuilderParams}
         * @see{DialogParams}
         */

        fun setDialogGravity(gravity: Int): Builder<D> {
            mParams.mDialogParams.gravity = gravity
            return this
        }

        fun setDialogCanceledOnTouchOutside(cancel: Boolean): Builder<D> {
            mParams.mDialogParams.canceledOnTouchOutside = cancel
            return this
        }

        fun setDialogCanceledBack(cancel: Boolean): Builder<D> {
            mParams.mDialogParams.cancelable = cancel
            return this
        }

        fun setDialogWidth(@FloatRange(from = 0.0, to = 1.0) width: Float): Builder<D> {
            mParams.mDialogParams.width = width
            return this
        }

        fun setDialogMaxHeight(
            @FloatRange(from = 0.0, to = 1.0) maxHeight: Float
        ): Builder<D> {
            mParams.mDialogParams.maxHeight = maxHeight
            return this
        }

        fun setDialogPadding(left: Int, top: Int, right: Int, bottom: Int): Builder<D> {
            mParams.mDialogParams.padding = intArrayOf(left, top, right, bottom)
            return this
        }

        fun setDialogAnimations(@StyleRes animation: Int): Builder<D> {
            mParams.mDialogParams.animationStyle = animation
            return this
        }

        fun setDialogIsDimEnabled(enabled: Boolean): Builder<D> {
            mParams.mDialogParams.isDimEnabled = enabled
            return this
        }

        fun setDialogBackgroundColor(@ColorInt color: Int): Builder<D> {
            mParams.mDialogParams.backgroundColor = color
            return this
        }

        fun setDialogRadius(radius: Int): Builder<D> {
            mParams.mDialogParams.radius = radius
            return this
        }

        fun setDialogAlpha(@FloatRange(from = 0.0, to = 1.0) alpha: Float): Builder<D> {
            mParams.mDialogParams.alpha = alpha
            return this
        }

        fun setDialogX(x: Int): Builder<D> {
            mParams.mDialogParams.xOffset = x
            return this
        }

        fun setDialogY(y: Int): Builder<D> {
            mParams.mDialogParams.yOffset = y
            return this
        }

        fun setDialogSystemUiVisibility(systemUiVisibility: Int): Builder<D> {
            mParams.mDialogParams.systemUiVisibility = systemUiVisibility
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
         * 设置标题属性
         * @see{TitleParams}
         */

        fun setHeaderIcon(icon: Int): Builder<D> {
            mParams.mHeaderParams.mTitleParams.icon = icon
            return this
        }

        fun setTitleText(text: String?): Builder<D> {
            mParams.mHeaderParams.mTitleParams.text = text
            return this
        }

        fun setTitleTextSize(textSize: Int): Builder<D> {
            mParams.mHeaderParams.mTitleParams.textSize = textSize.toFloat()
            return this
        }

        fun setTitleTextColor(@ColorInt textColor: Int): Builder<D> {
            mParams.mHeaderParams.mTitleParams.textColor = textColor
            return this
        }

        fun setTitleTextStyle(textStyle: Int): Builder<D> {
            mParams.mHeaderParams.mTitleParams.textStyle = textStyle
            return this
        }

        fun setTitleGravity(gravity: Int): Builder<D> {
            mParams.mHeaderParams.mTitleParams.gravity = gravity
            return this
        }

        fun setTitleHeight(height: Int): Builder<D> {
            mParams.mHeaderParams.mTitleParams.height = height
            return this
        }

        fun setTitlePadding(padding: IntArray?): Builder<D> {
            mParams.mHeaderParams.mTitleParams.padding = padding
            return this
        }

        fun setTitlePadding(left: Int, top: Int, right: Int, bottom: Int): Builder<D> {
            mParams.mHeaderParams.mTitleParams.padding = intArrayOf(left, top, right, bottom)
            return this
        }

        fun setTitleBackgroundColor(@ColorInt backgroundColor: Int): Builder<D> {
            mParams.mHeaderParams.mTitleParams.backgroundColor = backgroundColor
            return this
        }

        /**
         * 设置副标题属性
         * @see{SummaryParams}
         */

        fun setSummaryText(text: String?): Builder<D> {
            mParams.mHeaderParams.mSummaryParams.text = text
            return this
        }

        fun setSummaryTextSize(textSize: Float): Builder<D> {
            mParams.mHeaderParams.mSummaryParams.textSize = textSize
            return this
        }

        fun setSummaryTextColor(@ColorInt textColor: Int): Builder<D> {
            mParams.mHeaderParams.mSummaryParams.textColor = textColor
            return this
        }

        fun setSummaryTextStyle(textStyle: Int): Builder<D> {
            mParams.mHeaderParams.mSummaryParams.textStyle = textStyle
            return this
        }

        fun setSummaryGravity(gravity: Int): Builder<D> {
            mParams.mHeaderParams.mSummaryParams.gravity = gravity
            return this
        }

        fun setSummaryHeight(height: Int): Builder<D> {
            mParams.mHeaderParams.mSummaryParams.height = height
            return this
        }

        fun setSummaryPadding(padding: IntArray?): Builder<D> {
            mParams.mHeaderParams.mSummaryParams.padding = padding
            return this
        }

        fun setSummaryPadding(left: Int, top: Int, right: Int, bottom: Int): Builder<D> {
            mParams.mHeaderParams.mSummaryParams.padding = intArrayOf(left, top, right, bottom)
            return this
        }

        fun setSummaryBackgroundColor(@ColorInt backgroundColor: Int): Builder<D> {
            mParams.mHeaderParams.mSummaryParams.backgroundColor = backgroundColor
            return this
        }

        /**
         * 设置底部视图属性
         * @see{BuilderParams}
         * @see{FooterParams}
         * @see{ButtonParams}
         */

        fun setButtonText(index: Int, text: String?): Builder<D> {
            mParams.mFooterParams.mButtonParamsList[index].text = text
            return this
        }

        fun setButtonTextSize(index: Int, textSize: Int): Builder<D> {
            mParams.mFooterParams.mButtonParamsList[index].textSize = textSize.toFloat()
            return this
        }

        fun setButtonDisableTextColor(index: Int, @ColorInt textColor: Int): Builder<D> {
            mParams.mFooterParams.mButtonParamsList[index].textColorDisable = textColor
            return this
        }

        fun setButtonTextStyle(index: Int, textStyle: Int): Builder<D> {
            mParams.mFooterParams.mButtonParamsList[index].textStyle = textStyle
            return this
        }

        fun setButtonHeight(index: Int, height: Int): Builder<D> {
            mParams.mFooterParams.mButtonParamsList[index].height = height.toFloat()
            return this
        }

        fun setButtonBackgroundColor(index: Int, @ColorInt backgroundColor: Int): Builder<D> {
            mParams.mFooterParams.mButtonParamsList[index].backgroundColor = backgroundColor
            return this
        }

        fun setButtonPressedBackgroundColor(index: Int, @ColorInt backgroundColor: Int): Builder<D> {
            mParams.mFooterParams.mButtonParamsList[index].backgroundColorPressed = backgroundColor
            return this
        }

        fun setButtonOnClickListener(listener: ButtonOnClickListener<D>?): Builder<D> {
            mParams.mFooterParams.buttonOnClickListener = listener
            return this
        }

        fun setButtonOnClickListener(listener: ((D, ButtonView, Int) -> Unit)): Builder<D> {
            mParams.mFooterParams.buttonOnClickListener = object : ButtonOnClickListener<D> {
                override fun onClick(dialog: D, button: ButtonView, index: Int) {
                    listener.invoke(dialog, button, index)
                }
            }
            return this
        }


        /**
         * 设置内容对话框属性
         * @see{ContentParams}
         */

        fun setContentText(text: String?): Builder<D> {
            mParams.mBodyParams.mContentParams.text = text
            return this
        }

        fun setContentTextSize(textSize: Float): Builder<D> {
            mParams.mBodyParams.mContentParams.textSize = textSize
            return this
        }

        fun setContentTextColor(@ColorInt textColor: Int): Builder<D> {
            mParams.mBodyParams.mContentParams.textColor = textColor
            return this
        }

        fun setContentTextStyle(textStyle: Int): Builder<D> {
            mParams.mBodyParams.mContentParams.textStyle = textStyle
            return this
        }

        fun setContentGravity(gravity: Int): Builder<D> {
            mParams.mBodyParams.mContentParams.gravity = gravity
            return this
        }

        fun setContentHeight(height: Int): Builder<D> {
            mParams.mBodyParams.mContentParams.height = height
            return this
        }

        fun setContentPadding(padding: IntArray?): Builder<D> {
            mParams.mBodyParams.mContentParams.padding = padding
            return this
        }

        fun setContentPadding(left: Int, top: Int, right: Int, bottom: Int): Builder<D> {
            mParams.mBodyParams.mContentParams.padding = intArrayOf(left, top, right, bottom)
            return this
        }

        fun setContentBackgroundColor(@ColorInt backgroundColor: Int): Builder<D> {
            mParams.mBodyParams.mContentParams.backgroundColor = backgroundColor
            return this
        }

        /**
         * 设置列表对话框属性
         * @see{ListParams}
         */

        fun setListItems(items: List<String>): Builder<D> {
            mParams.mBodyParams.mListParams.items = items
            return this
        }

        fun setListItems(index: Int, items: List<String>): Builder<D> {
            mParams.mBodyParams.mListParams.checkedIndex = index
            mParams.mBodyParams.mListParams.items = items
            return this
        }

        fun setListItems(indexList: List<Int>, items: List<String>): Builder<D> {
            mParams.mBodyParams.mListParams.checkedIndexList = indexList
            mParams.mBodyParams.mListParams.items = items
            return this
        }

        fun setCheckedIndex(index: Int): Builder<D> {
            mParams.mBodyParams.mListParams.checkedIndex = index
            return this
        }

        fun setCheckedIndexList(indexList: List<Int>): Builder<D> {
            mParams.mBodyParams.mListParams.checkedIndexList = indexList
            return this
        }

        fun setListItemIsMultipleChoice(flag: Boolean): Builder<D> {
            mParams.mBodyParams.mListParams.isMultipleChoice = flag
            return this
        }

        fun setIsDividerList(flag: Boolean): Builder<D> {
            mParams.mBodyParams.mListParams.isDividerList = flag
            return this
        }

        fun setListItemTextSize(textSize: Float): Builder<D> {
            mParams.mBodyParams.mListParams.textSize = textSize
            return this
        }

        fun setListItemTextColor(@ColorInt textColor: Int): Builder<D> {
            mParams.mBodyParams.mListParams.textColor = textColor
            return this
        }

        fun setListItemPadding(padding: IntArray?): Builder<D> {
            mParams.mBodyParams.mListParams.padding = padding
            return this
        }

        fun setListItemPadding(left: Int, top: Int, right: Int, bottom: Int): Builder<D> {
            mParams.mBodyParams.mListParams.padding = intArrayOf(left, top, right, bottom)
            return this
        }

        /**
         * 设置输入对话框属性
         * @see{InputParams}
         */
        fun setInputType(type: Int): Builder<D> {
            mParams.mBodyParams.mInputParams.type = type
            return this
        }

        fun setInputText(text: String?): Builder<D> {
            mParams.mBodyParams.mInputParams.text = text
            return this
        }

        fun setInputTextSize(textSize: Float): Builder<D> {
            mParams.mBodyParams.mInputParams.textSize = textSize
            return this
        }

        fun setInputTextColor(@ColorInt textColor: Int): Builder<D> {
            mParams.mBodyParams.mInputParams.textColor = textColor
            return this
        }

        fun setInputTextStyle(textStyle: Int): Builder<D> {
            mParams.mBodyParams.mInputParams.textStyle = textStyle
            return this
        }

        fun setInputGravity(gravity: Int): Builder<D> {
            mParams.mBodyParams.mInputParams.gravity = gravity
            return this
        }

        fun setInputHintText(text: String?): Builder<D> {
            mParams.mBodyParams.mInputParams.hint = text
            return this
        }

        fun setInputHintTextColor(@ColorInt textColor: Int): Builder<D> {
            mParams.mBodyParams.mInputParams.hintColor = textColor
            return this
        }

        fun setInputSingleLine(flag: Boolean): Builder<D> {
            mParams.mBodyParams.mInputParams.isSingleLine = flag
            return this
        }

        fun setInputMaxLines(lines: Int): Builder<D> {
            mParams.mBodyParams.mInputParams.maxLines = lines
            return this
        }

        fun setInputMaxEms(ems: Int): Builder<D> {
            mParams.mBodyParams.mInputParams.maxEms = ems
            return this
        }

        fun setInputMaxLength(length: Int): Builder<D> {
            mParams.mBodyParams.mInputParams.maxLength = length
            return this
        }

        fun setInputHeight(height: Int): Builder<D> {
            mParams.mBodyParams.mInputParams.height = height
            return this
        }

        fun setInputStrokeWidth(width: Int): Builder<D> {
            mParams.mBodyParams.mInputParams.strokeWidth = width
            return this
        }

        fun setInputStrokeColor(@ColorInt color: Int): Builder<D> {
            mParams.mBodyParams.mInputParams.strokeColor = color
            return this
        }

        fun setInputBackgroundResourceId(@DrawableRes resourceId: Int): Builder<D> {
            mParams.mBodyParams.mInputParams.backgroundResourceId = resourceId
            return this
        }

        fun setInputBackgroundColor(@ColorInt color: Int): Builder<D> {
            mParams.mBodyParams.mInputParams.backgroundColor = color
            return this
        }

        fun setInputMargins(margins: IntArray?): Builder<D> {
            mParams.mBodyParams.mInputParams.margins = margins
            return this
        }

        fun setInputMargins(left: Int, top: Int, right: Int, bottom: Int): Builder<D> {
            mParams.mBodyParams.mInputParams.margins = intArrayOf(left, top, right, bottom)
            return this
        }

        fun setInputPadding(padding: IntArray?): Builder<D> {
            mParams.mBodyParams.mInputParams.padding = padding
            return this
        }

        fun setInputPadding(left: Int, top: Int, right: Int, bottom: Int): Builder<D> {
            mParams.mBodyParams.mInputParams.padding = intArrayOf(left, top, right, bottom)
            return this
        }
    }
}

