package com.jlegacyai.foregroundservices.application

import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context

class JApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        val channel = NotificationChannel("j_services",
            "Application Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )

        val notificationManager =  getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

    }
}