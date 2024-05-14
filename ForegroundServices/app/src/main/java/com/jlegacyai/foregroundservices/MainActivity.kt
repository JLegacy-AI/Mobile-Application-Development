package com.jlegacyai.foregroundservices

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.jlegacyai.foregroundservices.services.ForegroundServiceActions
import com.jlegacyai.foregroundservices.services.JForegroundServices
import com.jlegacyai.foregroundservices.ui.theme.ForegroundServicesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.POST_NOTIFICATIONS, Manifest.permission.FOREGROUND_SERVICE)
            ,0)
        setContent {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Button(onClick = {
                    Intent(this@MainActivity, JForegroundServices::class.java).also {
                        it.action = ForegroundServiceActions.START.toString()
                        startService(it)
                    }
                }) {
                    Text(text = "Start Service")
                }
                Button(onClick = {
                    Intent(this@MainActivity, JForegroundServices::class.java).also {
                        it.action = ForegroundServiceActions.STOP.toString()
                        startService(it)
                    }
                }) {
                    Text(text = "Stop Service")
                }
            }
        }
    }
}
