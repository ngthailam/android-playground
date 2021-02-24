package vn.thailam.android.challenge3.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

class CircleView @JvmOverloads constructor(
    context: Context? = null,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attributeSet, defStyleAttr) {
    private val paint = Paint()

    private var realWidth = 0f
    private var realHeight = 0f

    private var radius = 0f
    private var centerX = 0f
    private var centerY = 0f

    private var _circleColor: Int = Color.RED
    var circleColor: Int
        set(value) {
            _circleColor = value
            invalidate()
        }
        get() = _circleColor

    init {
        paint.isAntiAlias = true
        paint.color = _circleColor
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        calculateCircleMeasurements(w, h)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(centerX, centerY, radius, paint)
    }

    private fun calculateCircleMeasurements(w: Int, h: Int) {
        realWidth = w.toFloat() - (paddingStart + paddingEnd)
        realHeight = h.toFloat() - (paddingTop + paddingBottom)

        radius = min(realHeight, realWidth) / 2
        centerX = realWidth / 2
        centerY = realHeight / 2
    }
}