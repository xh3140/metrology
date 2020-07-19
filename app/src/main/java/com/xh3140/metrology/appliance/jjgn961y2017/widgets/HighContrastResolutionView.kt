package com.xh3140.metrology.appliance.jjgn961y2017.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.pow

/**
 * 螺旋CT检测模体低对比度层视图
 */
class HighContrastResolutionView : View {
    // 模块半径 mm
    private val mModelRadius: Float = 100f

    // 线对长度 mm
    private val mLinesLength: Float = 6f

    // 线对间距 mm
    private val mInterval: FloatArray = floatArrayOf(5.00f, 2.50f, 1.67f, 1.25f, 1.00f, 0.83f, 0.71f, 0.63f, 0.56f, 0.50f, 0.45f, 0.42f, 0.38f, 0.36f, 0.33f, 0.31f, 0.29f, 0.28f, 0.26f, 0.25f, 0.24f)

    // 比例
    private var mRatio: Float = 1f

    // 模体圆心坐标
    private var mCircleX = 0f
    private var mCircleY = 0f

    // 模体半径
    private var mRadiusBody = 100f


    // 触点
    private var mTouchX: Float = 0f
    private var mTouchY: Float = 0f
    private var mChecked: Boolean = false

    // 监听器
    //private var mOnClickHoleListener: OnClickHoleListener? = null

    private val mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.DKGRAY
        strokeWidth = 1f
        style = Paint.Style.FILL_AND_STROKE
    }

    private fun isTouchInside(x: Float, y: Float, radius: Float): Boolean {
        return (x - mTouchX).pow(2) + (y - mTouchY).pow(2) <= radius.pow(2)
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    interface OnClickHoleListener {
        fun onClickHole(index: Int, contrast: Float, radius: Float)
    }
//
//    fun setOnClickHoleListener(listener: OnClickHoleListener?) {
//        mOnClickHoleListener = listener
//    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            val minSize = minOf(widthSize, heightSize)
            setMeasuredDimension(minSize, minSize)
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(heightSize, heightSize)
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, widthSize)
        } else {
            setMeasuredDimension(widthSize, heightSize)
        }
    }


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        mRatio = 0.45f * minOf(width, height) / mModelRadius
        mCircleX = width / 2f
        mCircleY = height / 2f
        mRadiusBody = mRatio * mModelRadius
    }

    override fun performClick(): Boolean {
        var something = 0
        ++something
        return super.performClick()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                performClick()
                mTouchX = event.x
                mTouchY = event.y
                mChecked = false
                invalidate()
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawModel()
        canvas.drawLines()
        canvas.drawCheckModel()
    }

    private fun Canvas.drawModel() {
        mPaint.color = Color.BLACK
        mPaint.style = Paint.Style.STROKE
        drawCircle(mCircleX, mCircleY, mRadiusBody, mPaint)
    }

    private fun Canvas.drawLines() {
        var degree = -10f
        for(i in 0..20){
            //drawRect()

        }
    }


    private fun Canvas.drawCheckModel() {
        if (!mChecked && isTouchInside(mCircleX, mCircleY, mRadiusBody)) {
            mChecked = true
            mPaint.color = Color.RED
            mPaint.style = Paint.Style.STROKE
            drawCircle(mCircleX, mCircleY, mRadiusBody, mPaint)
        }
    }
}