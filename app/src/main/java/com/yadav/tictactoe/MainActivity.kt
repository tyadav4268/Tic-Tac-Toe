package com.yadav.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.yadav.tictactoe.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.b2.setOnClickListener { onClickButton(it) }
        binding.b1.setOnClickListener { onClickButton(it) }
        binding.b3.setOnClickListener { onClickButton(it) }
        binding.b4.setOnClickListener { onClickButton(it) }
        binding.b5.setOnClickListener { onClickButton(it) }
        binding.b6.setOnClickListener { onClickButton(it) }
        binding.b7.setOnClickListener { onClickButton(it) }
        binding.b8.setOnClickListener { onClickButton(it) }
        binding.b9.setOnClickListener { onClickButton(it) }



    }

    private fun onClickButton(view: View) {

        var cellId = 0
        when(view.id) {
            R.id.b1 -> cellId = 1
            R.id.b2 -> cellId = 2
            R.id.b3 -> cellId = 3
            R.id.b4 -> cellId = 4
            R.id.b5 -> cellId = 5
            R.id.b6 -> cellId = 6
            R.id.b7 -> cellId = 7
            R.id.b8 -> cellId = 8
            R.id.b9 -> cellId = 9
        }
//        Toast.makeText(this,"CellId: $cellId", Toast.LENGTH_SHORT).show()
        val autoPlay:Int = if(binding.autoplaySwitch.isChecked) 1 else 0
        playGame(cellId, view as Button, autoPlay)
        if(player1.size + player2.size == 9){
            Toast.makeText(this, "Game Over! No one Wins", Toast.LENGTH_SHORT).show()
            restartGame()
        }


    }
    private var activePlayer = 1

    private var player1 = ArrayList<Int>()
    private var player2 = ArrayList<Int>()

    private var player1WinCount = 0
    private var player2WinCount = 0


    private fun playGame(cellId: Int, button: Button, autoPlay: Int) {

        if(activePlayer == 1) {
            button.text = "X"
            button.setBackgroundResource(R.color.blue)
            player1.add(cellId)
            button.isEnabled = false
            activePlayer = 2
            if(autoPlay == 1 && (checkWinner() == -1))
                autoplay()

        }else {
            button.text = "O"
            button.setBackgroundResource(R.color.darkGreen)
            player2.add(cellId)
            button.isEnabled = false
            activePlayer = 1
        }
        checkWinner()

    }

    private fun autoplay() {
        val emptyCells = ArrayList<Int>()
        for(cellId in 1..9){
            if(!(player1.contains(cellId) || player2.contains(cellId))){
                emptyCells.add(cellId)
            }
        }
        if(emptyCells.isNotEmpty()) {
            val r = Random()
            val randIndex = r.nextInt(emptyCells.size)
            val cellId = emptyCells[randIndex]
            val buttonSelected: Button = when(cellId) {
                1 -> binding.b1
                2 -> binding.b2
                3 -> binding.b3
                4 -> binding.b4
                5 -> binding.b5
                6 -> binding.b6
                7 -> binding.b7
                8 -> binding.b8
                9 -> binding.b9
                else -> binding.b1
            }
            playGame(cellId, buttonSelected, 1)
        }
        else{
            checkWinner()
            Toast.makeText(this, "Game Over!! No one wins", Toast.LENGTH_SHORT).show()
            restartGame()
        }

    }

    private fun checkWinner():Int {
        var winner = -1

        // row 1
        if(player1.contains(1) && player1.contains(2) && player1.contains(3)){
            winner = 1
        }
        if(player2.contains(1) && player2.contains(2) && player2.contains(3)){
            winner = 2
        }
        // row 2
        if(player1.contains(4) && player1.contains(5) && player1.contains(6)){
            winner = 1
        }
        if(player2.contains(4) && player2.contains(5) && player2.contains(6)){
            winner = 2
        }
        // row 3
        if(player1.contains(7) && player1.contains(8) && player1.contains(9)){
            winner = 1
        }
        if(player2.contains(7) && player2.contains(8) && player2.contains(9)){
            winner = 2
        }
        // col 1
        if(player1.contains(1) && player1.contains(4) && player1.contains(7)){
            winner = 1
        }
        if(player2.contains(1) && player2.contains(4) && player2.contains(7)){
            winner = 2
        }
        // col 3
        if(player1.contains(2) && player1.contains(5) && player1.contains(8)){
            winner = 1
        }
        if(player2.contains(2) && player2.contains(5) && player2.contains(8)){
            winner = 2
        }
        // col 3
        if(player1.contains(3) && player1.contains(6) && player1.contains(9)){
            winner = 1
        }
        if(player2.contains(3) && player2.contains(6) && player2.contains(9)){
            winner = 2
        }
        // diagonal 1
        if(player1.contains(1) && player1.contains(5) && player1.contains(9)){
            winner = 1
        }
        if(player2.contains(1) && player2.contains(5) && player2.contains(9)){
            winner = 2
        }
        // diagonal 2
        if(player1.contains(3) && player1.contains(5) && player1.contains(7)){
            winner = 1
        }
        if(player2.contains(3) && player2.contains(5) && player2.contains(7)){
            winner = 2
        }
        if(winner == 1){
            Toast.makeText(this, "Player 1 Wins!", Toast.LENGTH_SHORT).show()
            player1WinCount += 1
            restartGame()
        }
        else if(winner == 2){
            Toast.makeText(this, "Player 2 Wins!", Toast.LENGTH_SHORT).show()
            player2WinCount += 1
            restartGame()
        }
        return winner
    }

    private fun restartGame() {
//        Toast.makeText(this, "Player1: $player1WinCount, Player2: $player2WinCount", Toast.LENGTH_SHORT).show()
        binding.scoreBoard.text = getString(R.string.score, player1WinCount, player2WinCount)
        activePlayer = 1
        player1.clear()
        player2.clear()
        for(cellId in 1..9){
            val buttonSelected: Button = when(cellId) {
                1 -> binding.b1
                2 -> binding.b2
                3 -> binding.b3
                4 -> binding.b4
                5 -> binding.b5
                6 -> binding.b6
                7 -> binding.b7
                8 -> binding.b8
                9 -> binding.b9
                else -> binding.b1
            }
            buttonSelected.text = ""
            buttonSelected.setBackgroundResource(R.color.white)
            buttonSelected.isEnabled = true
        }
    }

}