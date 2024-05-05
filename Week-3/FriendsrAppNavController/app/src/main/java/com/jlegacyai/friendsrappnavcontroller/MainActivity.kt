package com.jlegacyai.friendsrappnavcontroller

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jlegacyai.friendsrappnavcontroller.ui.theme.FriendsrAppNavControllerTheme
import java.util.Locale


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Container()
        }
    }
}



@Composable
fun Container(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home"){
            Home(navController)
        }
        composable("details/{id}",arguments = listOf(navArgument("id") {
            type = NavType.IntType
        })){
            Details(navController = navController, it.arguments?.getInt("id")!!)
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home(navController: NavController){
    val friendNames = LocalContext.current.resources.getStringArray(R.array.friend_names)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Friends")
                }
            )
        }
    ){
        LazyVerticalGrid(
            modifier = Modifier.padding(2.dp,100.dp,2.dp,2.dp),
            columns = GridCells.Adaptive(minSize = 128.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(friendNames.size){
                index ->
                FriendItem(friendNames[index], index, navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendItem(name: String, id:Int, navController: NavController){
    val imageId = LocalContext.current.resources.getIdentifier(name.lowercase(), "drawable", LocalContext.current.packageName)

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        onClick = {
            navController.navigate("details/$id")
        }
    ){
        Image(painter = painterResource(id = imageId), contentDescription = "$name Profile Image", modifier = Modifier.fillMaxSize() )
        Text(text = name, modifier = Modifier.padding(16.dp))
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Details(navController: NavController, id: Int){
    val friendNames = LocalContext.current.resources.getStringArray(R.array.friend_names)
    val friendFullName = LocalContext.current.resources.getStringArray(R.array.friend_full_names)
    val previewImage = LocalContext.current.resources.getIdentifier("${friendNames[id].toLowerCase(
        Locale.ROOT)}preview","drawable", LocalContext.current.packageName)
    val friendDetails = LocalContext.current.resources.getStringArray(R.array.friend_details)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "${friendNames[id]} Detail")
                }
            )
        }
    ){
        Column(
            modifier = Modifier.padding(5.dp, 100.dp, 5.dp, 5.dp).verticalScroll(rememberScrollState())
        ) {
            Image(painter = painterResource(id = previewImage), contentDescription = "${friendNames[id]} Profile Preview Image", modifier = Modifier
                .fillMaxWidth()
                .height(380.dp))
            Text(text = friendFullName[id], fontSize = 20.sp)
            Text(text = friendDetails[id])
            Button(onClick = { navController.navigate("home") }) {
                Text(text = "Go Back")
            }
        }
    }
}