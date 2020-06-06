package com.xh3140.metrology.appliance.jjg.jjgn961y2017.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin

/**
 * 螺旋CT检测模体低对比度层视图
 */
class LowContrastResolutionView : View {
    // 模块半径 mm
    private val mModelRadius: Float = 100f

    // 4孔序号半径 mm
    private val mHole4Radius: FloatArray = floatArrayOf(4.5f, 3.5f, 2.5f, 1.5f)

    // 9孔序号半径 mm
    private val mHole9Radius: FloatArray = floatArrayOf(7.5f, 4.5f, 4f, 3.5f, 3f, 2.5f, 2f, 1.5f, 1f)

    // 比例
    private var mRatio: Float = 1f

    // 模体圆心坐标
    private var mCircleX = 0f
    private var mCircleY = 0f

    // 模体半径
    private var mRadiusBody = 100f
    private var mRadius4Hole = 0f
    private var mRadius9Hole = 0f

    // 触点
    private var mTouchX: Float = 0f
    private var mTouchY: Float = 0f
    private var mChecked: Boolean = false

    // 监听器
    private var mOnClickHoleListener: OnClickHoleListener? = null

    private val mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply { strokeWidth = 3f }

    private fun isTouchInside(x: Float, y: Float, radius: Float): Boolean {
        return (x - mTouchX).pow(2) + (y - mTouchY).pow(2) <= radius.pow(2)
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun setOnClickHoleListener(listener: OnClickHoleListener?) {
        mOnClickHoleListener = listener
    }

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
        mRadius4Hole = mRadiusBody / 3
        mRadius9Hole = mRadiusBody * 2 / 3
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

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            drawModel()
            drawHole(90f, 1.0f)
            drawHole(210f, 0.3f)
            drawHole(330f, 0.5f)
            drawCheckModel()
        }
    }

    private fun Canvas.drawModel() {
        mPaint.color = Color.BLACK
        mPaint.style = Paint.Style.STROKE
        drawCircle(mCircleX, mCircleY, mRadiusBody, mPaint)
    }

    private fun Canvas.drawCheckHole(x: Float, y: Float, radius: Float, contrast: Float) {
        mPaint.color = Color.RED
        mPaint.style = Paint.Style.FILL_AND_STROKE
        mPaint.alpha = (255 * contrast).toInt()
        drawCircle(x, y, radius, mPaint)
        mPaint.color = Color.DKGRAY
        mPaint.style = Paint.Style.FILL_AND_STROKE
        mPaint.alpha = (255 * contrast).toInt()
    }

    private fun Canvas.drawHole(degree: Float, contrast: Float) {
        mPaint.color = Color.DKGRAY
        mPaint.style = Paint.Style.FILL_AND_STROKE
        mPaint.alpha = (255 * contrast).toInt()
        draw4Hole(degree, contrast)
        draw9Hole(degree, contrast)
    }

    private fun Canvas.draw4Hole(degree: Float, contrast: Float) {
        var angle4hole = degree + 30f
        var distance4hole = 26f
        for (i in mHole4Radius.indices) {
            val radian = angle4hole * PI / 180f
            val radius = mRatio * mHole4Radius[i]
            val x = width - mCircleX - mRadius4Hole * cos(radian).toFloat()
            val y = height - mCircleY - mRadius4Hole * sin(radian).toFloat()
            if (!mChecked && isTouchInside(x, y, radius + 15f)) {
                mChecked = true
                drawCheckHole(x, y, radius, contrast)
                mOnClickHoleListener?.onClickHole(i, contrast, mHole4Radius[i])
            } else {
                drawCircle(x, y, radius, mPaint)
            }
            angle4hole += distance4hole
            distance4hole -= 4f
        }
    }

    private fun Canvas.draw9Hole(degree: Float, contrast: Float) {
        var angle9hole = degree
        var distance9hole = 20f
        for (i in mHole9Radius.indices) {
            val radian = angle9hole * PI / 180f
            val radius = mRatio * mHole9Radius[i]
            val x = width - mCircleX - mRadius9Hole * cos(radian).toFloat()
            val y = height - mCircleY - mRadius9Hole * sin(radian).toFloat()
            if (!mChecked && isTouchInside(x, y, radius + 15f)) {
                mChecked = true
                drawCheckHole(x, y, radius, contrast)
                mOnClickHoleListener?.onClickHole(i, contrast, mHole9Radius[i])
            } else {
                drawCircle(x, y, radius, mPaint)
            }
            angle9hole += distance9hole
            distance9hole -= 2f
        }
    }

    private fun Canvas.drawCheckModel() {
        if (!mChecked && isTouchInside(mCircleX, mCircleY, mRadiusBody)) {
            mChecked = true
            mPaint.color = Color.RED
            mPaint.style = Paint.Style.STROKE
            drawCircle(mCircleX, mCircleY, mRadiusBody, mPaint)
            mOnClickHoleListener?.onClickHole(-1, 1f, mRadiusBody)
        }
    }
}