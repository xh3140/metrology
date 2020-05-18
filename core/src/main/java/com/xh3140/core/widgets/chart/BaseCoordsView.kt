package com.xh3140.core.widgets.chart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import kotlin.math.ceil

/**
 * Copyright (C), 2020-2020
 * FileName: BaseCoordsView.kt
 * ClassName: BaseCoordsView(坐标系视图基础类)
 * Description: 绘制了基础的坐标系，但并没有实现更多的功能
 * CreateDate: 2020-02-04
 * @author xh3140
 * @version 1.00
 */
open class BaseCoordsView : AbsCoordsView {
    // 锚点坐标(动态)
    protected var mAnchorX: Float = Float.NaN
    protected var mAnchorY: Float = Float.NaN

    // 坐标系标签区域大小(自动)
    protected var mLabelAreaLeft: Float = 0f
    protected var mLabelAreaTop: Float = 0f
    protected var mLabelAreaRight: Float = 0f
    protected var mLabelAreaBottom: Float = 0f

    // 坐标轴边界(自动)
    protected var mMinimumX: Float = 0f
    protected var mMinimumY: Float = 0f
    protected var mMaximumX: Float = 0f
    protected var mMaximumY: Float = 0f

    // 坐标轴和刻度画笔
    private val mPaintAxis: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        strokeWidth = 3f
        style = Paint.Style.STROKE
    }

    // 网格线画笔
    private val mPaintGrids: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GRAY
        strokeWidth = 1f
        style = Paint.Style.STROKE
    }

    // 坐标轴标签文本画笔
    private val mPaintAxisLabel: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        strokeWidth = 2f
        textSize = sp2px(12)
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD_ITALIC)
    }

    // 坐标轴刻度标签文本画笔
    private val mPaintScaleLabel: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.DKGRAY
        strokeWidth = 2f
        textSize = sp2px(9)
    }

    /**
     * 构造函数
     */
    constructor(context: Context)
            : super(context)

    constructor(context: Context, attrs: AttributeSet?)
            : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)

    /**
     * 将坐标系中的坐标转换为Canvas的实际坐标
     */
    override fun getCanvasX(x: Float): Float =
        mAnchorX + (x - mScaleListX.initial) * mBasicGridX / mScaleListX.interval

    override fun getCanvasY(y: Float): Float =
        mAnchorY - (y - mScaleListY.initial) * mBasicGridY / mScaleListY.interval

    /**
     * 判断坐标点是否在坐标系内
     */
    protected fun isOutsideSystem(x: Float, y: Float): Boolean {
        return x < mMinimumX || x > mMaximumX || y < mMinimumY || y > mMaximumY
    }

    /**
     * 重置锚点
     */
    fun resetAnchorPoint() {
        mAnchorX = mMinimumX + mBasicGridX / 2
        mAnchorY = mMaximumY - mBasicGridY / 2
    }

    /**
     * 更新与绘图和布局有关的参数
     */
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        // 标签文字区域（自动）
        mLabelAreaLeft = mScaleListY.getMaxWidth(mPaintScaleLabel) + 30f
        mLabelAreaTop = getTextHeight(mPaintAxisLabel, mAxisLabelY) + 30f
        mLabelAreaRight = getTextWidth(mPaintAxisLabel, mAxisLabelX) + 30f
        mLabelAreaBottom = mScaleListX.getMaxHeight(mPaintScaleLabel) + 30f
        // 坐标轴边界(自动)
        mMinimumX = mPaddingLeft + mLabelAreaLeft
        mMinimumY = mPaddingTop + mLabelAreaTop
        mMaximumX = width - mPaddingRight - mLabelAreaRight
        mMaximumY = height - mPaddingBottom - mLabelAreaBottom
        // 初始化时赋值，锚点坐标(动态)
        if (mAnchorX.isNaN() && mAnchorY.isNaN()) {
            mAnchorX = mMinimumX + mBasicGridX / 2
            mAnchorY = mMaximumY - mBasicGridY / 2
        }
    }

    /**
     * 缩放手势处理,控制坐标系的缩放
     * @param focusX 焦点的X坐标
     * @param focusY 焦点的Y坐标
     * @param factory 缩放因子，小于1时代表缩小，大于1时代表放大
     * @return Boolean 是否进行了事件处理
     */
    override fun onScaleGesture(focusX: Float, focusY: Float, factory: Float): Boolean {
        // 焦点不在坐标系内
        if (isOutsideSystem(focusX, focusY)) return false
        // 已缩至最小
        if (factory < 1f && (mBasicGridX < 25f || mBasicGridY < 25f)) {
            return false
        }
        // 已放至最大
        if (factory > 1f && ((mBasicGridX > mMaximumX - mMinimumX) && (mBasicGridY > mMaximumY - mMinimumY))) {
            return false
        }
        // 缩放操作
        mBasicGridX *= factory
        mBasicGridY *= factory
        // 根据缩放焦点调整锚点坐标
        mAnchorX = focusX + (mAnchorX - focusX) * factory
        mAnchorY = focusY + (mAnchorY - focusY) * factory
        invalidate()
        return true
    }

    /**
     * 滑动手势处理,控制坐标系的上下左右滑动
     * @param startX 起点的X坐标
     * @param startY 起点的Y坐标
     * @param distanceX 滑动距离的X轴投影，等于起点的X坐标 - 当前触摸点的X坐标
     * @param distanceY 滑动距离的Y轴投影，等于起点的Y坐标 - 当前触摸点的Y坐标
     * @return Boolean 是否进行了事件处理
     */
    override fun onScrollGesture(
        startX: Float,
        startY: Float,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        // 起点不在坐标系内
        if (isOutsideSystem(startX, startY)) return false
        // 在X轴活动范围内
        if ((distanceX < 0 && getCanvasX(mScaleListX.start) - mBasicGridX < mMaximumX)
            || (distanceX > 0 && getCanvasX(mScaleListX.end) + mBasicGridX > mMinimumX)
        ) {
            mAnchorX -= distanceX
        }
        // 在Y轴活动范围内
        if ((distanceY < 0 && getCanvasY(mScaleListY.end) - mBasicGridY < mMaximumY)
            || (distanceY > 0 && getCanvasY(mScaleListY.start) + mBasicGridY > mMinimumY)
        ) {
            mAnchorY -= distanceY
        }
        invalidate()
        return true
    }


    /**
     * 线程绘图，绘制图形（代替onDraw方法）
     */
    override fun onThreadDraw(canvas: Canvas) {
        drawAxisX(canvas)
        drawAxisY(canvas)
        drawGridsX(canvas)
        drawGridsY(canvas)
    }

    /**
     * 绘制X坐标轴
     */
    private fun drawAxisX(canvas: Canvas) {
        // X轴线
        canvas.drawLine(mMinimumX, mMaximumY, mMaximumX, mMaximumY, mPaintAxis)
        // X轴箭头
        canvas.drawLine(mMaximumX, mMaximumY, mMaximumX - 15f, mMaximumY + 10f, mPaintAxis)
        canvas.drawLine(mMaximumX, mMaximumY, mMaximumX - 15f, mMaximumY - 10f, mPaintAxis)
        // X标签
        val textX = mMaximumX + 15f
        val textY = mMaximumY + getTextHeight(mPaintAxisLabel, mAxisLabelX) / 2
        canvas.drawText(mAxisLabelX, textX, textY, mPaintAxisLabel)
    }

    /**
     * 绘制Y坐标轴
     */
    private fun drawAxisY(canvas: Canvas) {
        // Y轴线
        canvas.drawLine(mMinimumX, mMaximumY, mMinimumX, mMinimumY, mPaintAxis)
        // Y轴箭头
        canvas.drawLine(mMinimumX, mMinimumY, mMinimumX - 10f, mMinimumY + 15f, mPaintAxis)
        canvas.drawLine(mMinimumX, mMinimumY, mMinimumX + 10f, mMinimumY + 15f, mPaintAxis)
        // Y标签
        val textX = mMinimumX - getTextWidth(mPaintAxisLabel, mAxisLabelY) / 2
        val textY = mMinimumY - 15f
        canvas.drawText(mAxisLabelY, textX, textY, mPaintAxisLabel)
    }

    /**
     * 绘制X轴刻度、竖直网格线和刻度标签
     */
    private fun drawGridsX(canvas: Canvas) {
        val times = ceil((mMinimumX - mAnchorX) / mBasicGridX).toInt()
        var index = mScaleListX.index + times
        var currentX = mAnchorX + times * mBasicGridX
        while (currentX <= mMaximumX - 50f) {
            // 竖直网格线
            canvas.drawLine(currentX, mMaximumY, currentX, mMinimumY + 25f, mPaintGrids)
            // X轴刻度
            canvas.drawLine(currentX, mMaximumY, currentX, mMaximumY - 15f, mPaintAxis)
            // X轴刻度标签
            if (index >= 0 && index < mScaleListX.count) {
                val text = mScaleListX.getText(index)
                val textX = currentX - getTextWidth(mPaintScaleLabel, text) / 2
                val textY = mMaximumY + getTextHeight(mPaintScaleLabel, text) + 15f
                canvas.drawText(text, textX, textY, mPaintScaleLabel)
            }
            ++index
            currentX += mBasicGridX
        }
    }

    /**
     * 绘制Y轴刻度、水平网格线和刻度标签
     */
    private fun drawGridsY(canvas: Canvas) {
        val times = ceil((mAnchorY - mMaximumY) / mBasicGridY).toInt()
        var index = mScaleListY.index + times
        var currentY = mAnchorY - times * mBasicGridY
        while (currentY >= mMinimumY + 50f) {
            // 水平网格线
            canvas.drawLine(mMinimumX, currentY, mMaximumX - 25f, currentY, mPaintGrids)
            // Y轴刻度
            canvas.drawLine(mMinimumX, currentY, mMinimumX + 15f, currentY, mPaintAxis)
            // Y轴刻度标签
            if (index >= 0 && index < mScaleListY.count) {
                val text = mScaleListY.getText(index)
                val textX = mMinimumX - getTextWidth(mPaintScaleLabel, text) - 15f
                val textY = currentY + getTextHeight(mPaintScaleLabel, text) / 2
                canvas.drawText(text, textX, textY, mPaintScaleLabel)
            }
            ++index
            currentY -= mBasicGridY
        }
    }
}