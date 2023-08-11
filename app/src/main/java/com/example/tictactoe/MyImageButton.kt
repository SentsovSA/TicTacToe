package com.example.tictactoe

import android.content.Context
import android.util.AttributeSet
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat

class MyImageButton(context: Context, attributes: AttributeSet?) : androidx.appcompat.widget.AppCompatImageButton(context, attributes) {
    private var img = this
    private var theme = context.theme


    fun onClick() {
        img.setOnClickListener{
            img.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.cat, theme))
            Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show()
        }
    }

    fun setDefault() {
        img.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.select, theme))
    }
}