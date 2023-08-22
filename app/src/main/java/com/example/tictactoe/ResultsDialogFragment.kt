package com.example.tictactoe

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ResultsDialogFragment(private var currentPlayer: Int, private var checkWin: Boolean,
                            private val counter: Int): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            if (checkWin) {
                return activity?.let {
                    val builder = AlertDialog.Builder(it)
                    builder.setTitle("Конец игры!")
                        .setMessage("Игрок $currentPlayer победил уже $counter раз")
                        .setPositiveButton("Отлично!") { dialog, id ->
                            dialog.cancel()
                        }
                    builder.create()
                } ?: throw IllegalStateException("Activity cannot be null")
            } else {
                return activity?.let {
                    val builder = AlertDialog.Builder(it)
                    builder.setTitle("Конец игры!")
                        .setMessage("Ничья!")
                        .setPositiveButton("Отлично!") { dialog, id ->
                            dialog.cancel()
                        }
                    builder.create()
                } ?: throw IllegalStateException("Activity cannot be null")
            }
        }
}