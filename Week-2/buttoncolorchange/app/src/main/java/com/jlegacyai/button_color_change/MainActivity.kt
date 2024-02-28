package com.jlegacyai.button_color_change

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.jlegacyai.button_color_change.ui.theme.ButtoncolorchangeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ButtonWrapper()
        }
    }
}

val colorList = listOf<Color>(Color.Cyan, Color.LightGray, Color.Green, Color.Magenta, Color.Blue)
val colorName = listOf<String>("Cyan","Light Gray","Green","Magenta", "Blue")

@Composable
fun ButtonWrapper(){

    var color by remember {
        mutableIntStateOf(0)
    }
    val nextColor = (color + 1) % colorList.size
    val nextColorName = colorName[nextColor]


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Next Color is $nextColorName")
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = colorList[color]
            ),
            onClick = {
                color = (color + 1) % colorList.size
            }
        ){
            Text(text = "Change Color")
        }
    }
}
