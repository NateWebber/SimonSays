package com.nwebber.simonsays.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.nwebber.simonsays.R

class ConfigFragment : Fragment() {
    companion object {
        fun newInstance() = ConfigFragment()
    }
    private val sharedViewModel: MainViewModel by activityViewModels()

    private lateinit var playButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.config_fragment, container, false)

        playButton = view.findViewById(R.id.play_button)

        playButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_configFragment_to_gameFragment)
        }

        return view
    }
}