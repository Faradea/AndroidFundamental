package com.example.coroutinesnetworkrequest.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {

    val text: MutableLiveData<String> = MutableLiveData()

    fun  onSingleLaunchClicked() {
        viewModelScope.launch {
            fetchDocs("1")
        }
    }

    fun onMultipleSequenceClicked() {
        viewModelScope.launch {
            //fetchSeveralDocsParallel()
            fetchSeveralDocsSequence()
        }
    }

    fun onMultipleParallelClicked() {
        viewModelScope.launch {
            fetchSeveralDocsParallel()
        }
    }

    private suspend fun fetchDocs(url: String) {              // Dispatchers.Main
        val result = get(url)  // Dispatchers.Main
        text.postValue(result)                     // Dispatchers.Main
    }

    private suspend fun fetchSeveralDocsSequence() =        // called on any Dispatcher (any thread, possibly Main)
        coroutineScope {
            var result: String =  get("1")
            result = "$result + ${get("2")}"
            result = "$result + ${get("3")}"
            result = "$result + ${get("4")}"

            text.postValue(result)
        }

    private suspend fun fetchSeveralDocsParallel() =        // called on any Dispatcher (any thread, possibly Main)
        coroutineScope {
            val deferreds = listOf(     // fetch two docs at the same time
                async { get("1") },  // async returns a result for the first doc
                async { get("2") },   // async returns a result for the second doc
                async { get("3")},
                async { get("4")},
                async { get("5")}
            )
            //deferreds.awaitAll()        // use awaitAll to wait for both network requests
            var result = ""
            deferreds.forEach {
                result = if (result.isEmpty()) {
                    it.await()
                } else {
                    "$result + ${it.await()}"
                }
            }
            text.postValue(result)
        }

    private suspend fun get(url: String): String =         // Dispatchers.Main
        withContext(Dispatchers.IO) {              // Dispatchers.IO (main-safety block)
            delay(1000L)
            "Content from url = $url"              // Dispatchers.IO (main-safety block)
        }                                          // Dispatchers.Main
}
