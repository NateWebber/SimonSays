package com.nwebber.simonsays.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.nwebber.simonsays.R

class GameFragment : Fragment() {
    companion object {
        fun newInstance() = GameFragment()
    }
    private val sharedViewModel: MainViewModel by activityViewModels()

    private lateinit var endButton: Button
    private lateinit var redButton: ImageButton
    private lateinit var yellowButton: ImageButton
    private lateinit var greenButton: ImageButton
    private lateinit var blueButton: ImageButton
    private lateinit var score_text: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.game_fragment, container, false)

        score_text = view.findViewById(R.id.score_text)

        endButton = view.findViewById(R.id.end_button)

        endButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_gameFragment_to_resultsFragment)
        }
        redButton = view.findViewById(R.id.button_red)
        yellowButton = view.findViewById(R.id.button_yellow)
        greenButton = view.findViewById(R.id.button_green)
        blueButton = view.findViewById(R.id.button_blue)
        redButton.setOnClickListener {
            var result = sharedViewModel.checkInput(0) //red clicked
            if (!result){
                view.findNavController().navigate(R.id.action_gameFragment_to_resultsFragment)
            }
            else if (sharedViewModel.turnCompleted()) {
                sharedViewModel.current_score++
                score_text.text = "Current Score: %d".format(sharedViewModel.current_score)
                sharedViewModel.simonTurn()
                //play simon animations here
            }
        }
        yellowButton.setOnClickListener {
            var result = sharedViewModel.checkInput(1) //yellow clicked
            if (!result){
                view.findNavController().navigate(R.id.action_gameFragment_to_resultsFragment)
            }
            else if (sharedViewModel.turnCompleted()){
                sharedViewModel.simonTurn()
                sharedViewModel.current_score++
                score_text.text = "Current Score: %d".format(sharedViewModel.current_score)
                //play simon animations here
            }
        }
        greenButton.setOnClickListener {
            var result = sharedViewModel.checkInput(2) //green clicked
            if (!result){
                view.findNavController().navigate(R.id.action_gameFragment_to_resultsFragment)
            }
            else if (sharedViewModel.turnCompleted()){
                sharedViewModel.simonTurn()
                sharedViewModel.current_score++
                score_text.text = "Current Score: %d".format(sharedViewModel.current_score)
                //play simon animations here
            }
        }
        blueButton.setOnClickListener {
            var result = sharedViewModel.checkInput(3) //blue clicked
            if (!result){
                view.findNavController().navigate(R.id.action_gameFragment_to_resultsFragment)
            }
            else if (sharedViewModel.turnCompleted()){
                sharedViewModel.simonTurn()
                sharedViewModel.current_score++
                score_text.text = "Current Score: %d".format(sharedViewModel.current_score)
                //play simon animations here
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.startGame()
        score_text.text = "Current Score: %d".format(sharedViewModel.current_score)
        //play simon animations here
    }
}