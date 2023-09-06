package com.tienuu.lib.camera.ioscapturebutton

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import java.util.Collections.max

class CaptureVideoButton @JvmOverloads constructor(
    context: Context, private val attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var sizeDrawableTakePhoto = 0f
    private var sizeDrawableRecord = 0f

    private var rectDrawableTakePhoto = Rect()
    private var rectDrawable = Rect()

    private val rectView = RectF()

    init {
        initView(attrs)
    }

    private fun initView(attrs: AttributeSet?) {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        attrs?.let {

            //sizeDrawableRecord =
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val viewWidth = MeasureSpec.getSize(widthMeasureSpec)
        val viewHeight = MeasureSpec.getSize(heightMeasureSpec)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.let { canvas ->

        }
    }

}