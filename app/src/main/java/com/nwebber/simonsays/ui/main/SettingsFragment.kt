package com.nwebber.simonsays.ui.main

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.nwebber.simonsays.MainActivity
import com.nwebber.simonsays.R
class SettingsFragment : Fragment() {
    companion object {
        fun newInstance() = SettingsFragment()
    }
    private val sharedViewModel: MainViewModel by activityViewModels()
    private lateinit var backButton : Button
    private lateinit var themeRadioGroup: RadioGroup
    private lateinit var themeLightButton: RadioButton
    private lateinit var themeDarkButton: RadioButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.settings_fragment, container, false)
        backButton = view.findViewById(R.id.back_button)
        backButton.setOnClickListener{
            view.findNavController().navigate(R.id.action_settingsFragment_to_welcomeFragment)
        }
        themeRadioGroup = view.findViewById(R.id.theme_radiogroup)
        themeRadioGroup.clearCheck()
        themeLightButton = view.findViewById(R.id.theme_light_button)
        themeDarkButton = view.findViewById(R.id.theme_dark_button)
        when(sharedViewModel.current_theme){
            0 -> {
                themeLightButton.isChecked = true
                themeLightButton.setTextColor(resources.getColor(R.color.black))
                themeDarkButton.setTextColor(resources.getColor(R.color.black))
            }
            1 -> {
                themeDarkButton.isChecked = true
                themeLightButton.setTextColor(resources.getColor(R.color.white))
                themeDarkButton.setTextColor(resources.getColor(R.color.white))
            }
        }
        themeRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.theme_light_button -> {
                    sharedViewModel.current_theme = 0
                    //Log.d("THEME", "Light button clicked, calling activity method")
                    (activity as MainActivity?)!!.changeTheme(sharedViewModel.current_theme)
                }
                R.id.theme_dark_button -> {
                    sharedViewModel.current_theme = 1
                    //Log.d("THEME", "Dark button clicked, calling activity method")
                    (activity as MainActivity?)!!.changeTheme(sharedViewModel.current_theme)
                }
                else -> null
            }
        }
        return view
    }
}