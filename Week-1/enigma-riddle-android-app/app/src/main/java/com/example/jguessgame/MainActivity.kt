package com.example.jguessgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import kotlin.math.abs
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val numberToGuess: Int = Random.nextInt(100,199)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputField: TextInputEditText = findViewById(R.id.inputNumber)
        val button: Button = findViewById(R.id.guessButton)

        button.setOnClickListener{

            if(inputField.text.toString().isBlank()){
                showToast("Try Your Luck :)")
            }

            val guessNumber: Int = inputField.text.toString().toInt()
            val difference: Int = abs(guessNumber - numberToGuess)
            if(difference == 0){
                showToast("You Win!!!")
            }
            else if(difference < 10){
                showToast("Very Close")
            }else if(difference < 30){
                showToast("Close")
            }else if(difference < 50){
                showToast("Far")
            }else if(difference < 70){
                showToast("Very Far")
            }else{
                showToast("Leave the Game ;(")
            }

        }

    }

    private fun showToast(message: String){
        val toast = Toast.makeText(this,message, Toast.LENGTH_SHORT)
        toast.show()
    }

}