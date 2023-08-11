package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat

class MainActivity : AppCompatActivity() {

    //com.example.tictactoe.MyImageView
    private lateinit var gridLayout: GridLayout
    private lateinit var resetButton: Button
    private lateinit var leftTop : MyImageButton
    private lateinit var centerTop : MyImageButton
    private lateinit var rightTop : MyImageButton
    private lateinit var centerLeft : MyImageButton
    private lateinit var center : MyImageButton
    private lateinit var centerRight : MyImageButton
    private lateinit var leftBottom : MyImageButton
    private lateinit var centerBottom : MyImageButton
    private lateinit var rightBottom : MyImageButton
    private var currentPlayer = 1
    private var gameEnded = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resetButton = findViewById(R.id.resetButton)

        leftTop = findViewById(R.id.left_top)
        centerTop = findViewById(R.id.center_top)
        rightTop = findViewById(R.id.right_top)
        centerLeft = findViewById(R.id.left_center)
        center = findViewById(R.id.center_center)
        centerRight = findViewById(R.id.right_center)
        leftBottom = findViewById(R.id.left_bottom)
        centerBottom = findViewById(R.id.center_bottom)
        rightBottom = findViewById(R.id.right_bottom)

        resetButton.setOnClickListener {
            resetGame()
            Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show()
        }

        initializeBoard()

    }

    private fun initializeBoard() {
        currentPlayer = 1
        gameEnded = false

        leftTop.setDefault()
        centerTop.setDefault()
        rightTop.setDefault()
        centerLeft.setDefault()
        center.setDefault()
        centerRight.setDefault()
        leftBottom.setDefault()
        centerBottom.setDefault()
        rightBottom.setDefault()
    }

   fun onCellClick(view: View) {
        leftTop.setOnClickListener{
            leftTop.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.cat, theme))
            Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show()
        }
        centerTop.onClick()
        rightTop.onClick()
        centerLeft.onClick()
        center.onClick()
        centerRight.onClick()
        leftBottom.onClick()
        centerBottom.onClick()
        rightBottom.onClick()
    }

    private fun checkWin() : Boolean {
        return false;
    }

    private fun resetGame() {
        initializeBoard()
    }

}