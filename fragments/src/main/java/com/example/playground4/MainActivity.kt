package com.example.playground4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity(), Fragment1.OnButtonClickFragment1Listener, Fragment2.OnButtonClickFragment2Listener {

    val fragments = listOf(Fragment1::class)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showFirstFragment()
    }

    override fun onNextButton() {
        Toast.makeText(this, "Next button in Fragment1", Toast.LENGTH_SHORT).show()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, Fragment2())
            .addToBackStack(null)
            .commit()
    }


    override fun onDoneButton() {
        Toast.makeText(this, "Done button in Fragment2", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onAttachFragment(fragment: Fragment) {
        when (fragment) {
            is Fragment1 -> fragment.setOnButtonClickFragment1Listener(this)
            is Fragment2 -> fragment.setOnButtonClickFragment2Listener(this)
        }
    }

    private fun showFirstFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, Fragment1())
            .commit()
    }
}
