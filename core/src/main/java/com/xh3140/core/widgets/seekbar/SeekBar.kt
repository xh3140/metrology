package com.xh3140.core.widgets.seekbar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.text.TextPaint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import com.xh3140.core.extensions.dp2px
import com.xh3140.core.extensions.getTextBoundsHeight
import com.xh3140.core.extensions.getTextBoundsWidth
import com.xh3140.core.extensions.sp2px
import kotlin.math.pow

class SeekBar : View {
    // 标签
    private var mLabels: List<String> = emptyList()

    // 标签位置
    private var mLabelPosition: Int = LABEL_POSITION_TOP

    // 标签字体大小 sp
    private var mLabelTextSize: Float = 12f

    // 标签字体颜色 argb
    private var mLabelTextColor: Int = Color.parseColor("#808080")

    // 标签选中状态字体颜色 argb
    private var mLabelCheckedTextColor: Int = Color.parseColor("#000000")

    // 标签与滑条的距离 px
    private var mLabelDistance: Int = dp2px(15)

    // 滑条宽度 px
    private var mSliderStripWidth: Int = dp2px(8)

    // 滑条圆角半径 px
    private var mSliderStripRadius: Int = dp2px(4)

    // 滑条颜色 argb
    private var mSliderStripColor: Int = Color.parseColor("#C0C0C0")

    // 滑条选中状态颜色 argb
    private var mSliderStripCheckedColor: Int = Color.parseColor("#32CD32")

    // 滑块颜色 argb
    private var mSliderBlockColor: Int = Color.parseColor("#F5F5F5")

    // 滑块半径 px
    private var mSliderBlockRadius: Int = dp2px(12)

    // 内边距 px
    private var mSeekPadding: IntArray = intArrayOf(0, 0, 0, 0)

    // 标签文本宽度 px
    private var mLabelTextWidth: Int = 0

    // 标签文本高度 px
    private var mLabelTextHeight: Int = 0

    // 当前标签索引
    private var mSliderBlockIndex: Int = 0

    // 滑动横坐标
    private var mScrollX: Int = 0

    // 滑动纵坐标
    private var mScrollY: Int = 0

    // 滑条区域
    private val mSliderStripRectF: RectF = RectF(0f, 0f, 0f, 0f)

    // 标签文本画笔
    private val mPaintLabelText = TextPaint(Paint.ANTI_ALIAS_FLAG)

    // 滑条画笔
    private val mPaintSliderStrip = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 3f
        style = Paint.Style.FILL_AND_STROKE
    }

    // 滑块画笔
    private val mPaintSliderBlock = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 3f
        style = Paint.Style.FILL_AND_STROKE
    }

    // 手势事件
    private val mGestureDetector: GestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            if (e == null || mLabels.size < 2) return false
            val radius = dp2px(mSliderBlockRadius)
            val halfWidth = (mSliderStripRectF.right - mSliderStripRectF.left) / (mLabels.size - 1) / 2
            val centerY = (mSliderStripRectF.top + mSliderStripRectF.bottom) / 2
            for (index in mLabels.indices) {
                if (index != mSliderBlockIndex) {
                    val centerX = getIndexLabelCenterX(index)
                    if (e.x > centerX - halfWidth && e.x < centerX + halfWidth && e.y > centerY - radius && e.y < centerY + radius) {
                        mSliderBlockIndex = index
                        invalidate()
                        return true
                    }
                }
            }
            return false
        }

        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
            if (e1 == null || e2 == null) return false
            if (distanceX == 0f || mLabels.size < 2) return false
            if (mSliderBlockIndex == 0 && distanceX > 0) return false
            if (mSliderBlockIndex == mLabels.size - 1 && distanceX < 0) return false
            val radius = dp2px(mSliderBlockRadius).toFloat()
            val blockX = getIndexLabelCenterX(mSliderBlockIndex)
            val blockY = (mSliderStripRectF.top + mSliderStripRectF.bottom) / 2
            if ((e1.x - blockX).pow(2) + (e1.y - blockY).pow(2) > radius.pow(2)) return false
            val halfWidth = (mSliderStripRectF.right - mSliderStripRectF.left) / (mLabels.size - 1) / 2
            for (index in mLabels.indices) {
                if (index != mSliderBlockIndex) {
                    val centerX = getIndexLabelCenterX(index)
                    if (e2.x > centerX - halfWidth && e2.x < centerX + halfWidth) {
                        mSliderBlockIndex = index
                        invalidate()
                        return true
                    }
                }
            }
            return true
        }
    })

    /**
     * 构造函数
     */
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        mLabels = listOf("初级码农", "中级码农", "高级码农", "CTO", "卒")
        mSeekPadding = intArrayOf(dp2px(30), dp2px(30), dp2px(30), dp2px(30))
        mSliderBlockIndex = 2
    }

    /**
     * 获取标签的中心横向坐标
     */
    private fun getIndexLabelCenterX(index: Int): Float {
        return if (mLabels.size == 1) {
            mSliderStripRectF.left + mSliderStripRectF.right
        } else {
            mSliderStripRectF.left + index * (mSliderStripRectF.right - mSliderStripRectF.left) / (mLabels.size - 1)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        // 标签文本画笔
        mPaintLabelText.textSize = sp2px(mLabelTextSize).toFloat()
        // 标签文本宽度和高度 px
        mLabelTextWidth = mPaintLabelText.getTextBoundsWidth(mLabels.joinToString(" "))
        mLabelTextHeight = mPaintLabelText.getTextBoundsHeight(mLabels.joinToString())
        val width = mLabelTextWidth + mSeekPadding[0] + mSeekPadding[2]
        val height = mLabelTextHeight + mLabelDistance + mSliderStripWidth + mSeekPadding[1] + mSeekPadding[3]
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(width, height)
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(width, heightSize)
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, height)
        } else {
            setMeasuredDimension(widthSize, heightSize)
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        // 滑条区域
        mSliderStripRectF.left = mSeekPadding[0].toFloat()
        mSliderStripRectF.top = mSeekPadding[1] + mLabelTextHeight + mLabelDistance.toFloat()
        mSliderStripRectF.right = width - mSeekPadding[2].toFloat()
        mSliderStripRectF.bottom = height - mSeekPadding[3].toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        val radius = dp2px(mSliderStripRadius).toFloat()
        val centerX = getIndexLabelCenterX(mSliderBlockIndex)
        // 绘制滑条
        if (mSliderBlockIndex == 0 || mLabels.isEmpty()) {
            mPaintSliderStrip.color = mSliderStripColor
            canvas.drawRoundRect(mSliderStripRectF, radius, radius, mPaintSliderStrip)
        } else if (mSliderBlockIndex == mLabels.size - 1) {
            mPaintSliderStrip.color = mSliderStripCheckedColor
            canvas.drawRoundRect(mSliderStripRectF, radius, radius, mPaintSliderStrip)
        } else {
            mPaintSliderStrip.color = mSliderStripCheckedColor
            canvas.drawRoundRect(mSliderStripRectF.left, mSliderStripRectF.top, centerX, mSliderStripRectF.bottom, radius, radius, mPaintSliderStrip)
            mPaintSliderStrip.color = mSliderStripColor
            canvas.drawRoundRect(centerX, mSliderStripRectF.top, mSliderStripRectF.right, mSliderStripRectF.bottom, radius, radius, mPaintSliderStrip)
        }
        // 绘制滑块
        mPaintSliderBlock.color = mSliderBlockColor
        canvas.drawCircle(centerX, (mSliderStripRectF.top + mSliderStripRectF.bottom) / 2, mSliderBlockRadius.toFloat(), mPaintSliderBlock)
        // 绘制标签
        if (mLabels.isNotEmpty()) {
            val labelY = mLabelTextHeight + mSeekPadding[1].toFloat()
            for ((i, label) in mLabels.withIndex()) {
                val halfWidth = mPaintLabelText.getTextBoundsWidth(label) / 2
                val labelX = getIndexLabelCenterX(i) - halfWidth
                canvas.drawText(label, labelX, labelY, mPaintLabelText)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        mGestureDetector.onTouchEvent(event)
        return true
    }

    companion object {
        // 标签位置 上方
        const val LABEL_POSITION_TOP: Int = -1

        // 标签位置 下方
        const val LABEL_POSITION_BOTTOM: Int = -2
    }

    /**
     * 设置标签
     */
    fun setLabels(labels: List<String>) {
        if (mLabels != labels) {
            mLabels = labels
            requestLayout()
        }
    }

    /**
     * 设置标签位置
     */
    fun setLabelPosition(position: Int) {
        if (position != LABEL_POSITION_TOP || position != LABEL_POSITION_BOTTOM) {
            throw IllegalArgumentException("label position mast be LABEL_POSITION_TOP or LABEL_POSITION_BOTTOM.")
        } else {
            if (mLabelPosition != position) {
                mLabelPosition = position
                requestLayout()
            }
        }
    }

    /**
     * 设置标签字体大小 sp
     */
    fun setLabelTextSize(textSize: Float) {
        if (mLabelTextSize != textSize) {
            mLabelTextSize = textSize
            requestLayout()
        }
    }

    /**
     * 设置标签字体颜色
     */
    fun setLabelTextColor(color: Int) {
        if (mLabelTextColor != color) {
            mLabelTextColor = color
            invalidate()
        }
    }

    /**
     * 设置标签选中状态字体颜色
     */
    fun setLabelCheckedTextColor(color: Int) {
        if (mLabelCheckedTextColor != color) {
            mLabelCheckedTextColor = color
            invalidate()
        }
    }

    /**
     * 设置标签与滑条的距离 px
     */
    fun setLabelDistance(distance: Int) {
        if (mLabelDistance != distance) {
            mLabelDistance = distance
            requestLayout()
        }
    }

    /**
     * 设置滑条宽度 px
     */
    fun setSliderStripWidth(width: Int) {
        if (mSliderStripWidth != width) {
            mSliderStripWidth = width
            requestLayout()
        }
    }

    /**
     * 滑条圆角半径 px
     */
    fun setSliderStripRadius(radius: Int) {
        if (mSliderStripRadius != radius) {
            mSliderStripRadius = radius
            requestLayout()
        }
    }

    /**
     * 设置滑条颜色 argb
     */
    fun setSliderStripColor(color: Int) {
        if (mSliderStripColor != color) {
            mSliderStripColor = color
            invalidate()
        }
    }

    /**
     * 设置滑条选中状态颜色 argb
     */
    fun setSliderStripCheckedColor(color: Int) {
        if (mSliderStripCheckedColor != color) {
            mSliderStripCheckedColor = color
            invalidate()
        }
    }

    /**
     * 设置滑块颜色 argb
     */
    fun setSliderBlockColor(color: Int) {
        if (mSliderBlockColor != color) {
            mSliderBlockColor = color
            invalidate()
        }
    }

    /**
     * 设置滑块半径 px
     */
    fun setSliderBlockRadius(radius: Int) {
        if (mSliderBlockRadius != radius) {
            mSliderBlockRadius = radius
            requestLayout()
        }
    }

    /**
     * 设置内边距 px
     */
    fun setSeekPadding(padding: IntArray) {
        if (!padding.contentEquals(mSeekPadding)) {
            mSeekPadding = padding
            requestLayout()
        }
    }
}