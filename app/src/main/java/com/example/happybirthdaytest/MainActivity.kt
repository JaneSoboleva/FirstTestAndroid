package com.example.happybirthdaytest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.content.pm.ActivityInfo
import android.view.View

class Dice(private val numSides: Int) {

    fun roll(): Int {
        return (1..numSides).random()
    }
}

//test
//more test

const val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE"

class MainActivity : AppCompatActivity() {
    private fun sendMessage() {
        val editText = findViewById<TextView>(R.id.textView3)
        val message = editText.text.toString()
        val intent = Intent(this, CharactersActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.button)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE //this part forces landscape orientation

        //this next part forces fullscreen, but also is apparently deprecated
        val mDecorView = getWindow().decorView
        mDecorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // прячем панель навигации
                    or View.SYSTEM_UI_FLAG_FULLSCREEN // прячем строку состояния
                    or View.SYSTEM_UI_FLAG_IMMERSIVE
        )

        rollButton.setOnClickListener {
            val toast = Toast.makeText(this, "Dice Rolled!", Toast.LENGTH_SHORT)
            toast.show()
            val resultTextView: TextView = findViewById(R.id.textView3)
            val newDice = Dice(6)
            val diceRoll = newDice.roll()
            resultTextView.text = diceRoll.toString()
            val diceImage: ImageView = findViewById(R.id.imageView2)
            when (diceRoll) {
                1 -> diceImage.setImageResource(R.drawable.dice_1)
                2 -> diceImage.setImageResource(R.drawable.dice_2)
                3 -> diceImage.setImageResource(R.drawable.dice_3)
                4 -> diceImage.setImageResource(R.drawable.dice_4)
                5 -> diceImage.setImageResource(R.drawable.dice_5)
                6 -> diceImage.setImageResource(R.drawable.dice_6)
            }
            sendMessage()
            //some test comment
            /*
            One
            two
            three
            */
        }
    }
}