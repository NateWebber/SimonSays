package com.nwebber.simonsays.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.nwebber.simonsays.R

class SettingsFragment : Fragment() {
    companion object {
        fun newInstance() = SettingsFragment()
    }

    private lateinit var backButton : Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.settings_fragment, container, false)

        backButton = view.findViewById(R.id.back_button)
        backButton.setOnClickListener{
            view.findNavController().navigate(R.id.action_settingsFragment_to_welcomeFragment)
        }
        return view
    }
}