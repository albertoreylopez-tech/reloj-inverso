package com.ejemplo.relojinverso

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val handler = Handler(Looper.getMainLooper())
    private var faceColor = Color.WHITE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.clockImageView)
        val btnColor = findViewById<Button>(R.id.btnChangeColor)

        btnColor.setOnClickListener {
            faceColor = if (faceColor == Color.WHITE) Color.YELLOW else Color.WHITE
        }

        val runnable = object : Runnable {
            override fun run() {
                imageView.setImageBitmap(ReverseClockDrawer.draw(800, faceColor))
                handler.postDelayed(this, 1000)
            }
        }
        handler.post(runnable)
    }
}