package com.jlegacyai.contentproviderjokesdatabase

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Space
import android.widget.Toast
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jlegacyai.contentproviderjokesdatabase.database.DatabaseHelper
import com.jlegacyai.contentproviderjokesdatabase.database.model.Category
import com.jlegacyai.contentproviderjokesdatabase.database.model.Joke
import com.jlegacyai.contentproviderjokesdatabase.database.model.Language
import com.jlegacyai.contentproviderjokesdatabase.database.model.Type
import com.jlegacyai.contentproviderjokesdatabase.ui.theme.ContentProviderJokesDatabaseTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import net.thauvin.erik.jokeapi.joke

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

    NavHost(navController = navController, startDestination = "dashboard") {

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

    val context = LocalContext.current
    var jokes by rememberSaveable { mutableStateOf(listOf<Joke>()) }

    // Asynchronously load jokes from the database
    LaunchedEffect(key1 = true) {
        val db = DatabaseHelper.getInstance(context)
        jokes = withContext(Dispatchers.IO) {
            db.getAllJokes()
        }
    }

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

    Column(
        modifier= Modifier
            .padding(40.dp, 50.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ){
        Image(
            painter = painterResource(id = R.drawable.no_jokes),
            contentDescription = "Splash Screen",
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .border(0.dp, Color.Blue, shape = RoundedCornerShape(10.dp))
                .height(50.dp)
                .width(50.dp),
            contentScale = ContentScale.Crop
        )
        LazyColumn(
            modifier= Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ){

            items(jokes) {
                JokeCard(it)
            }
        }
    }

}

@Composable
fun JokeCard(joke: Joke) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor =  Color(android.graphics.Color.parseColor("#F4CBBA"))
        ),
        modifier = Modifier.fillMaxWidth()
    ){
        Column(
            modifier = Modifier.padding(10.dp, 20.dp)
        ) {
            if (joke.type == Type.SINGLE) {
                Row{
                    Text(text = "Joke: ", modifier = Modifier.width(60.dp), fontWeight = FontWeight.Bold)
                    Text(text = joke.joke,color = Color.White)
                }
            } else {
                Row {
                    Text(text = "Setup: ", modifier = Modifier.width(60.dp), fontWeight = FontWeight.Bold)
                    Text(text = joke.setup,color = Color.White)
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Text(text = "Delivery: ",modifier = Modifier.width(60.dp), fontWeight = FontWeight.Bold)
                    Text(text = joke.delivery,color = Color.White)
                }
            }
        }

    }
}

@Composable
fun AddJoke(navController: NavController) {

    val db = DatabaseHelper.getInstance(LocalContext.current)

    // Enums List
    var typeList = Type.entries
    var categoryList = Category.entries
    var languageList = Language.entries

    //States
    var typeIsExpanded by rememberSaveable {
        mutableStateOf(false)
    }

    var jokeType by rememberSaveable {
        mutableStateOf("Type")
    }

    var categoryIsExpanded by rememberSaveable {
        mutableStateOf(false)
    }

    var jokeCategory by rememberSaveable {
        mutableStateOf("Category")
    }

    var languageIsExpanded by rememberSaveable {
        mutableStateOf(false)
    }

    var jokeLanguage by rememberSaveable {
        mutableStateOf("Language")
    }

    var jokeDescription by rememberSaveable {
        mutableStateOf("")
    }

    var setupDescription by rememberSaveable {
        mutableStateOf("")
    }

    var deliveryDescription by rememberSaveable {
        mutableStateOf("")
    }

    // Composable UI Code
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp, 120.dp, 10.dp, 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.add_joke_monkey),
            contentDescription = "Splash Screen",
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
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
        
        Spacer(modifier = Modifier.height(40.dp))
        
        Column(
            modifier = Modifier.padding(0.dp,0.dp)
        ){
            CustomDropDown(
                isExpanded = typeIsExpanded,
                onExpandedChange = { typeIsExpanded = it} ,
                value = jokeType,
                onValueChange = {
                        jokeType = it
                },
                typeList
            )
        }

        Column(
            modifier = Modifier.padding(0.dp,7.dp, 0.dp,0.dp)
        ){
            CustomDropDown(
                isExpanded = categoryIsExpanded,
                onExpandedChange = { categoryIsExpanded = it} ,
                value = jokeCategory,
                onValueChange = {
                    jokeCategory = it
                },
                categoryList
            )
        }

        if(jokeType == "TWOPART"){

            Column {

                OutlinedTextField(
                    value = setupDescription,
                    onValueChange = {
                        setupDescription = it
                    },
                    label = {
                        Text(text = "Setup", fontSize = 14.sp)
                    },
                    textStyle = TextStyle(
                        fontSize = 14.sp
                    )
                )
            }

            Column {
                OutlinedTextField(
                    value = deliveryDescription,
                    onValueChange = {
                        deliveryDescription = it
                    },
                    label = {
                        Text(text = "Delivery", fontSize = 14.sp)
                    },
                    textStyle = TextStyle(
                        fontSize = 14.sp
                    )
                )
            }
        }else{
            Column {
                OutlinedTextField(
                    value = jokeDescription,
                    onValueChange = {
                        jokeDescription = it
                    },
                    label={
                        Text("Joke",fontSize = 14.sp)
                    },
                    textStyle = TextStyle(
                        fontSize = 14.sp
                    )
                )
            }
        }

        Column(
            modifier = Modifier.padding(0.dp,8.dp)
        ){
            CustomDropDown(
                isExpanded = languageIsExpanded,
                onExpandedChange = { languageIsExpanded = it} ,
                value = jokeLanguage,
                onValueChange = {
                    jokeLanguage = it
                },
                languageList
            )
        }
        
        Row {

            Button(
                modifier = Modifier.width(120.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(android.graphics.Color.parseColor("#F4CBBA"))
                ),
                shape = RoundedCornerShape(5.dp),
                onClick = {
                    val new_joke = Joke(
                        0,
                        Type.valueOf(jokeType),
                        Category.valueOf(jokeCategory),
                        jokeDescription,
                        setupDescription,
                        deliveryDescription,
                        Language.valueOf(jokeLanguage)
                    )
                    db.insertJoke(new_joke)

                    jokeType = "Type"
                    jokeCategory = "Category"
                    jokeDescription = ""
                    setupDescription = ""
                    deliveryDescription = ""
                    jokeLanguage = "Language"



                }) {
                Text(text = "Add Joke")
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                modifier = Modifier.width(120.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(android.graphics.Color.parseColor("#F4CBBA"))
                ),
                shape = RoundedCornerShape(5.dp),
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Text(text = "Cancel")
            }

        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T>CustomDropDown(isExpanded: Boolean, onExpandedChange: (Boolean) -> Unit, value: String, onValueChange: (String) -> Unit, dropdownItemsList: List<T>){

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = {
            onExpandedChange(it)
        }
    )
    {
        OutlinedTextField(
            value = value,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            modifier = Modifier.menuAnchor()
        )
        
        ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { onExpandedChange(false) }) {
            for(item in dropdownItemsList){
                DropdownMenuItem(
                    text = {
                           Text(text = item.toString())
                    },
                    onClick = {
                        onValueChange(item.toString())
                        onExpandedChange(false)
                    }
                )
            }
        }
    }

}