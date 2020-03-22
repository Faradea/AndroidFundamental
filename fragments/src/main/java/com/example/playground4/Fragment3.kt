package com.example.playground4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_fragment3.*

class Fragment3 : Fragment(), FragmentWithDoneButton {

    private lateinit var callback: OnDoneButtonFragmentListener

    override fun setOnDoneButtonListener(callback: OnDoneButtonFragmentListener) {
        this.callback = callback
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fragment3, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        buttonDone.setOnClickListener {
            callback.onDoneButton()
        }
    }
}