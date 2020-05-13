package com.xh3140.core.widget.chart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.*
import com.xh3140.core.widget.utils.PixelUtil

/**
 * Copyright (C), 2020-2020
 * FileName: AbsCoordsView.kt
 * ClassName: AbsCoordsView(坐标系视图抽象类)
 * Description: 集成了坐标系的基本属性，提供视图的缩放和滑动手势回调，
 *              使用SurfaceView高效率线程绘图,提供绘图回调
 * CreateDate: 2020-02-04
 * @author xh3140
 * @version 1.00
 */
abstract class AbsCoordsView : SurfaceView, SurfaceHolder.Callback, Runnable {
    // 背景颜色（手动）
    protected var mBackgroundColor: Int = Color.WHITE

    // 坐标轴标签（手动）
    protected var mAxisLabelX: String = "x"
    protected var mAxisLabelY: String = "y"

    // 最小网格大小（手动）
    protected var mBasicGridX: Float = 100f
    protected var mBasicGridY: Float = 100f
    protected var mBasicGridKeepX: Float = 100f
    protected var mBasicGridKeepY: Float = 100f

    // 坐标系周围空白大小（手动）
    protected var mPaddingLeft: Float = 50f
    protected var mPaddingTop: Float = 50f
    protected var mPaddingRight: Float = 50f
    protected var mPaddingBottom: Float = 50f

    // 刻度列表（手动）
    protected var mScaleListX: ScaleList = ScaleList.Standard
    protected var mScaleListY: ScaleList = ScaleList.Standard

    // 线程绘图
    private var mCanvas: Canvas? = null
    private var mIsDrawing: Boolean = false

    // 缩放事件
    private var mIsScaling: Boolean = false
    private val mScaleGestureDetector: ScaleGestureDetector =
        ScaleGestureDetector(context,
            object : ScaleGestureDetector.OnScaleGestureListener {
                override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {
                    mIsScaling = true
                    return true
                }

                override fun onScaleEnd(detector: ScaleGestureDetector?) {
                    mIsScaling = false
                }

                override fun onScale(detector: ScaleGestureDetector?): Boolean {
                    if (detector == null) return false
                    return onScaleGesture(detector.focusX, detector.focusY, detector.scaleFactor)
                }
            })

    // 滑动事件
    private val mScrollGestureDetector: GestureDetector =
        GestureDetector(context,
            object : GestureDetector.SimpleOnGestureListener() {
                override fun onScroll(
                    e1: MotionEvent?,
                    e2: MotionEvent?,
                    distanceX: Float,
                    distanceY: Float
                ): Boolean {
                    if (e1 == null || e2 == null || mIsScaling) return false
                    return onScrollGesture(e1.x, e1.y, distanceX, distanceY)
                }
            })

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
     * 初始化
     */
    init {
        isFocusable = true
        keepScreenOn = true
        isFocusableInTouchMode = true
        apply { holder?.addCallback(this) }
    }

    /**
     * 重载像素方法
     */
    protected fun dp2px(dp: Int): Float =
        PixelUtil.dp2px(context, dp).toFloat()

    protected fun sp2px(sp: Int): Float =
        PixelUtil.sp2px(context, sp).toFloat()

    protected fun getTextWidth(paint: Paint, text: String): Float =
        PixelUtil.getTextBoundsWidth(paint, text).toFloat()

    protected fun getTextHeight(paint: Paint, text: String): Float =
        PixelUtil.getTextBoundsHeight(paint, text).toFloat()

    /**
     * 设置绘图背景颜色
     */
    fun setDrawBackgroundColor(color: Int) {
        mBackgroundColor = color
    }

    /**
     * 将坐标系中的坐标转换为Canvas的实际坐标
     */
    abstract fun getCanvasX(x: Float): Float

    abstract fun getCanvasY(y: Float): Float

    protected fun getCanvasPointF(point: PointF): PointF =
        PointF(getCanvasX(point.x), getCanvasY(point.y))

    /**
     * 设置坐标轴标签文本
     */
    fun setLabelText(labelX: String, labelY: String) {
        mAxisLabelX = labelX
        mAxisLabelY = labelY
    }

    /**
     * 设置网格大小
     */
    fun setGridSize(gridX: Int, gridY: Int) {
        mBasicGridX = dp2px(gridX)
        mBasicGridY = dp2px(gridY)
        mBasicGridKeepX = mBasicGridX
        mBasicGridKeepY = mBasicGridY
    }

    /**
     * 重置网格大小
     */
    fun resetGridSize() {
        mBasicGridX = mBasicGridKeepX
        mBasicGridY = mBasicGridKeepY
    }

    /**
     * 设置坐标系Padding
     */
    fun setChartPadding(left: Int, top: Int, right: Int, bottom: Int) {
        mPaddingLeft = dp2px(left)
        mPaddingTop = dp2px(top)
        mPaddingRight = dp2px(right)
        mPaddingBottom = dp2px(bottom)
    }

    fun setChartPadding(padding: Int) {
        setChartPadding(padding, padding, padding, padding)
    }

    /**
     * 设置X轴刻度列表
     */
    fun setScaleListX(scaleList: ScaleList) {
        mScaleListX = scaleList
    }

    /**
     * 设置Y轴刻度列表
     */
    fun setScaleListY(scaleList: ScaleList) {
        mScaleListY = scaleList
    }

    /**
     * 缩放手势处理,控制坐标系的缩放
     * @param focusX 焦点的X坐标
     * @param focusY 焦点的Y坐标
     * @param factory 缩放因子，小于1时代表缩小，大于1时代表放大
     * @return Boolean 是否进行了事件处理
     */
    abstract fun onScaleGesture(focusX: Float, focusY: Float, factory: Float): Boolean

    /**
     * 滑动手势处理,控制坐标系的上下左右滑动
     * @param startX 起点的X坐标
     * @param startY 起点的Y坐标
     * @param distanceX 滑动距离的X轴投影，等于起点的X坐标 - 当前触摸点的X坐标
     * @param distanceY 滑动距离的Y轴投影，等于起点的Y坐标 - 当前触摸点的Y坐标
     * @return Boolean 是否进行了事件处理
     */
    abstract fun onScrollGesture(
        startX: Float,
        startY: Float,
        distanceX: Float,
        distanceY: Float
    ): Boolean

    /**
     * 触摸事件，包含缩放事件和滑动事件
     * 应优先处理缩放手势，然后再处理滑动手势
     * 缩放手势为双触点手势，滑动手势为单触点手势，两者冲突
     * 在处理缩放手势时屏蔽滑动手势来防止冲突
     */
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) return false
        mScaleGestureDetector.onTouchEvent(event)
        mScrollGestureDetector.onTouchEvent(event)
        return true
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        mIsDrawing = true
        Thread(this).start()
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
        // do nothing
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        mIsDrawing = false
    }

    /**
     * 线程绘图，绘制图形（代替onDraw方法）
     */
    abstract fun onThreadDraw(canvas: Canvas)

    /**
     * 绘图线程
     */
    override fun run() {
        while (mIsDrawing) {
            try {
                mCanvas = holder?.lockCanvas()
                // 开始绘制，获取的Canvas保存了之前绘制的痕迹
                mCanvas?.apply {
                    // 绘制背景，清理前面的痕迹
                    drawColor(mBackgroundColor)
                    onThreadDraw(this)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                // 绘制结束，提交绘制的Canvas
                mCanvas?.apply {
                    holder?.unlockCanvasAndPost(this)
                }
            }
        }
    }
}