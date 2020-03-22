package com.example.playground4

import androidx.fragment.app.Fragment

class FragmentsProvider {

    companion object {
        const val FRAGMENT1_KEY = "Fragment1"
        const val FRAGMENT2_KEY = "Fragment2"
        const val FRAGMENT3_KEY = "Fragment3"
    }

    private val fragments = listOf(FRAGMENT1_KEY, FRAGMENT2_KEY, FRAGMENT3_KEY)

    fun provideNextFragment(targetFragment: Int): Fragment? {
        if (targetFragment >= fragments.size ) return null
        when (fragments[targetFragment]) {
            FRAGMENT1_KEY -> return Fragment1()
            FRAGMENT2_KEY -> return Fragment2()
            FRAGMENT3_KEY -> return Fragment3()
            else -> return null
        }
    }
}