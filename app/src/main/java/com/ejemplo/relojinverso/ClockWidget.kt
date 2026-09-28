package com.ejemplo.relojinverso

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.widget.RemoteViews

class ClockWidget : AppWidgetProvider() {
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            val views = RemoteViews(context.packageName, R.layout.widget_clock)
            
            val bitmap = ReverseClockDrawer.draw(500, Color.CYAN)
            views.setImageViewBitmap(R.id.widget_clock_image, bitmap)

            val intent = Intent(context, ClockWidget::class.java).apply {
                action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
                putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, intArrayOf(appWidgetId))
            }
            val pendingIntent = PendingIntent.getBroadcast(
                context, appWidgetId, intent, 
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            views.setOnClickPendingIntent(R.id.widget_clock_image, pendingIntent)

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}