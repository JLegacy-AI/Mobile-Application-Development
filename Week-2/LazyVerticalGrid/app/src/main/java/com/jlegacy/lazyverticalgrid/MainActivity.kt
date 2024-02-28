package com.jlegacy.lazyverticalgrid

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jlegacy.lazyverticalgrid.ui.theme.LazyVerticalGridTheme

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
    LazyVerticalGrid(columns = GridCells.Fixed(2) ,
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        content = {
            items(moviesName.size){
                    index -> Card(moviesName[index], moviesImage[index])
            }
        })
}


@Composable
fun Card(name: String, imageID: Int){
    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .clip(shape = RoundedCornerShape(10.dp))
    ){
        Column(
            modifier = Modifier.fillMaxHeight(1f)
        ){
            Image(painter = painterResource(id = imageID),
                contentDescription = name,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp, 0.dp, 0.dp, 0.dp))
                    .height(200.dp)
                    .fillMaxWidth(1f),
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
                fontSize = 16.sp
            )
            Column (
                modifier = Modifier
                    .width(180.dp)
                    .padding(0.dp, 6.dp, 30.dp, 10.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = "Action",
                    color = Color.White)
            }
            Button(
                onClick = {},
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Yellow
                ),
                modifier = Modifier.fillMaxWidth(1f)
            ) {
                Text(text = "Watch Trailer",
                    color = Color.Black)
            }
        }

    }
}