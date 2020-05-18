package com.xh3140.core.widgets.test

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.xh3140.core.utils.PixelUtil
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin

/**
 * 螺旋CT检测模体低对比度层视图
 */
class CTModelLCRView : View {
    // 模块半径 mm
    private val mModelRadius: Float = 100f

    // 4孔序号半径 mm
    private val mHole4Radius: FloatArray =
        floatArrayOf(4.5f, 3.5f, 2.5f, 1.5f)

    // 9孔序号半径 mm
    private val mHole9Radius: FloatArray =
        floatArrayOf(7.5f, 4.5f, 4f, 3.5f, 3f, 2.5f, 2f, 1.5f, 1f)

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

    constructor(context: Context)
            : super(context)

    constructor(context: Context, attrs: AttributeSet?)
            : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)


    private val mPaintModel: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        strokeWidth = 3f
        style = Paint.Style.STROKE
    }

    private val mPaintText: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GRAY
        textSize = PixelUtil.sp2px(context, 16).toFloat()
        strokeWidth = 1f
        style = Paint.Style.FILL_AND_STROKE
    }

    private val mPaintHole: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.DKGRAY
        strokeWidth = 3f
        style = Paint.Style.FILL_AND_STROKE
    }

    private val mPaintChecked: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
        strokeWidth = 5f
        style = Paint.Style.STROKE
    }

    private fun isTouchInside(x: Float, y: Float, radius: Float): Boolean {
        return (x - mTouchX).pow(2) + (y - mTouchY).pow(2) <= radius.pow(2)
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


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) return false
        if (event.action == MotionEvent.ACTION_DOWN) {
            mTouchX = event.x
            mTouchY = event.y
            mChecked = false
            invalidate()
        }
        return super.onTouchEvent(event)
    }


    override fun onDraw(canvas: Canvas?) {
        canvas?.drawCircle(mCircleX, mCircleY, mRadiusBody, mPaintModel)
        canvas?.drawTitle(mPaintText)
        canvas?.drawHole(090f, 1.0f, mPaintHole)
        canvas?.drawHole(210f, 0.3f, mPaintHole)
        canvas?.drawHole(330f, 0.5f, mPaintHole)
        canvas?.drawBodyInfo(mPaintText)
        canvas?.drawRequireInfo(mPaintText)
        // canvas?.drawSubLine(mPaintBody)
    }


    private fun Canvas.drawTitle(paint: Paint) {
        val subTitle = "低对比度分辨力层示意图"
        val subTitleWidth = PixelUtil.getTextBoundsWidth(paint, subTitle)
        val subTitleHeight = PixelUtil.getTextBoundsHeight(paint, subTitle)
        val subTitleX = width / 2f - subTitleWidth / 2f
        val subTitleY = mCircleY - mRadiusBody - 50f
        drawText(subTitle, subTitleX, subTitleY, paint)
        val title = "检测螺旋CT模体"
        val titleWidth = PixelUtil.getTextBoundsWidth(paint, title)
        val titleX = width / 2f - titleWidth / 2f
        val titleY = subTitleY - subTitleHeight - 15f
        drawText(title, titleX, titleY, paint)
    }

    private fun Canvas.drawHole(degree: Float, contrast: Float, paint: Paint) {
        var angle4hole = degree + 30f
        var distance4hole = 26f
        paint.alpha = (255 * contrast).toInt()
        for (i in mHole4Radius.indices) {
            val radian = angle4hole * PI / 180f
            val radius = mRatio * mHole4Radius[i]
            val x = width - mCircleX - mRadius4Hole * cos(radian).toFloat()
            val y = height - mCircleY - mRadius4Hole * sin(radian).toFloat()
            drawCircle(x, y, radius, paint)
            if (!mChecked && isTouchInside(x, y, radius + 15f)) {
                mChecked = true
                drawCircle(x, y, radius, mPaintChecked)
                drawHoleInfo(contrast, mHole4Radius[i], mPaintText)
            }
            angle4hole += distance4hole
            distance4hole -= 4f
        }
        var angle9hole = degree
        var distance9hole = 20f
        for (i in mHole9Radius.indices) {
            val radian = angle9hole * PI / 180f
            val radius = mRatio * mHole9Radius[i]
            val x = width - mCircleX - mRadius9Hole * cos(radian).toFloat()
            val y = height - mCircleY - mRadius9Hole * sin(radian).toFloat()
            drawCircle(x, y, radius, paint)
            if (!mChecked && isTouchInside(x, y, radius + 15f)) {
                mChecked = true
                drawCircle(x, y, radius, mPaintChecked)
                drawHoleInfo(contrast, mHole9Radius[i], mPaintText)
            }
            angle9hole += distance9hole
            distance9hole -= 2f
        }
    }

    private fun Canvas.drawHoleInfo(contrast: Float, radius: Float, paint: Paint) {
        val info = String.format("低对比程度：%.1f%%    直径：%.1fmm", contrast, 2 * radius)
        val infoWidth = PixelUtil.getTextBoundsWidth(paint, info)
        val infoHeight = PixelUtil.getTextBoundsHeight(paint, info)
        val infoX = width / 2f - infoWidth / 2f
        val infoY = mCircleY + mRadiusBody + infoHeight + 50f
        drawText(info, infoX, infoY, paint)
    }

    private fun Canvas.drawBodyInfo(paint: Paint) {
        if (!mChecked && isTouchInside(mCircleX, mCircleY, mRadiusBody)) {
            mChecked = true
            drawCircle(mCircleX, mCircleY, mRadiusBody, mPaintChecked)
            val info = "检测螺旋CT模体其直径应不小于20cm"
            val infoWidth = PixelUtil.getTextBoundsWidth(paint, info)
            val infoHeight = PixelUtil.getTextBoundsHeight(paint, info)
            val infoX = width / 2f - infoWidth / 2f
            val infoY = mCircleY + mRadiusBody + infoHeight + 50f
            drawText(info, infoX, infoY, paint)
        }
    }

    private fun Canvas.drawRequireInfo(paint: Paint) {
        val spacing = PixelUtil.getTextBoundsHeight(paint, "spacing")
        val require1 = "新装CT：[1.0%/2.0mm]和[0.3%/5.0mm]"
        val require1Width = PixelUtil.getTextBoundsWidth(paint, require1)
        val require1Height = PixelUtil.getTextBoundsHeight(paint, require1)
        val require1X = width / 2f - require1Width / 2f
        val require1Y = mCircleY + mRadiusBody + spacing + require1Height + 80f
        drawText(require1, require1X, require1Y, paint)
        val require2 = "运行CT：[1.0%/3.0mm]和[0.3%/6.0mm]"
        val require2Width = PixelUtil.getTextBoundsWidth(paint, require2)
        val require2Height = PixelUtil.getTextBoundsHeight(paint, require2)
        val require2X = width / 2f - require2Width / 2f
        val require2Y = require1Y + require2Height + 15f
        drawText(require2, require2X, require2Y, paint)
    }

    private fun Canvas.drawSubLine(paint: Paint) {
        drawCircle(mCircleX, mCircleY, mRadius4Hole, mPaintModel)
        drawCircle(mCircleX, mCircleY, mRadius9Hole, mPaintModel)
        val xx1 = width - mCircleX - mRadiusBody * cos(90 * PI / 180).toFloat()
        val yy1 = height - mCircleY - mRadiusBody * sin(90 * PI / 180).toFloat()
        drawLine(mCircleX, mCircleY, xx1, yy1, paint)
        val xx2 = width - mCircleX - mRadiusBody * cos(210 * PI / 180).toFloat()
        val yy2 = height - mCircleY - mRadiusBody * sin(210 * PI / 180).toFloat()
        drawLine(mCircleX, mCircleY, xx2, yy2, paint)
        val xx3 = width - mCircleX - mRadiusBody * cos(330 * PI / 180).toFloat()
        val yy3 = height - mCircleY - mRadiusBody * sin(330 * PI / 180).toFloat()
        drawLine(mCircleX, mCircleY, xx3, yy3, paint)
    }
}