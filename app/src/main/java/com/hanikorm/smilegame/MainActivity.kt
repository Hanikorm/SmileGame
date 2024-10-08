package com.hanikorm.smilegame

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var temporaryValue = 1
    private var znacHod = 0
    private var badSmileCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val buttonPlus = findViewById<Button>(R.id.buttonPlus)
        val buttonMinus = findViewById<Button>(R.id.buttonMinus)
        buttonPlus.setOnClickListener {
            onClick(5)
        }
        buttonMinus.setOnClickListener {
            onClick(-2)
        }
    }
    private fun onClick(number: Int) {
        val imageViewSmile = findViewById<ImageView>(R.id.imageView)
        val display = findViewById<TextView>(R.id.textView)
        val displayCount = findViewById<TextView>(R.id.textViewCount)
        temporaryValue += number
        display.text = temporaryValue.toString()
        znacHod += 1
        displayCount.text = znacHod.toString()
        if (temporaryValue in 0..9) {
            imageViewSmile.setImageResource(R.drawable.notbadsmile)
            badSmileCount = 0
        } else {
            imageViewSmile.setImageResource(R.drawable.badsmail)
            badSmileCount += 1
            if (badSmileCount == 3) {
                showGameOverDialog()
            }
        }
    }

    private fun showGameOverDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Вы проиграли!")
        builder.setMessage("Хотите начать сначала?")
        builder.setPositiveButton("Да") { dialog, _ ->
            resetGame()
            dialog.dismiss()
        }
        builder.setNegativeButton("Нет") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

    private fun resetGame() {
        temporaryValue = 1
        znacHod = 0
        badSmileCount = 0
        val display = findViewById<TextView>(R.id.textView)
        val displayCount = findViewById<TextView>(R.id.textViewCount)
        val imageViewSmile = findViewById<ImageView>(R.id.imageView)
        display.text = temporaryValue.toString()
        displayCount.text = znacHod.toString()
        imageViewSmile.setImageResource(R.drawable.notbadsmile)
    }
}