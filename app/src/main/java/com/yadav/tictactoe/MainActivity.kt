package com.yadav.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.yadav.tictactoe.databinding.ActivityMainBinding
import java.lang.Math.random
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
        playGame(cellId, view as Button)


    }
    var activePlayer = 1

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()

    private fun playGame(cellId: Int, button: Button) {
        if(activePlayer == 1) {
            button.text = "X"
            button.setBackgroundResource(R.color.blue)
            player1.add(cellId)
            activePlayer = 2
            autoplay()
        }else {
            button.text = "O"
            button.setBackgroundResource(R.color.darkGreen)
            player2.add(cellId)
            activePlayer = 1
        }

        button.isEnabled = false
        
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
            playGame(cellId, buttonSelected)
        }
        else{
            Toast.makeText(this, "Game Over!!", Toast.LENGTH_SHORT).show()
        }


    }

    private fun checkWinner() {
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
        }
        else if(winner == 2){
            Toast.makeText(this, "Player 2 Wins!", Toast.LENGTH_SHORT).show()
        }
    }


}