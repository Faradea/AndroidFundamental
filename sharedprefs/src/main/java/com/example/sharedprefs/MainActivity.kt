package com.example.sharedprefs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

//1. Создать вторую активити и запустить ее через implicit intent
//2. Lyfecycle для двух активити (домашнее задание)
//2. Рассказать что такое intent filter и как он используется - например для вызова внешних приложений и открытия своего приложения по урлу
//3. Обработка нажатия на кнопку
//4. Shared preferences (обязательно рассказать про асинхронность)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tutorialB.setOnClickListener {
            val sharedPrefs = SharedPreferencesProvider(this)
            if (!sharedPrefs.isTutorialAlreadyPassed()) {
                val intent = Intent(this, TutorialActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Tutorial is already passed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
