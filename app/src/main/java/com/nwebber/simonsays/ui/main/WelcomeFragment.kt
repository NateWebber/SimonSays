package com.nwebber.simonsays.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.nwebber.simonsays.R

class WelcomeFragment : Fragment() {

    companion object {
        fun newInstance() = WelcomeFragment()
    }

    private lateinit var startButton: Button
    private lateinit var settingsButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.welcome_fragment, container, false)

        startButton = view.findViewById(R.id.start_button)
        settingsButton = view.findViewById(R.id.settings_button)
        startButton.setOnClickListener{
            view.findNavController().navigate(R.id.action_welcomeFragment_to_configFragment)
        }
        settingsButton.setOnClickListener{
            view.findNavController().navigate(R.id.action_welcomeFragment_to_settingsFragment)
        }

        return view
    }


}