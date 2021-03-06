package com.example.sharedprefs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class TutorialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)

        val sharedPrefs = SharedPreferencesProvider(this)
        sharedPrefs.onTutorialPassed()
    }
}