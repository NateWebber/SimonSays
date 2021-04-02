package com.nwebber.simonsays.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nwebber.simonsays.R

class ResultsFragment : Fragment(){
    companion object {
        fun newInstance() = ResultsFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.results_fragment, container, false)
    }
}