package com.nwebber.simonsays.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nwebber.simonsays.ui.main.model.GameModel

class DifficultyVocab : ArrayList<DifficultyDefinition>()

data class DifficultyDefinition(val name: String, val id: Int, val multiplier: Double) {}

class MainViewModel : ViewModel() {
    var current_theme = 0
    var current_difficulty = 1

    //var current_turn = 0
    var current_score = 0
    var currentIndex = 0
    var gameModel = GameModel()

    //difficulty data
    val difficultyEasy = DifficultyDefinition("Easy", 0, 1.33)
    val difficultyMedium = DifficultyDefinition("Medium", 1, 1.0)
    val difficultyHard = DifficultyDefinition("Hard", 2, 0.66)

    val difficultyList = listOf(difficultyEasy, difficultyMedium, difficultyHard)
    private val _difficultyVocab = MutableLiveData<List<DifficultyDefinition>>()
    var difficultyVocab: LiveData<List<DifficultyDefinition>> = _difficultyVocab

    init{
        _difficultyVocab.value = difficultyList
        difficultyVocab = _difficultyVocab
    }

    fun startGame() {
        gameModel.newGame()
        currentIndex = 0
        current_score = 0
        simonTurn()
    }

    fun checkInput(input: Int): Boolean {
        if (gameModel.checkInput(input, currentIndex)) {
            currentIndex++
            return true
        }
        return false
    }

    fun simonTurn() {
        gameModel.addToPattern()
        currentIndex = 0
    }

    fun turnCompleted(): Boolean {
        return (currentIndex == gameModel.currentPattern.size)
    }

    fun getPatternLength(): Int {
        return gameModel.currentPattern.size
    }

    fun getPatternAtIndex(index: Int): Int {
        return gameModel.currentPattern[index]
    }



}

