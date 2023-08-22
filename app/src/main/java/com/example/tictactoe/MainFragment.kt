package com.example.tictactoe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import androidx.fragment.app.Fragment

class MainFragment : Fragment() {
    //delete anim after gameEnd
    lateinit var gridLayout: GridLayout
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
    private var boardArray = Array(3) { IntArray(3) } //as java.io.Serializable
    private var currentPlayer = 0
    private var gameEnded = false
    private val BOARD_ARRAY_KEY = "BOARD_ARRAY_KEY"
    private val CURRENT_PLAYER_KEY = "CURRENT_PLAYER_KEY"
    private val GAME_ENDED_KEY = "GAME_ENDED_KEY"
    private var winCounterPlayerOne = 0
    private var winCounterPlayerTwo = 0
    private var drawCounter = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(BOARD_ARRAY_KEY, boardArray)
        outState.putInt(CURRENT_PLAYER_KEY, currentPlayer)
        outState.putBoolean(GAME_ENDED_KEY, gameEnded)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gridLayout = requireView().findViewById(R.id.glt)
        resetButton = requireView().findViewById(R.id.resetButton)
        leftTop = requireView().findViewById(R.id.left_top)
        centerTop = requireView().findViewById(R.id.center_top)
        rightTop = requireView().findViewById(R.id.right_top)
        centerLeft = requireView().findViewById(R.id.left_center)
        center = requireView().findViewById(R.id.center_center)
        centerRight = requireView().findViewById(R.id.right_center)
        leftBottom = requireView().findViewById(R.id.left_bottom)
        centerBottom = requireView().findViewById(R.id.center_bottom)
        rightBottom = requireView().findViewById(R.id.right_bottom)

        resetButton.setOnClickListener {
            resetGame()
        }
        initializeBoard()
        if (savedInstanceState != null) {
            boardArray = (savedInstanceState.getSerializable(BOARD_ARRAY_KEY) as Array<IntArray>?)!!
            currentPlayer = savedInstanceState.getInt(CURRENT_PLAYER_KEY)
            gameEnded = savedInstanceState.getBoolean(GAME_ENDED_KEY)
            leftTop.onRestoreInstanceState(savedInstanceState)
            centerTop.onRestoreInstanceState(savedInstanceState)
            rightTop.onRestoreInstanceState(savedInstanceState)
            centerLeft.onRestoreInstanceState(savedInstanceState)
            center.onRestoreInstanceState(savedInstanceState)
            centerRight.onRestoreInstanceState(savedInstanceState)
            leftBottom.onRestoreInstanceState(savedInstanceState)
            centerBottom.onRestoreInstanceState(savedInstanceState)
            rightBottom .onRestoreInstanceState(savedInstanceState)
        }

    }

    private fun initializeBoard() {
        currentPlayer = 1
        gameEnded = false

        for (i in 0 until 3) {
            for (j in 0 until 3) {
                boardArray[i][j] = 0
            }
        }
        for (i in 0 until gridLayout.childCount) {
            val button = gridLayout.getChildAt(i) as MyImageButton
            button.setDefault()
            button.isEnabled = true
            button.setOnClickListener {
                button.animate().apply {
                    duration = 1000
                    rotationYBy(360f)
                }.withEndAction {
                    onCellClick(button)
                }
            }
        }
    }

    private fun onCellClick(cell: MyImageButton) {
        if(!gameEnded) {
            val currentI = cell.tag.toString().split(",")[0].toInt()
            val currentJ = cell.tag.toString().split(",")[1].toInt()
            cell.onPlayerClick(currentPlayer)
            val winner = checkWin(currentPlayer, currentI, currentJ)
            cell.isEnabled = false
            lateinit var resultsDialogFragment: ResultsDialogFragment
            if (winner) {
                gameEnded = true
                if(currentPlayer == 1) {
                    winCounterPlayerOne++
                    resultsDialogFragment = ResultsDialogFragment(currentPlayer, winner, winCounterPlayerOne)
                } else {
                    winCounterPlayerTwo++
                    resultsDialogFragment = ResultsDialogFragment(currentPlayer, winner, winCounterPlayerTwo)
                }
                resultsDialogFragment.show(parentFragmentManager, "Tag")
            } else if (isFull()) {
                drawCounter++
                resultsDialogFragment = ResultsDialogFragment(currentPlayer, winner, drawCounter)
                gameEnded = true
                resultsDialogFragment.show(parentFragmentManager, "Tag")
            } else {
                currentPlayer = if (currentPlayer == 1) 2 else 1
            }
        }
    }

    private fun checkWin(player: Int, currentI: Int, currentJ: Int): Boolean {
        if (!gameEnded) {
            if (boardArray[currentI][currentJ] == 0) {
                boardArray[currentI][currentJ] = player
                for (i in 0 until 3) {
                    if (boardArray[i][0] == player && boardArray[i][1] == player && boardArray[i][2] == player ||
                        boardArray[0][i] == player && boardArray[1][i] == player && boardArray[2][i] == player
                    ) {
                        return true
                    }
                }
                if (boardArray[0][0] == player && boardArray[1][1] == player && boardArray[2][2] == player ||
                    boardArray[2][0] == player && boardArray[1][1] == player && boardArray[0][2] == player
                ) {
                    return true
                }
            }
        }
        return false;
    }

    private fun isFull(): Boolean {
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                if (boardArray[i][j] == 0) {
                    return false
                }
            }
        }
        return true
    }

    private fun resetGame() {
        initializeBoard()
    }
}