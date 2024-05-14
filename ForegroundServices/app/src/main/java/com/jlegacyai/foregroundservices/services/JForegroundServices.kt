package com.jlegacyai.foregroundservices.services

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.IBinder

class JForegroundServices: Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null;
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        when(intent?.action){
            ForegroundServiceActions.START.toString() -> start()
            ForegroundServiceActions.STOP.toString() -> stopSelf()
        }


        return super.onStartCommand(intent, flags, startId)
    }

    fun start(){
        val notification = Notification.Builder(this, "j_services")
            .setSmallIcon(androidx.core.R.drawable.ic_call_decline)
            .setContentTitle("Running Foreground Services")
            .setContentText("This is JLegacy-AI Services")
            .build()

        startForeground(1, notification)
    }


}