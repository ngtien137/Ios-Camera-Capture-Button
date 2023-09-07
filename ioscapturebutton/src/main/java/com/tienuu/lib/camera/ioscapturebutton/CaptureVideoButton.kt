package com.tienuu.lib.camera.ioscapturebutton

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import java.util.Collections.max
import kotlin.math.min

class CaptureVideoButton @JvmOverloads constructor(
    private val context: Context, private val attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var drawableTakePhoto: Drawable? = null
    private var sizeDrawableTakePhoto = 0f
    private var rectDrawableTakePhoto = Rect()

    private var backgroundRecord: Drawable? = null
    private var drawableRecord: Drawable? = null
    private var sizeDrawableRecord = 0f
    private var rectDrawableRecord = Rect()

    private val rectView = RectF()

    private val paintDrawable = Paint(Paint.ANTI_ALIAS_FLAG)

    private var viewWidth = 0
    private var viewHeight = 0

    var mode: Mode = Mode.TakePhoto
        set(value) {
            field = value
            invalidate()
        }

    init {
        initView(attrs)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        viewWidth = MeasureSpec.getSize(widthMeasureSpec)
        viewHeight = MeasureSpec.getSize(heightMeasureSpec)
        rectView.setCenter(viewWidth / 2f, viewHeight / 2f, viewWidth, viewHeight)
        if (sizeDrawableRecord == 0f)
            sizeDrawableRecord = min(viewWidth, viewHeight).toFloat()
        if (sizeDrawableTakePhoto == 0f)
            sizeDrawableTakePhoto = min(viewWidth, viewHeight).toFloat()
        rectDrawableRecord.setCenter(
            rectView.centerX(),
            rectView.centerY(),
            sizeDrawableRecord,
            sizeDrawableRecord
        )
        rectDrawableTakePhoto.setCenter(
            rectView.centerX(),
            rectView.centerY(),
            sizeDrawableTakePhoto,
            sizeDrawableTakePhoto
        )
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.let { canvas ->
            when (mode) {
                Mode.TakePhoto -> {
                    drawableTakePhoto.drawToCanvas(canvas, rectDrawableTakePhoto)
                }

                Mode.Record -> {
                    backgroundRecord.drawToCanvas(canvas, rectView.toRect())
                    drawableRecord.drawToCanvas(canvas, rectDrawableRecord)
                }
            }
        }
    }

    private fun Drawable?.drawToCanvas(canvas: Canvas, rect: Rect) {
        this?.let { drawable ->
            drawable.bounds = rect
            drawable.draw(canvas)
        }
    }

    enum class Mode(var value: Int) {
        TakePhoto(0), Record(1)
    }

    private fun initView(attrs: AttributeSet?) {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        attrs?.let {
            val ta = context.obtainStyledAttributes(attrs, R.styleable.CaptureVideoButton)

            backgroundRecord = ta.getDrawable(R.styleable.CaptureVideoButton_cvb_background_record)
            drawableRecord = ta.getDrawable(R.styleable.CaptureVideoButton_cvb_drawable_record)
            sizeDrawableRecord =
                ta.getDimension(R.styleable.CaptureVideoButton_cvb_drawable_size_record, 0f)

            drawableTakePhoto =
                ta.getDrawable(R.styleable.CaptureVideoButton_cvb_drawable_take_photo)
            sizeDrawableTakePhoto =
                ta.getDimension(R.styleable.CaptureVideoButton_cvb_drawable_size_take_photo, 0f)

            val modeInt = ta.getInt(R.styleable.CaptureVideoButton_cvb_mode, Mode.TakePhoto.value)

            mode = when (modeInt) {
                Mode.TakePhoto.value -> {
                    Mode.TakePhoto
                }

                Mode.Record.value -> {
                    Mode.Record
                }

                else -> {
                    Mode.TakePhoto
                }
            }

            ta.recycle()
        }
    }

}