package com.nwebber.simonsays.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.nwebber.simonsays.R

class GameFragment : Fragment() {
    companion object {
        fun newInstance() = GameFragment()
    }

    private lateinit var endButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.game_fragment, container, false)

        endButton = view.findViewById(R.id.end_button)

        endButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_gameFragment_to_resultsFragment)
        }

        return view
    }
}