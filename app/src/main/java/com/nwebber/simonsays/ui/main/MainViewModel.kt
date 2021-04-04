package com.nwebber.simonsays.ui.main

import androidx.lifecycle.ViewModel
import com.nwebber.simonsays.ui.main.model.GameModel

class MainViewModel : ViewModel() {
    var current_theme = 0
    var current_difficulty = 1
    //var current_turn = 0
    var current_score = 0
    var currentIndex = 0
    var gameModel = GameModel()

    fun startGame(){
        gameModel.newGame()
        currentIndex = 0
        current_score = 0
        simonTurn()
    }
    fun checkInput(input: Int) : Boolean{
        if(gameModel.checkInput(input, currentIndex)){
            currentIndex++
            return true
        }
        return false
    }
    fun simonTurn(){
        gameModel.addToPattern()
        currentIndex=0
    }
    fun turnCompleted(): Boolean{
        return (currentIndex == gameModel.currentPattern.size)
    }
    fun getPatternLength(): Int{
        return gameModel.currentPattern.size
    }
    fun getPatternAtIndex(index: Int): Int{
        return gameModel.currentPattern[index]
    }
}

