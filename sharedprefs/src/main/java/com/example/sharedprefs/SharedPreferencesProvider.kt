package com.example.sharedprefs

import android.content.Context
import android.content.Context.MODE_PRIVATE

class SharedPreferencesProvider(context: Context) {

    companion object {
        const val SHARED_PREFS_FILE_NAME = "sharedPrefs"
        const val IS_TUTORIAL_PASSED = "isTutorialPassed"
    }

    private val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE_NAME, MODE_PRIVATE)

    fun isTutorialAlreadyPassed(): Boolean {
        return sharedPreferences.getBoolean(IS_TUTORIAL_PASSED, false)
    }

    fun onTutorialPassed() {
        sharedPreferences.edit()
            .putBoolean(IS_TUTORIAL_PASSED, true)
            .apply()
    }

}