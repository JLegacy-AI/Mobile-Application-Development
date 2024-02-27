package com.example.mr_potato

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox

class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val noseCheckBox = findViewById<CheckBox>(R.id.nose)
        val earCheckBox = findViewById<CheckBox>(R.id.Ears)
        val mustacheCheckBox = findViewById<CheckBox>(R.id.mustache)
        val mouthCheckBox = findViewById<CheckBox>(R.id.mouth)
        val glassesCheckBox = findViewById<CheckBox>(R.id.glasses)
        val eyebrowsCheckBox = findViewById<CheckBox>(R.id.eyebrows)
        val shoesCheckBox = findViewById<CheckBox>(R.id.shoes)
        val eyesCheckBox = findViewById<CheckBox>(R.id.eyes)
        val hatCheckBox = findViewById<CheckBox>(R.id.hat)
        val armsCheckBox = findViewById<CheckBox>(R.id.arms)


        val checkBoxes = listOf(noseCheckBox, earCheckBox, mustacheCheckBox, mouthCheckBox,
            glassesCheckBox, eyebrowsCheckBox, shoesCheckBox, eyesCheckBox, hatCheckBox, armsCheckBox)

        checkBoxes.forEach { checkBox ->
            checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                val imageViewId = resources.getIdentifier(buttonView.id.toString() + "_", "id", packageName)
                val imageView = findViewById<View>(imageViewId)
                imageView.visibility = if (isChecked) View.VISIBLE else View.GONE
            }
        }



    }
}