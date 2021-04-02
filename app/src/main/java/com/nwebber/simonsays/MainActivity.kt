package com.nwebber.simonsays

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nwebber.simonsays.ui.main.WelcomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, WelcomeFragment.newInstance())
                    .commitNow()
        }
    }
}