package com.jlegacyai.loginsignupscreenshift;

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen(this)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(context: Context) {

    val darkTurquoise = Color(0xFF045D56)
    val offWhite = Color(0xFFECEFF1)
    val white = Color.White

    Surface(color = darkTurquoise, modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            TextField(
                value = "",
                onValueChange = {},
                label = { Text("Email", color =  Color.Black) },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = offWhite,
                    cursorColor = white,
                    focusedTextColor = white
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = "",
                onValueChange = {},
                label = { Text("Password", color = Color.Black) },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = offWhite,
                    cursorColor = white,
                    focusedTextColor = white
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { }),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {  },
                colors = ButtonDefaults.buttonColors(containerColor = white),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("LOGIN", color = darkTurquoise)
            }
            Spacer(modifier = Modifier.height(8.dp))
            TextButton(onClick = {
                val intent = Intent(context, RegisterActivity::class.java)
                context.startActivity(intent)
            }) {
                Text("Not a member? Sign up now.", color = white)
            }
        }
    }
}
