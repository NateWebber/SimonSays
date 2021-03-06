package com.nwebber.simonsays.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.nwebber.simonsays.R

class ResultsFragment : Fragment(){
    companion object {
        fun newInstance() = GameFragment()
    }
    private val sharedViewModel: MainViewModel by activityViewModels()

    private lateinit var againButton: Button
    private lateinit var scoreText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.results_fragment, container, false)

        againButton = view.findViewById(R.id.again_button)

        againButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_resultsFragment_to_welcomeFragment)
        }

        scoreText = view.findViewById(R.id.you_scored_text)

        return view
    }
    private val args: ResultsFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scoreText.text = (getString(R.string.your_score).format(args.score))
    }
}