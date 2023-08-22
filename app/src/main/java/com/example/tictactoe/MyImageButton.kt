package com.example.tictactoe

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat


class MyImageButton(context: Context, attributes: AttributeSet?) :
    androidx.appcompat.widget.AppCompatImageButton(context, attributes) {
    private var img = this
    private var theme = context.theme


    fun onPlayerClick(currentPlayer: Int) {
        if (currentPlayer == 1) {
            img.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.cat, theme))
        } else {
            img.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.dog, theme))
        }

        img.onSaveInstanceState()
    }

    fun setDefault() {
        img.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.select, theme))
        img.onSaveInstanceState()
    }

    public override fun onSaveInstanceState(): Parcelable? {
       return super.onSaveInstanceState()
    }

    public override fun onRestoreInstanceState(state: Parcelable?) {
        super.onRestoreInstanceState(state)
    }



    inner class MyState(val superSavedState: Parcelable?, val loading: Boolean) : View.BaseSavedState(superSavedState), Parcelable
}