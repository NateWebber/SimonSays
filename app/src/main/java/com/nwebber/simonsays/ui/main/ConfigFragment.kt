package com.nwebber.simonsays.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nwebber.simonsays.R

class ConfigFragment : Fragment() {
    companion object {
        fun newInstance() = ConfigFragment()
    }
    private val sharedViewModel: MainViewModel by activityViewModels()


    private lateinit var playButton: Button
    private lateinit var selectedDifficulty: DifficultyDefinition
    private lateinit var recycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.config_fragment, container, false)

        playButton = view.findViewById(R.id.play_button)
        recycler = view.findViewById(R.id.recyclerView)
        recycler.layoutManager = LinearLayoutManager(context)

        playButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_configFragment_to_gameFragment)
        }
        selectedDifficulty = sharedViewModel.difficultyMedium

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.difficultyVocab.observe(viewLifecycleOwner, {
            recycler.adapter = DifficultyAdapter(it)
        })
    }



    private inner class DifficultyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{
        private val nameTextView: TextView = itemView.findViewById(R.id.name_textView)
        private lateinit var difficulty: DifficultyDefinition
        init{
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            selectedDifficulty = difficulty
        }

        fun bind(difficulty: DifficultyDefinition){
            this.difficulty = difficulty
            nameTextView.text = difficulty.name
        }
    }

    private inner class DifficultyAdapter(private val list: List<DifficultyDefinition>) : RecyclerView.Adapter<DifficultyViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DifficultyViewHolder {
            val view = layoutInflater.inflate(R.layout.recycler_item, parent, false)
            return DifficultyViewHolder(view)
        }

        override fun getItemCount() = list.size

        override fun onBindViewHolder(holder: DifficultyViewHolder, position: Int) {
            holder.bind(list[position])
        }

    }
}