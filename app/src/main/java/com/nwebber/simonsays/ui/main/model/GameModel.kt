package com.nwebber.simonsays.ui.main.model

import android.util.Log
import kotlin.random.Random

class GameModel {
    var score = 0
    var currentPattern : ArrayList<Int> = arrayListOf()
    fun newGame(){
        currentPattern = arrayListOf()
        score = 0
    }
    fun addToPattern(){
        var new = Random.nextInt(0, 4)
        currentPattern.add(new)
        Log.d("SIMON", "added %d to the pattern".format(new))
    }
    fun checkInput(input : Int, currentIndex: Int): Boolean {
        return (currentPattern[currentIndex] == input)
    }
}