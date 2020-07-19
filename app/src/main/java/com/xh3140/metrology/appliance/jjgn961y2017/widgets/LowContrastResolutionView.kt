package com.xh3140.metrology.appliance.jjgn961y2017.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
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

    // 4孔圆心点坐标
    private val mHole4Contrast030Points: List<PointF> = List(4) { PointF(0f, 0f) }
    private val mHole4Contrast050Points: List<PointF> = List(4) { PointF(0f, 0f) }
    private val mHole4Contrast100Points: List<PointF> = List(4) { PointF(0f, 0f) }

    // 9孔圆心点坐标
    private val mHole9Contrast030Points: List<PointF> = List(9) { PointF(0f, 0f) }
    private val mHole9Contrast050Points: List<PointF> = List(9) { PointF(0f, 0f) }
    private val mHole9Contrast100Points: List<PointF> = List(9) { PointF(0f, 0f) }

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

    interface OnClickHoleListener {
        fun onClickHole(index: Int, contrast: Float, radius: Float)
    }

    fun setOnClickHoleListener(listener: OnClickHoleListener?) {
        mOnClickHoleListener = listener
    }

    private fun init4HolePoints(degree: Float, points: List<PointF>) {
        var deg4hole = degree + 30f
        var step4hole = 26f
        val radius4Hole = mRadiusBody / 3
        for (i in 0..3) {
            val radian = deg4hole * PI / 180f
            points[i].x = width - mCircleX - radius4Hole * cos(radian).toFloat()
            points[i].y = height - mCircleY - radius4Hole * sin(radian).toFloat()
            deg4hole += step4hole
            step4hole -= 4f
        }
    }

    private fun init9HolePoints(degree: Float, points: List<PointF>) {
        var deg9hole = degree + 30f
        var step9hole = 20f
        val radius9Hole = mRadiusBody * 2 / 3
        for (i in 0..8) {
            val radian = deg9hole * PI / 180f
            points[i].x = width - mCircleX - radius9Hole * cos(radian).toFloat()
            points[i].y = height - mCircleY - radius9Hole * sin(radian).toFloat()
            deg9hole += step9hole
            step9hole -= 2f
        }
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
        init4HolePoints(210f, mHole4Contrast030Points)
        init4HolePoints(330f, mHole4Contrast050Points)
        init4HolePoints(090f, mHole4Contrast100Points)
        init9HolePoints(210f, mHole9Contrast030Points)
        init9HolePoints(330f, mHole9Contrast050Points)
        init9HolePoints(090f, mHole9Contrast100Points)
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
        canvas.drawHole(0.3f, mHole4Contrast030Points, mHole9Contrast030Points)
        canvas.drawHole(0.5f, mHole4Contrast050Points, mHole9Contrast050Points)
        canvas.drawHole(1.0f, mHole4Contrast100Points, mHole9Contrast100Points)
        canvas.drawCheckModel()
    }

    private fun Canvas.drawModel() {
        mPaint.color = Color.BLACK
        mPaint.style = Paint.Style.STROKE
        drawCircle(mCircleX, mCircleY, mRadiusBody, mPaint)
    }

    private fun Canvas.drawHole(index: Int, contrast: Float, holeRadius: Float, point: PointF) {
        val radius = mRatio * holeRadius
        if (!mChecked && isTouchInside(point.x, point.y, radius + 15f)) {
            mChecked = true
            mPaint.color = Color.RED
            mPaint.style = Paint.Style.FILL_AND_STROKE
            mPaint.alpha = (255 * contrast).toInt()
            drawCircle(point.x, point.y, radius, mPaint)
            mPaint.color = Color.DKGRAY
            mPaint.style = Paint.Style.FILL_AND_STROKE
            mPaint.alpha = (255 * contrast).toInt()
            mOnClickHoleListener?.onClickHole(index, contrast, holeRadius)
        } else {
            drawCircle(point.x, point.y, radius, mPaint)
        }
    }

    private fun Canvas.drawHole(contrast: Float, hole4Points: List<PointF>, hole9Points: List<PointF>) {
        mPaint.color = Color.DKGRAY
        mPaint.style = Paint.Style.FILL_AND_STROKE
        mPaint.alpha = (255 * contrast).toInt()
        for ((i, point) in hole4Points.withIndex()) {
            drawHole(i, contrast, mHole4Radius[i], point)
        }
        for ((i, point) in hole9Points.withIndex()) {
            drawHole(i, contrast, mHole9Radius[i], point)
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