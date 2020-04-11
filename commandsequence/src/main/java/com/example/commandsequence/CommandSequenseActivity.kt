package com.example.commandsequence

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_command_sequense.*

// Activity is opened, press button twice. Result? (logs and toasts)
class CommandSequenseActivity : AppCompatActivity() {

    val log = "text for Log.d"

    override fun onResume() {
        super.onResume()
        showToast("3")
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_command_sequense)

        button.text = "Custom button text"
        Log.d("TAG", "logging new text for button = ${button.text}")

        button.setOnClickListener {
            showToast("1")
            Log.d("TAG", "logging $log")
        }
        showToast("2")
    }
}
