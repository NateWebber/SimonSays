package com.nwebber.simonsays

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import com.nwebber.simonsays.ui.main.MainViewModel
import com.nwebber.simonsays.ui.main.WelcomeFragment
class MainActivity : AppCompatActivity() {
    private val sharedViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("THEME", "Detected the intent's theme value as %d".format(intent.getIntExtra("theme", 0)))
        sharedViewModel.current_theme = intent.getIntExtra("theme", 0)
        when(sharedViewModel.current_theme){
            0 -> setTheme(R.style.Theme_SimonSays)
            1 -> setTheme(R.style.Theme_SimonSaysDark)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
    fun changeTheme(new_theme: Int){
        when(new_theme){
            0 -> {
                setTheme(R.style.Theme_SimonSays)
                Log.d("THEME", "Changed theme to light, trying to recreate")
                sharedViewModel.current_theme = 0
                val intent = intent
                intent.putExtra("theme", 0)
                finish()
                startActivity(intent)
            }
            1 -> {
                setTheme(R.style.Theme_SimonSaysDark)
                Log.d("THEME", "Changed theme to dark, trying to recreate")
                val intent = intent
                intent.putExtra("theme", 1)
                finish()
                startActivity(intent)
            }

        }

    }
}