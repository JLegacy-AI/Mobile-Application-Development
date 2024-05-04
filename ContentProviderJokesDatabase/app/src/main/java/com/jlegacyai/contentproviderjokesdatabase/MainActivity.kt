package com.jlegacyai.contentproviderjokesdatabase

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jlegacyai.contentproviderjokesdatabase.database.DatabaseHelper
import com.jlegacyai.contentproviderjokesdatabase.database.model.Joke
import com.jlegacyai.contentproviderjokesdatabase.database.model.Type
import com.jlegacyai.contentproviderjokesdatabase.ui.theme.ContentProviderJokesDatabaseTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainContainerScreen(this)
        }
    }
}

@Composable
fun MainContainerScreen(context: Context) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {

        composable("home") {
            Home(navController = navController)
        }

        composable("dashboard") {
            Dashboard(navController = navController)
        }

        composable("add") {
            AddJoke(navController = navController)
        }

    }

}

@Composable
fun Home(navController: NavController) {

    LaunchedEffect(true) {

        delay(3000)
        navController.navigate("dashboard")

    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.splash_screen_monkey),
            contentDescription = "Splash Screen",
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .border(0.dp, Color.Blue, shape = RoundedCornerShape(10.dp))
                .height(150.dp)
                .width(150.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Monkey Joke",
            fontSize = 25.sp,
            fontFamily = FontFamily.SansSerif,
            color = Color(android.graphics.Color.parseColor("#CF6582"))
        )

    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Dashboard(navController: NavController) {
    val dbHelper = DatabaseHelper(LocalContext.current)
    val jokes = dbHelper.getAllJokes()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            IconButton(
                modifier = Modifier.background(Color(244, 203, 186, 100), shape = CircleShape),
                onClick = {
                    navController.navigate("add")
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.add_icon),
                    contentDescription = "Add Joke"
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        if (jokes.isEmpty()) {
            NoJokeComponent()
        } else {
            DisplayAllJokes(jokes)
        }
    }
}

@Composable
fun NoJokeComponent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.no_jokes),
            contentDescription = "Splash Screen",
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .border(0.dp, Color.Blue, shape = RoundedCornerShape(10.dp))
                .height(150.dp)
                .width(150.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "No Jokes",
            fontSize = 25.sp,
            fontFamily = FontFamily.SansSerif,
            color = Color(android.graphics.Color.parseColor("#F4CBBA"))
        )

    }
}

@Composable
fun DisplayAllJokes(jokes: List<Joke>) {
    LazyColumn {
        items(jokes) {
            JokeCard(it)
        }
    }
}

@Composable
fun JokeCard(joke: Joke) {
    ElevatedCard {
        if (joke.type == Type.SINGLE) {
            Text(text = joke.joke)
        } else {
            Row {
                Text(text = "Setup: ")
                Text(text = joke.setup)
            }
            Row {
                Text(text = "Delivery: ")
                Text(text = joke.delivery)
            }
        }
    }
}

@Composable
fun AddJoke(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(10.dp, 50.dp, 10.dp, 10.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.add_joke_monkey),
            contentDescription = "Splash Screen",
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .border(0.dp, Color.Blue, shape = RoundedCornerShape(10.dp))
                .height(50.dp)
                .width(50.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Add Joke",
            fontSize = 25.sp,
            fontFamily = FontFamily.SansSerif,
            color = Color(android.graphics.Color.parseColor("#F4CBBA"))
        )

        Column {
            Text(text = "Type")

        }

        Column {
            Text(text = "Category")

        }

        Column {
            Text(text = "Joke")
            OutlinedTextField(value = "", onValueChange = {})
        }

        Column {
            Text(text = "Setup")
            OutlinedTextField(value = "", onValueChange = {})
        }

        Column {
            Text(text = "Delivery")
            OutlinedTextField(value = "", onValueChange = {})
        }

        Column {
            Text(text = "Language")

        }
        
        Row {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Add Joke")
            }

            Button(onClick = {
                navController.popBackStack()
            }) {
                Text(text = "Cancel")
            }
        }

    }
}