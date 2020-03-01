package com.example.playground4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_fragment1.*

class Fragment1 : Fragment() {

    private lateinit var callback: OnButtonClickFragment1Listener

    interface OnButtonClickFragment1Listener {
        fun onNextButton()
    }

    fun setOnButtonClickFragment1Listener(callback: OnButtonClickFragment1Listener) {
        this.callback = callback
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fragment1, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        button.setOnClickListener {
            callback.onNextButton()
        }
    }
}