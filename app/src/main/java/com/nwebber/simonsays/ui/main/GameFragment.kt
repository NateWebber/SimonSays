package com.nwebber.simonsays.ui.main

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
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
        //TODO receive the difficulty from somewhere and apply it to this variable
        //Easy = 1.25? Normal = 1 Hard = .75?
        var animationSpeedDifficultyModifier = 1
        const val ANIM_SPEED_MASTER = 200L
    }
    private val sharedViewModel: MainViewModel by activityViewModels()

    private lateinit var endButton: Button
    private lateinit var redButton: ImageButton
    private lateinit var yellowButton: ImageButton
    private lateinit var greenButton: ImageButton
    private lateinit var blueButton: ImageButton
    private lateinit var score_text: TextView

    //Animators and Sets
    private lateinit var animatorRed1 : ObjectAnimator
    private lateinit var animatorRed2 : ObjectAnimator
    private lateinit var animSetRed : AnimatorSet

    private lateinit var animatorYellow1 : ObjectAnimator
    private lateinit var animatorYellow2 : ObjectAnimator
    private lateinit var animSetYellow : AnimatorSet

    private lateinit var animatorGreen1 : ObjectAnimator
    private lateinit var animatorGreen2 : ObjectAnimator
    private lateinit var animSetGreen : AnimatorSet

    private lateinit var animatorBlue1 : ObjectAnimator
    private lateinit var animatorBlue2 : ObjectAnimator
    private lateinit var animSetBlue : AnimatorSet



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.game_fragment, container, false)

        score_text = view.findViewById(R.id.score_text)

        endButton = view.findViewById(R.id.end_button)

        endButton.setOnClickListener {
            val action = GameFragmentDirections.actionGameFragmentToResultsFragment(sharedViewModel.current_score)
            view.findNavController().navigate(action)
        }
        redButton = view.findViewById(R.id.button_red)
        yellowButton = view.findViewById(R.id.button_yellow)
        greenButton = view.findViewById(R.id.button_green)
        blueButton = view.findViewById(R.id.button_blue)


        animatorRed1 = ObjectAnimator.ofFloat(redButton, "scaleY", 1f, 0f)
        animatorRed2 = ObjectAnimator.ofFloat(redButton, "scaleY", 0f, 1f)
        animSetRed = AnimatorSet()

        animatorYellow1 = ObjectAnimator.ofFloat(yellowButton, "scaleY", 1f, 0f)
        animatorYellow2 = ObjectAnimator.ofFloat(yellowButton, "scaleY", 0f, 1f)
        animSetYellow = AnimatorSet()

        animatorGreen1 = ObjectAnimator.ofFloat(greenButton, "scaleY", 1f, 0f)
        animatorGreen2 = ObjectAnimator.ofFloat(greenButton, "scaleY", 0f, 1f)
        animSetGreen = AnimatorSet()

        animatorBlue1 = ObjectAnimator.ofFloat(blueButton, "scaleY", 1f, 0f)
        animatorBlue2 = ObjectAnimator.ofFloat(blueButton, "scaleY", 0f, 1f)
        animSetBlue = AnimatorSet()


        redButton.setOnClickListener {
            var result = sharedViewModel.checkInput(0) //red clicked
            if (!result){
                val action = GameFragmentDirections.actionGameFragmentToResultsFragment(sharedViewModel.current_score)
                view.findNavController().navigate(action)
            }
            else if (sharedViewModel.turnCompleted()) {
                sharedViewModel.current_score++
                score_text.text = (getString(R.string.your_score).format(sharedViewModel.current_score))
                animateRed()
                sharedViewModel.simonTurn()
                //play simon animations here
            }
            else{
                animateRed()
            }
        }
        yellowButton.setOnClickListener {
            var result = sharedViewModel.checkInput(1) //yellow clicked
            if (!result){
                val action = GameFragmentDirections.actionGameFragmentToResultsFragment(sharedViewModel.current_score)
                view.findNavController().navigate(action)
            }
            else if (sharedViewModel.turnCompleted()){
                sharedViewModel.current_score++
                score_text.text = (getString(R.string.your_score).format(sharedViewModel.current_score))
                animateYellow()
                sharedViewModel.simonTurn()
                //play simon animations here
            }
            else{
                animateYellow()
            }
        }
        greenButton.setOnClickListener {
            var result = sharedViewModel.checkInput(2) //green clicked
            if (!result){
                val action = GameFragmentDirections.actionGameFragmentToResultsFragment(sharedViewModel.current_score)
                view.findNavController().navigate(action)
            }
            else if (sharedViewModel.turnCompleted()){
                sharedViewModel.current_score++
                score_text.text = (getString(R.string.your_score).format(sharedViewModel.current_score))
                animateGreen()
                sharedViewModel.simonTurn()
                //play simon animations here
            }
            animSetGreen.play(animatorGreen1).before(animatorGreen2)
            animSetGreen.duration = (ANIM_SPEED_MASTER * animationSpeedDifficultyModifier)
            animSetGreen.start()
        }
        blueButton.setOnClickListener {
            var result = sharedViewModel.checkInput(3) //blue clicked
            if (!result){
                val action = GameFragmentDirections.actionGameFragmentToResultsFragment(sharedViewModel.current_score)
                view.findNavController().navigate(action)
            }
            else if (sharedViewModel.turnCompleted()){
                animateBlue()
                sharedViewModel.current_score++
                score_text.text = (getString(R.string.your_score).format(sharedViewModel.current_score))
                sharedViewModel.simonTurn()
                //play simon animations here
            }
            animateBlue()
        }

        return view
    }

    private fun animateRed(){
        disableAllButtons()
        animSetRed.play(animatorRed1).before(animatorRed2)
        animSetRed.duration = (ANIM_SPEED_MASTER * animationSpeedDifficultyModifier)
        animSetRed.start()
        animatorRed2.addListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {}
            override fun onAnimationEnd(animation: Animator?){
                enableAllButtons()
            }
            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationStart(animation: Animator?) {}
        })

    }
    private fun animateYellow(){
        disableAllButtons()
        animSetYellow.play(animatorYellow1).before(animatorYellow2)
        animSetYellow.duration = (ANIM_SPEED_MASTER * animationSpeedDifficultyModifier)
        animSetYellow.start()
        animatorYellow2.addListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {}
            override fun onAnimationEnd(animation: Animator?){
                enableAllButtons()
            }
            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationStart(animation: Animator?) {}
        })

    }
    private fun animateGreen(){
        disableAllButtons()
        animSetGreen.play(animatorGreen1).before(animatorGreen2)
        animSetGreen.duration = (ANIM_SPEED_MASTER * animationSpeedDifficultyModifier)
        animSetGreen.start()
        animatorGreen2.addListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {}
            override fun onAnimationEnd(animation: Animator?){
                enableAllButtons()
            }
            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationStart(animation: Animator?) {}
        })
    }
    private fun animateBlue(){
        disableAllButtons()
        animSetBlue.play(animatorBlue1).before(animatorBlue2)
        animSetBlue.duration = (ANIM_SPEED_MASTER * animationSpeedDifficultyModifier)
        animSetBlue.start()
        animatorBlue2.addListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {}
            override fun onAnimationEnd(animation: Animator?){
                enableAllButtons()
            }
            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationStart(animation: Animator?) {}
        })
    }
    private fun disableAllButtons(){
        redButton.isEnabled = false
        yellowButton.isEnabled = false
        greenButton.isEnabled = false
        blueButton.isEnabled = false

    }
    private fun enableAllButtons(){
        redButton.isEnabled = true
        yellowButton.isEnabled = true
        greenButton.isEnabled = true
        blueButton.isEnabled = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.startGame()
        score_text.text = (getString(R.string.your_score).format(sharedViewModel.current_score))
        //play simon animations here
    }



}