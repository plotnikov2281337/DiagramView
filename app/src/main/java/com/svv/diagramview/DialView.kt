package com.svv.diagramview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin
import kotlin.random.Random

private const val RADIUS_OFFSET_LABEL = 30
private const val RADIUS_OFFSET_INDICATOR = -35

class DialView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var radius = 0.0f

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL_AND_STROKE
        textAlign = Paint.Align.CENTER
        textSize = 55.0f
        typeface = Typeface.create("", Typeface.BOLD)
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        radius = (min(width, height) / 2.0 * 0.8).toFloat()
    }

    var numbers = mutableListOf<Int>()
    var list1 = mutableListOf<Double>()

    fun generate()
    {
        invalidate()
    }

    private fun dialSum()
    {
        numbers.clear()
        list1.clear()

        var sum: Double = 0.0
        var result: Double = 0.0

        var lenght = (2 .. 10).random()

        for(i in 0..lenght)
        {
            var value = (0..100).random()
            numbers.add(value)
        }

        for (i in numbers)
        {
            sum += i.toDouble()
        }

        for (i in numbers)
        {
            result = (i.toDouble() / sum) * 360
            list1.add(result)
        }
    }

    private val colors = arrayOf(
        Color.parseColor("#000000"),
        Color.parseColor("#FF8F00"),
        Color.parseColor("#002366"),
        Color.parseColor("#D84315"),
        Color.parseColor("#32222F"),
        Color.parseColor("#67482F"),
        Color.parseColor("#30090F"),
        Color.parseColor("#49999F"),
        Color.parseColor("#17474F"),
    )

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var lastPoint: Float = 0.0f
        dialSum()

        var max = list1.max()
        for (i in list1)
        {
            var l = 50f
            var r = 550f
            var b = 550f
            var t = 50f

            if(i == max)
            {
                if(lastPoint > 90)
                {
                    l -= 40f
                    b -= 40f
                }
                if(lastPoint > 180)
                {
                    l -= 40f
                    t -= 40f
                }
                if(lastPoint > 270)
                {
                    r += 40f
                    t -= 40f
                }
                else
                {
                    r += 40f
                    b += 40f
                }
            }

            paint.color = colors.random()

            canvas.drawArc(l, t, r, b, lastPoint,
                i.toFloat(),true, paint)

            lastPoint += i.toFloat()
        }
    }
}