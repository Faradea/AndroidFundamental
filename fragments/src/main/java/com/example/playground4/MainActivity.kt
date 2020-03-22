package com.example.playground4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity(), OnNextButtonFragmentListener, OnDoneButtonFragmentListener {

    private var currentFragmentNumber = 0
    private val fragmentsProvider = FragmentsProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            showNextFragment()
        }
    }

    override fun onNextButton() {
        Toast.makeText(this, "Next button", Toast.LENGTH_SHORT).show()
        showNextFragment()
    }


    override fun onDoneButton() {
        Toast.makeText(this, "Done button", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onAttachFragment(fragment: Fragment) {
        when (fragment) {
            is FragmentWithNextButton -> (fragment as FragmentWithNextButton).setOnNextButtonListener(this)
            is FragmentWithDoneButton -> (fragment as FragmentWithDoneButton).setOnDoneButtonListener(this)
        }
    }

    private fun showNextFragment() {
        val nextFragment = fragmentsProvider.provideNextFragment(currentFragmentNumber)
        currentFragmentNumber += 1
        nextFragment?.let {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, it)
                .addToBackStack(currentFragmentNumber.toString())
                .commit()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) super.onBackPressed()
        super.onBackPressed()
    }
}
