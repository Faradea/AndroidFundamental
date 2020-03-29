package com.example.coroutines.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coroutines.R
import com.example.coroutines.data.SharedPreferencesProvider
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    private lateinit var sharedPrefs : SharedPreferencesProvider

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sharedPrefs = SharedPreferencesProvider(this.requireContext())
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        isTutorialPassedSwitch.isChecked = sharedPrefs.isTutorialAlreadyPassed()

        changeTutorialStateButton.setOnClickListener {
            sharedPrefs.onTutorialPassedChangeState()
            GlobalScope.launch {
                // Logging delayed for 2 seconds
                delay(2000L)
                Log.d("CoroutinesApp", "changeTutorialStateButton has been pressed 2 seconds ago")
            }
            isTutorialPassedSwitch.isChecked = sharedPrefs.isTutorialAlreadyPassed()
        }
    }

}
