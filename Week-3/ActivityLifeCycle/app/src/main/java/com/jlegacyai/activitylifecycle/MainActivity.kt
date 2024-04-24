package com.jlegacyai.activitylifecycle

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Container()
        }
    }

    override fun onStart() {
        super.onStart()
        showToast("onStart called")
    }

    override fun onResume() {
        super.onResume()
        showToast("onResume called")
    }

    override fun onPause() {
        super.onPause()
        showToast("onPause called")
    }

    override fun onStop() {
        super.onStop()
        showToast("onStop called")
    }

    override fun onRestart() {
        super.onRestart()
        showToast("onRestart called")
    }

    override fun onDestroy() {
        super.onDestroy()
        showToast("onDestroy called")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        showToast("onSaveInstanceState called")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        showToast("onRestoreInstanceState called")
    }

}

@Composable
fun Container(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "JLegacy AI",
            modifier = modifier,
            fontSize = 40.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Activity Life Cycle",
            fontSize= 12.sp,
            modifier = modifier,
            letterSpacing = 5.sp
        )
    }
}
