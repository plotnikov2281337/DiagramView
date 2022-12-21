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

    private var diagramData = mutableListOf<DiagramData>()

    private var radius = 0.0f

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL_AND_STROKE
        textAlign = Paint.Align.CENTER
        textSize = 40.0f
        typeface = Typeface.create("", Typeface.BOLD)
    }

    fun init(diagramData: MutableList<DiagramData>)
    {
        this.diagramData = diagramData
        invalidate()
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        radius = (min(width, height) / 2.0 * 0.8).toFloat()
    }

    var numbers = mutableListOf<DiagramData>()
    var list1 = mutableListOf<Double>()

    var sum2 = 0.0

    private fun dialSum(length: Int, list2: MutableList<DiagramData> )
    {
        numbers.clear()
        list1.clear()

        var sum: Double = 0.0

        var result: Double = 0.0

        for(i in list2)
        {
            numbers.add(i)
        }

        for (i in numbers)
        {
            sum += i.value
        }

        for (i in numbers)
        {
            result = (i.value / sum) * 360
            list1.add(result)
        }
        sum2 = sum

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
        if(diagramData.size != 0)
        {
            dialSum(diagramData.size, diagramData)

            var max = list1.max()
            var textPosition = 100f

            for (i in list1.indices)
            {
                var l = 50f
                var r = 550f
                var b = 550f
                var t = 50f

                if(list1[i] == max)
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
                    list1[i].toFloat(),true, paint)

                lastPoint += list1[i].toFloat()

                var printText = diagramData[i].text + " - " + (diagramData[i].value / sum2 * 100).toInt().toString()+ "%"

                canvas.drawText(printText,700f, textPosition, paint)
                textPosition += 100f

            }
            paint.color = Color.WHITE
            canvas.drawCircle(300f,300f,100f, paint)
        }

    }
}