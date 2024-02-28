package com.jlegacyai.verticalscrollablecardlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jlegacyai.verticalscrollablecardlayout.ui.theme.VerticalScrollableCardLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Container()
        }
    }
}



@Composable
fun Container(){
    val moviesName: List<String> = listOf<String>( "Jack Sparrow", "Dragon Ball Z","Ratatouille", "Fast & Furious","Murree Sparkletts", "Marine Games")
    val moviesImage: List<Int> = listOf(R.drawable.jack_sparrow, R.drawable.dragon_ball,R.drawable.ratatoulie,R.drawable.fast_furious,R.drawable.marine,R.drawable.one)
    LazyVerticalGrid(columns = GridCells.Fixed(1) ,
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        content = {
            items(moviesName.size){
                index -> Card(moviesName[index], moviesImage[index])
            }
        })
}



@Composable
fun Card(name: String, imageID: Int){
    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(150.dp)
            .clip(shape = RoundedCornerShape(10.dp))
    ){
        Column(
            modifier = Modifier.fillMaxHeight(1f)
        ){
            Image(painter = painterResource(id = imageID),
                contentDescription = name,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp, 0.dp, 0.dp, 10.dp))
                    .height(150.dp)
                    .width(130.dp),
                contentScale = ContentScale.Crop)
        }
        Column(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxWidth(1f)
                .fillMaxHeight(1f)
                .padding(10.dp)
        ){
            Text(text = name,
                color = Color.Yellow,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Row (
                modifier = Modifier
                    .width(180.dp)
                    .padding(0.dp, 6.dp, 30.dp, 30.dp),
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