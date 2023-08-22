package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

open class MainActivity() : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mainFragment = MainFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, mainFragment).commit()
    }

}