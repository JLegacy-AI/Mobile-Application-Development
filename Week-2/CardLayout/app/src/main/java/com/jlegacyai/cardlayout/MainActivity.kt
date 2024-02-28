package com.jlegacyai.cardlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jlegacyai.cardlayout.ui.theme.CardLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CardContainer()
        }
    }
}

@Composable
fun CardContainer(){
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(10.dp),
    ){
        Card()
    }
}

@Composable
fun Card(){
    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(150.dp)
            .clip(shape = RoundedCornerShape(10.dp))
    ){
        Column(
            modifier = Modifier.fillMaxHeight(1f)
        ){
            Image(painter = painterResource(id = R.drawable.card_img),
                contentDescription = "KILL BILL: VOL. 1",
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp, 0.dp, 0.dp, 10.dp))
                    .height(150.dp))
        }
        Column(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxWidth(1f)
                .fillMaxHeight(1f)
                .padding(10.dp)
        ){
            Text(text = "KILL BILL: VOL. 1",
                color = Color.Yellow,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Row (
                modifier = Modifier.width(180.dp).padding(0.dp,6.dp, 30.dp, 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = "2003",
                    color = Color.White)
                Text(text = "111 min",
                    color = Color.White)
                Text(text = "Action",
                    color = Color.White)
            }
            Button(
                onClick = {},
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Yellow
                )
            ) {
                Text(text = "Watch Trailer",
                    color = Color.Black)
            }
        }

    }
}