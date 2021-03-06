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
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.nwebber.simonsays.MainActivity
import com.nwebber.simonsays.R
import com.nwebber.simonsays.databinding.SettingsFragmentBinding

class SettingsFragment : Fragment() {
    companion object {
        fun newInstance() = SettingsFragment()
    }
    private val sharedViewModel: MainViewModel by activityViewModels()
    private lateinit var backButton : Button
    private lateinit var themeRadioGroup: RadioGroup
    private lateinit var themeLightButton: RadioButton
    private lateinit var themeDarkButton: RadioButton

    private lateinit var cbRadioGroup: RadioGroup
    private lateinit var cbDisabledButton: RadioButton
    private lateinit var cbEnabledButton: RadioButton

    private lateinit var binding: SettingsFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.settings_fragment, container, false)
        binding.run{
            viewModel = sharedViewModel
            lifecycleOwner = this@SettingsFragment
        }

        val view = binding.root

        backButton = view.findViewById(R.id.back_button)
        backButton.setOnClickListener{
            view.findNavController().navigate(R.id.action_settingsFragment_to_welcomeFragment)
        }
        themeRadioGroup = view.findViewById(R.id.theme_radiogroup)
        themeRadioGroup.clearCheck()
        themeLightButton = view.findViewById(R.id.theme_light_button)
        themeDarkButton = view.findViewById(R.id.theme_dark_button)
        cbRadioGroup = view.findViewById(R.id.cb_radiogroup)
        cbDisabledButton = view.findViewById(R.id.cb_disabled_button)
        cbEnabledButton = view.findViewById(R.id.cb_enabled_button)
        when(sharedViewModel.current_theme){
            0 -> {
                themeLightButton.isChecked = true
                themeLightButton.setTextColor(resources.getColor(R.color.black))
                themeDarkButton.setTextColor(resources.getColor(R.color.black))
                cbEnabledButton.setTextColor(resources.getColor(R.color.black))
                cbDisabledButton.setTextColor(resources.getColor(R.color.black))
            }
            1 -> {
                themeDarkButton.isChecked = true
                themeLightButton.setTextColor(resources.getColor(R.color.white))
                themeDarkButton.setTextColor(resources.getColor(R.color.white))
                cbEnabledButton.setTextColor(resources.getColor(R.color.white))
                cbDisabledButton.setTextColor(resources.getColor(R.color.white))
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

        cbRadioGroup.clearCheck()
        when (sharedViewModel.colorblindEnabled){
            false -> cbDisabledButton.isChecked = true
            true -> cbEnabledButton.isChecked = true
        }
        /*cbRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId){
                R.id.cb_disabled_button -> sharedViewModel.colorblindEnabled = false
                R.id.cb_enabled_button -> sharedViewModel.colorblindEnabled = true
            }
        }*/
        return view
    }
}