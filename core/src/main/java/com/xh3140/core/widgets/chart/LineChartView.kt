package com.xh3140.core.widgets.chart

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import com.xh3140.core.widgets.chart.LineChartView.BezierLineMode.*
import com.xh3140.core.widgets.chart.LineChartView.DataPointMode.*
import kotlin.math.*

/**
 * Copyright (C), 2020-2020
 * FileName: LineChartView.kt
 * ClassName: LineChartView(折线视图类)
 * Description: 基于坐标系，实现了简单的折线图功能，折线采用贝塞尔曲线
 * CreateDate: 2020-02-04
 * @author xh3140
 * @version 1.00
 */
class LineChartView : BaseCoordsView {
    // 数据点圆点半径 px
    private var mDataPointRadius: Float = 5f

    // 数据点坐标文本显示模式
    private var mDataPointMode: DataPointMode = DataPointMode.NONE

    // 贝塞尔曲线显示模式
    private var mBezierLineMode: BezierLineMode = BezierLineMode.LINE_AND_FILL

    // 数据点列表
    private var mDataPoints: List<PointF> = emptyList()

    // 贝塞尔曲线平滑度
    private var mBezierSmoothness: Float = 0.16f

    // 贝塞尔曲线控制点
    private var mBezierControlPoints: List<PointF> = emptyList()

    // 保留小数点位数（动态）
    private var mDataScaleX: Int = 0
    private var mDataScaleY: Int = 0

    // 最大值点（频繁使用，优化）
    private var mMaximumDataY: Float = Float.NaN

    // 最小值点（频繁使用，优化）
    private var mMinimumDataY: Float = Float.NaN

    // 平均值点（频繁使用，优化）
    private var mAverageDataY: Float = Float.NaN

    // 折线填充底部（频繁使用，优化）
    private var mLineFillBottomY: Float = 0f

    // 折线绘图区域（频繁使用，优化）
    private val mGridRect: RectF = RectF(0f, 0f, 0f, 0f)
    private val mDataLabelRect: RectF = RectF(0f, 0f, 0f, 0f)

    // 折线图线段路径
    private val mPathFillLine = Path()
    private val mPathStrokeLine = Path()

    // 折线图点路径
    private val mPathPoint = Path()

    // 折线填充画笔
    private val mPaintFillLine: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.CYAN
        alpha = 64
        style = Paint.Style.FILL
    }

    // 折线描边画笔
    private val mPaintStrokeLine: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.DKGRAY
        strokeWidth = 2f
        style = Paint.Style.STROKE
    }

    // 折线圆点画笔
    private val mPaintPoint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.DKGRAY
        style = Paint.Style.FILL
    }

    // 坐标点文本画笔
    private val mPaintPointText: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        strokeWidth = 2f
        textSize = sp2px(9)
    }

    // 虚线画笔
    private val mPaintDashLine: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 3f
        style = Paint.Style.STROKE
        pathEffect = DashPathEffect(floatArrayOf(8f, 10f, 8f, 10f), 0f)
    }

    /**
     * 数据点文本显示模式
     * @sample NONE 不显示任何文本
     * @sample ONLY_X 只显示X坐标文本
     * @sample ONLY_Y 只显示Y坐标文本
     * @sample X_AND_Y 显示X坐标和Y坐标文本
     */
    enum class DataPointMode { NONE, ONLY_X, ONLY_Y, X_AND_Y }

    /**
     * 贝塞尔曲线显示模式
     * @sample LINE 只显示贝塞尔曲线
     * @sample FILL 只填充贝塞尔曲线
     * @sample LINE_AND_FILL 显示和填充贝塞尔曲线
     */
    enum class BezierLineMode { LINE, FILL, LINE_AND_FILL }

    /**
     * 构造函数
     */
    constructor(context: Context)
            : super(context)

    constructor(context: Context, attrs: AttributeSet?)
            : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)

    init {
        setLabelText("Data", "Value")
        setGridSize(30, 30)
        setDataPointMode(DataPointMode.ONLY_Y)
    }

    /**
     * 获取贝塞尔曲线控制点
     */
    private fun getBezierControlPoints(points: List<PointF>, smoothness: Float): List<PointF> {
        if (points.size <= 1) return emptyList()
        val controlPoints = mutableListOf<PointF>()
        for (i in 1 until points.size) {
            val moreBeforePoint = points[maxOf(0, i - 2)]
            val beforePoint = points[maxOf(0, i - 1)]
            val currentPoint = points[i]
            val nextPoint = points[minOf(points.size - 1, i + 1)]
            val leftControlX = beforePoint.x + (currentPoint.x - moreBeforePoint.x) * smoothness
            val leftControlY = beforePoint.y + (currentPoint.y - moreBeforePoint.y) * smoothness
            val rightControlX = currentPoint.x - (nextPoint.x - beforePoint.x) * smoothness
            val rightControlY = currentPoint.y - (nextPoint.y - beforePoint.y) * smoothness
            controlPoints.add(PointF(leftControlX, leftControlY))
            controlPoints.add(PointF(rightControlX, rightControlY))
        }
        return controlPoints.toList()
    }

    /**
     * 设置数据点圆点半径 px
     */
    fun setDataPointRadius(radius: Float) {
        mDataPointRadius = radius
    }

    /**
     * 设置数据点坐标文本显示模式
     */
    fun setDataPointMode(mode: DataPointMode) {
        mDataPointMode = mode
    }

    /**
     * 设置贝塞尔曲线显示模式
     */
    fun setBezierLineMode(mode: BezierLineMode) {
        mBezierLineMode = mode
    }

    /**
     * 设置贝塞尔曲线平滑度
     * 值越大，连线越弯曲，为0时，连线为直线，通常取0.16
     */
    fun setBezierLineSmoothness(smoothness: Float) {
        mBezierSmoothness = smoothness
    }

    /**
     * 设置数据坐标点
     */
    fun setDataPoints(list: List<PointF>) {
        mDataPoints = list
        mBezierControlPoints = getBezierControlPoints(list, mBezierSmoothness)
        if (list.isEmpty()) {
            mMaximumDataY = Float.NaN
            mMinimumDataY = Float.NaN
            mAverageDataY = Float.NaN
        } else {
            mDataScaleX = 0
            mDataScaleY = 0
            mMaximumDataY = list.first().y
            mMinimumDataY = list.first().y
            mAverageDataY = 0f
            for (point in list) {
                mDataScaleX = maxOf(mDataScaleX, point.x.toBigDecimal().scale())
                mDataScaleY = maxOf(mDataScaleY, point.y.toBigDecimal().scale())
                mMaximumDataY = maxOf(mMaximumDataY, point.y)
                mMinimumDataY = minOf(mMinimumDataY, point.y)
                mAverageDataY += point.y
            }
            mAverageDataY /= list.size
        }
    }

    /**
     * 设置Y轴刻度
     * 设定的值只是建议值，是最终取值得依据
     * 分别采用2进制和[M*10^N]（包含10进制）来划分，然后取最近的值
     * @param minimum 刻度的建议最小值
     * @param maximum 刻度的建议最大值
     * @param count 划分建议数量
     * @return
     */
    fun setScaleListY(minimum: Float, maximum: Float, count: Int) {
        val scale: Int
        val interval: Float
        val interval0 = (maximum - minimum) / count
        val power1 = round(log2(interval0)).toInt()
        val interval1 = 2f.pow(power1)
        val power2 = floor(log10(interval0)).toInt()
        val times2 = round(interval0 / 10f.pow(power2)).toInt()
        val interval2 = times2 * 10f.pow(power2)
        if (abs(interval0 - interval1) < abs(interval0 - interval2)) {
            scale = maxOf(0, -power1)
            interval = interval1
        } else {
            scale = maxOf(0, -power2)
            interval = interval2
        }
        val minimum0 = floor(minimum / interval - 5) * interval
        val maximum0 = ceil(maximum / interval + 5) * interval
        val count0 = ceil((maximum0 - minimum0) / interval).toInt()
        setScaleListY(ScaleList.Linear(minimum0, interval, count0, 3, scale))
    }

    /**
     * 设置数据坐标点并自动设置Y轴刻度列表
     */
    fun setDataPointsAutoScaleListY(list: List<PointF>) {
        setDataPoints(list)
        val maxGridCount = floor((mMaximumY - mMinimumY) / mBasicGridKeepY).toInt()
        val count = maxOf(5, minOf(mDataPoints.size, maxGridCount))
        setScaleListY(mMinimumDataY, mMaximumDataY, count)
    }

    /**
     * 更新与绘图和布局有关的参数
     */
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        // 网格区域
        mGridRect.left = mMinimumX
        mGridRect.top = mMinimumY + 25f
        mGridRect.right = mMaximumX - 25f
        mGridRect.bottom = mMaximumY
        // 右侧数据标签区域
        mDataLabelRect.left = mMaximumX - 30f
        mDataLabelRect.top = mMinimumY
        mDataLabelRect.right = width - mPaddingRight
        mDataLabelRect.bottom = mMaximumY
        // 折线填充底部（频繁使用，优化）
        val index = ((mMinimumDataY - mScaleListY.start) / mScaleListY.interval).toInt()
        mLineFillBottomY = mScaleListY.getValue(maxOf(0, index - 2))
    }

    /**
     * 线程绘图，绘制图形（代替onDraw方法）
     */
    override fun onThreadDraw(canvas: Canvas) {
        super.onThreadDraw(canvas)
        drawLine(canvas)
        drawDash(canvas)
    }

    /**
     * 获取数据点文字相对数据点的漂浮方向
     */
    private fun getDrawTextDirection(index: Int): Boolean {
        val beforeY = mDataPoints[if (index == 0) index + 1 else index - 1].y
        val currentY = mDataPoints[index].y
        val nextY = mDataPoints[if (index == mDataPoints.size - 1) index - 1 else index + 1].y
        // 升型
        if (currentY in beforeY..nextY) return false
        // 降型
        if (currentY in nextY..beforeY) return true
        // 峰型
        if (beforeY <= currentY && currentY >= nextY) return true
        // 谷型
        if (beforeY >= currentY && currentY <= nextY) return false
        // 默认在上方
        return true
    }

    /**
     * 绘制折现和数据点
     */
    private fun drawLine(canvas: Canvas) {
        canvas.save()
        canvas.clipRect(mGridRect)
        // 重置路径
        mPathFillLine.reset()
        mPathStrokeLine.reset()
        mPathPoint.reset()
        mPaintPointText.color = Color.BLACK
        val lineFillBottomY = getCanvasY(mLineFillBottomY)
        for (index in mDataPoints.indices) {
            val currentPoint = getCanvasPointF(mDataPoints[index])
            // 添加贝塞尔曲线数据
            if (index == 0) {
                mPathFillLine.moveTo(currentPoint.x, lineFillBottomY)
                mPathFillLine.lineTo(currentPoint.x, currentPoint.y)
                mPathStrokeLine.moveTo(currentPoint.x, currentPoint.y)
            } else {
                val leftControlPoint = getCanvasPointF(mBezierControlPoints[index * 2 - 2])
                val rightControlPoint = getCanvasPointF(mBezierControlPoints[index * 2 - 1])
                mPathFillLine.cubicTo(
                    leftControlPoint.x, leftControlPoint.y,
                    rightControlPoint.x, rightControlPoint.y,
                    currentPoint.x, currentPoint.y
                )
                mPathStrokeLine.cubicTo(
                    leftControlPoint.x, leftControlPoint.y,
                    rightControlPoint.x, rightControlPoint.y,
                    currentPoint.x, currentPoint.y
                )
                // 最后闭合曲线
                if (index == mDataPoints.size - 1) {
                    mPathFillLine.lineTo(currentPoint.x, lineFillBottomY)
                    mPathFillLine.close()
                }
            }
            // 添加数据点圆点数据
            mPathPoint.addCircle(
                currentPoint.x,
                currentPoint.y,
                mDataPointRadius,
                Path.Direction.CCW
            )
            // 绘制数据点文本
            if (mDataPointMode != NONE) {
                val pointText = when (mDataPointMode) {
                    ONLY_X -> String.format("%.${mDataScaleX}f", mDataPoints[index].x)
                    ONLY_Y -> String.format("%.${mDataScaleY}f", mDataPoints[index].y)
                    X_AND_Y -> String.format(
                        "(%.${mDataScaleX}f,%.${mDataScaleY}f)",
                        mDataPoints[index].x, mDataPoints[index].y
                    )
                    else -> ""
                }
                if (pointText.isNotEmpty()) {
                    val drawTextX = currentPoint.x - getTextWidth(mPaintPointText, pointText) / 2
                    val drawTextY = if (getDrawTextDirection(index)) {
                        currentPoint.y - 15f
                    } else {
                        currentPoint.y + getTextHeight(mPaintPointText, pointText) / 2 + 15f
                    }
                    canvas.drawText(pointText, drawTextX, drawTextY, mPaintPointText)
                }
            }
        }
        // 绘制折线
        when (mBezierLineMode) {
            FILL -> canvas.drawPath(mPathFillLine, mPaintFillLine)
            LINE -> canvas.drawPath(mPathStrokeLine, mPaintStrokeLine)
            else -> {
                canvas.drawPath(mPathFillLine, mPaintFillLine)
                canvas.drawPath(mPathStrokeLine, mPaintStrokeLine)
            }
        }
        // 绘制圆点
        canvas.drawPath(mPathPoint, mPaintPoint)
    }

    /**
     * 绘制虚线
     */
    private fun drawDash(canvas: Canvas) {
        if (mMaximumDataY.isNaN() || mMinimumDataY.isNaN() || mAverageDataY.isNaN()) {
            return
        }
        val maximumY = getCanvasY(mMaximumDataY)
        val minimumY = getCanvasY(mMinimumDataY)
        val averageY = getCanvasY(mAverageDataY)
        // 绘制虚线
        mPaintDashLine.color = Color.RED
        canvas.drawLine(mMinimumX, maximumY, mMaximumX, maximumY, mPaintDashLine)
        mPaintDashLine.color = Color.BLUE
        canvas.drawLine(mMinimumX, minimumY, mMaximumX, minimumY, mPaintDashLine)
        mPaintDashLine.color = Color.GREEN
        canvas.drawLine(mMinimumX, averageY, mMaximumX, averageY, mPaintDashLine)
        // 绘制虚线文本
        canvas.restore()
        canvas.clipRect(mDataLabelRect)
        // 绘制最大值
        mPaintPointText.color = Color.RED
        val maximumValueText = String.format("%.${mDataScaleY}f", mMaximumDataY)
        canvas.drawDataLabel("Max", maximumValueText, maximumY)
        // 绘制最小值
        mPaintPointText.color = Color.BLUE
        val minimumValueText = String.format("%.${mDataScaleY}f", mMinimumDataY)
        canvas.drawDataLabel("Min", minimumValueText, minimumY)
        // 绘制平均值
        mPaintPointText.color = Color.GREEN
        val averageValueText = String.format("%.${mDataScaleY + 1}f", mAverageDataY)
        canvas.drawDataLabel("Avg", averageValueText, averageY)
    }

    /**
     *  重置方法，绘制数据标签（最大值、最小值、平均值）
     */
    private fun Canvas.drawDataLabel(text: String, valueText: String, currentY: Float) {
        val valueTextY = currentY + getTextHeight(mPaintPointText, text) + 5f
        drawText(text, mMaximumX - 15f, currentY - 5f, mPaintPointText)
        drawText(valueText, mMaximumX - 15f, valueTextY, mPaintPointText)
    }
}