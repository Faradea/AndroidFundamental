package com.example.playground4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_fragment2.*

class Fragment2 : Fragment() {

    private lateinit var callback: OnButtonClickFragment2Listener

    interface OnButtonClickFragment2Listener {
        fun onDoneButton()
    }

    fun setOnButtonClickFragment2Listener(callback: OnButtonClickFragment2Listener) {
        this.callback = callback
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fragment2, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        button2.setOnClickListener {
            callback.onDoneButton()
        }
    }
}