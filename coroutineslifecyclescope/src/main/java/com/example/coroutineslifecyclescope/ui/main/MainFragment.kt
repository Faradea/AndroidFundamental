package com.example.coroutineslifecyclescope.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import com.example.coroutineslifecyclescope.R
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragment : Fragment() {

    companion object {
        const val isUserAuthorized = false
        fun newInstance() = MainFragment()
    }

    init { // Notice that we can safely launch in the constructor of the Fragment.
        lifecycleScope.launch {
            whenStarted {
                // The block inside will run only when Lifecycle is at least STARTED.
                // It will start executing when fragment is started and
                // can call other suspend methods.
                progressBar.visibility = View.VISIBLE
                val canAccess = withContext(Dispatchers.IO) {
                    checkUserAccess()
                }

                // When checkUserAccess returns, the next line is automatically
                // suspended if the Lifecycle is not *at least* STARTED.
                // We could safely run fragment transactions because we know the
                // code won't run unless the lifecycle is at least STARTED.
                progressBar.visibility = View.GONE
                if (!canAccess) {
                    Toast.makeText(this@MainFragment.context, "NO ACCESS", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MainFragment.context, "welcome", Toast.LENGTH_SHORT).show()
                }
            }

            // This line runs only after the whenStarted block above has completed.

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    private suspend fun checkUserAccess(): Boolean {
        delay(1000)
        return isUserAuthorized
    }
}
