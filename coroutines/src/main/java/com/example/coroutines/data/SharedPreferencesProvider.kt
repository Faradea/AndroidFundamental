package com.example.coroutines.data

import android.content.Context

class SharedPreferencesProvider(context: Context) {

    companion object {
        const val SHARED_PREFS_FILE_NAME = "sharedPrefs"
        const val IS_TUTORIAL_PASSED = "isTutorialPassed"
    }

    private val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE_NAME,
        Context.MODE_PRIVATE
    )

    fun isTutorialAlreadyPassed(): Boolean {
        return sharedPreferences.getBoolean(IS_TUTORIAL_PASSED, false)
    }

    fun onTutorialPassedChangeState() {
        sharedPreferences.edit()
            .putBoolean(IS_TUTORIAL_PASSED, !sharedPreferences.getBoolean(IS_TUTORIAL_PASSED, false))
            .apply()
    }

}