package com.example.commandsequence

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        commandSequenceTv.setOnClickListener {
            val intent = Intent(this, CommandSequenseActivity::class.java)
            startActivity(intent)
        }
    }
}
