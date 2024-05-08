package com.jlegacyai.externalstoragenotes;

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.io.File

class MainActivity : ComponentActivity() {

    private val filename = "Note.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Container(saveNote = { note -> saveNote(note, this) }, loadNote = {loadNote(this)})
        }
    }

    private fun saveNote(note: String, context: Context) {
        val file = File(context.getExternalFilesDir(null), filename)
        file.writeText(note)
        Toast.makeText(context, "Note saved successfully", Toast.LENGTH_SHORT).show()
    }

    private fun loadNote(context: Context): String {
        val file = File(context.getExternalFilesDir(null), filename)
        return if (file.exists()) {
            file.readText().also {
                Toast.makeText(context, "Note loaded successfully", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "No note found", Toast.LENGTH_SHORT).show()
            ""
        }
    }
}

@Composable
fun Container(saveNote: (String) -> Unit, loadNote: () -> String){

    var note by rememberSaveable {
        mutableStateOf("")
    }

    var loadedNote by rememberSaveable {
        mutableStateOf("Application Startup Note")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp, 100.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(value = note, onValueChange = {
            note = it
        },
            label={
                Text(text = "Enter Note Here")
            })
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Button(onClick = { saveNote(note) }) {
                Text(text = "Save")
            }
            Spacer(modifier = Modifier.width(14.dp))
            Button(onClick = {
                loadedNote = loadNote()
            }) {
                Text(text = "Load")
            }
        }
        Text(text = "Note:",modifier = Modifier
            .padding(40.dp, 5.dp)
            .fillMaxWidth(),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold)
        Text(text = loadedNote, modifier = Modifier
            .padding(40.dp, 0.dp)
            .fillMaxSize(), textAlign = TextAlign.Left)
    }

}