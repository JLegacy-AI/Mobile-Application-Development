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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegisterScreen(this)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(context: Context) {

    val deepPurple = Color(0xFF673AB7)
    val lightPurple = Color(0xFFD1C4E9)
    val pink = Color(0xFFE91E63)

    Surface(color = deepPurple, modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            TextField(
                value = "",
                onValueChange = {},
                label = { Text("Full Name", color = Color.White) },
                colors = TextFieldDefaults.textFieldColors(
                    focusedTextColor = Color.White,
                    containerColor = Color.Transparent,
                    cursorColor = lightPurple
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = "",
                onValueChange = {},
                label = { Text("Email",color = Color.White) },
                colors = TextFieldDefaults.textFieldColors(
                    focusedTextColor = lightPurple,
                    containerColor = Color.Transparent,
                    cursorColor = lightPurple
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = "",
                onValueChange = {},
                label = { Text("Password", color = Color.White) },
                colors = TextFieldDefaults.textFieldColors(
                    focusedTextColor = lightPurple,
                    containerColor = Color.Transparent,
                    cursorColor = lightPurple
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { /* Handle action */ }),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = pink),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("REGISTER")
            }
            Spacer(modifier = Modifier.height(8.dp))
            TextButton(onClick = {
                val intent = Intent(context, LoginActivity::class.java)
                context.startActivity(intent)
            }) {
                Text("Already registered? Login Me.", color = Color.White)
            }
        }
    }
}
