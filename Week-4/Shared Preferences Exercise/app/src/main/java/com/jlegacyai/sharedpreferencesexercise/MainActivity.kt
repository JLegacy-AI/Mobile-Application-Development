package com.jlegacyai.sharedpreferencesexercise

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Container(context = this)
        }
    }
}


@Composable
fun Container(context: Context){

    // SharedPref Object
    val sharedPreferencesManager = SharedPreferencesManager(context)

    // States
    var key by rememberSaveable {
        mutableStateOf("")
    } 
    var value by rememberSaveable {
        mutableStateOf("")
    }
    
    // UI
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Store & Retrieve Data")
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ){
           TextField(
               modifier = Modifier.fillMaxWidth(0.5f)
               ,value = key, onValueChange = {
               key = it
           },
               label = {
                   Text(text = "Key")
               })
            TextField(value = value, onValueChange = {
                value = it
            },
                label = {
                    Text(text = "Value")
                })
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ){
            Button(onClick = {
                sharedPreferencesManager.store(key, value)
                Toast.makeText(context, "Data Save", Toast.LENGTH_SHORT).show()
            }) {
                Text(text = "Store")
            }
            Button(onClick = {
                Toast.makeText(context, "Data Read", Toast.LENGTH_SHORT).show()
                val storedValue = sharedPreferencesManager.retrieve(key,"")
                value = storedValue.toString()
            }) {

                Text(text = "Read")
            }
        }
    }
}
