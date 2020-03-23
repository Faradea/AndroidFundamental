package com.example.playground4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment

// Хорошая теория по фрагментам - https://www.youtube.com/watch?v=EHxHZs5xI3Q
class ActivityWithFragments : AppCompatActivity(), OnNextButtonFragmentListener, OnDoneButtonFragmentListener {

    companion object {
        const val CURRENT_FRAGMENT_NUMBER_KEY = "currentFragmentNumber"
    }

    // Номер следующего фрагмента который нужно показать на экране
    private var nextFragmentNumber = 0
    private val fragmentsProvider = FragmentsProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_with_fragments)

        // Проверяем есть ли сохраненное значение номера текущего фрагмента на экране
        savedInstanceState?.getInt(CURRENT_FRAGMENT_NUMBER_KEY)?.let {
             nextFragmentNumber = it
        }

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
        // Чистим стек чтобы после кнопки done нельзя было вернуться назад
        clearStack()
        finish()
    }

    // Передаем во фрагмент ссылку на текущую активити, (эта ссылка нужна будет фрагменту чтобы отправить события клика на кнопку)
    override fun onAttachFragment(fragment: Fragment) {
        when (fragment) {
            is FragmentWithNextButton -> (fragment as FragmentWithNextButton).setOnNextButtonListener(this)
            is FragmentWithDoneButton -> (fragment as FragmentWithDoneButton).setOnDoneButtonListener(this)
        }
    }

    private fun showNextFragment() {
        val nextFragment = fragmentsProvider.provideFragment(nextFragmentNumber)
        nextFragmentNumber += 1
        nextFragment?.let {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, it)
                .addToBackStack(nextFragmentNumber.toString())
                .commit()
        }
    }

    // Чтобы пропустить состояние "активити без добавленного фрагмента" (потому что когда открывается активити - это состояние попадает в стек)
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) super.onBackPressed()
        super.onBackPressed()
    }

    // Сохраняем номер текущего фрагмента на экране (метод вызывается при "повороте экрана")
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(CURRENT_FRAGMENT_NUMBER_KEY, nextFragmentNumber)
        super.onSaveInstanceState(outState)
    }

    private fun clearStack() {
        val supportFragmentManager = supportFragmentManager
        var count = supportFragmentManager.backStackEntryCount
        while (count >= 0) {
            onBackPressed()
            count--
        }
    }
}
