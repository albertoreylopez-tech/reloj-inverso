package com.ejemplo.relojinverso

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import java.util.Calendar

object ReverseClockDrawer {
    fun draw(size: Int, faceColor: Int): Bitmap {
        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        val center = size / 2f
        val radius = center - 20f

        paint.color = faceColor
        paint.style = Paint.Style.FILL
        canvas.drawCircle(center, center, radius, paint)

        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 10f
        canvas.drawCircle(center, center, radius, paint)

        paint.style = Paint.Style.FILL
        paint.textSize = size * 0.12f
        paint.textAlign = Paint.Align.CENTER
        val textOffset = (paint.descent() + paint.ascent()) / 2f

        for (i in 1..12) {
            val angleDeg = -90 - (i * 30)
            val angleRad = Math.toRadians(angleDeg.toDouble())
            val numRadius = radius * 0.75f
            
            val x = center + (numRadius * Math.cos(angleRad)).toFloat()
            val y = center + (numRadius * Math.sin(angleRad)).toFloat() - textOffset
            
            canvas.drawText(i.toString(), x, y, paint)
        }

        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR)
        val minute = calendar.get(Calendar.MINUTE)
        val second = calendar.get(Calendar.SECOND)

        val hourAngle = -90 - (hour * 30) - (minute * 0.5)
        drawHand(canvas, center, hourAngle, radius * 0.5f, 15f, Color.BLACK)

        val minAngle = -90 - (minute * 6) - (second * 0.1)
        drawHand(canvas, center, minAngle, radius * 0.7f, 10f, Color.DKGRAY)

        val secAngle = -90 - (second * 6)
        drawHand(canvas, center, secAngle, radius * 0.85f, 5f, Color.RED)

        return bitmap
    }

    private fun drawHand(canvas: Canvas, center: Float, angleDeg: Double, length: Float, width: Float, color: Int) {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = color
        paint.strokeWidth = width
        paint.strokeCap = Paint.Cap.ROUND

        val angleRad = Math.toRadians(angleDeg)
        val endX = center + (length * Math.cos(angleRad)).toFloat()
        val endY = center + (length * Math.sin(angleRad)).toFloat()

        canvas.drawLine(center, center, endX, endY, paint)
    }
}