package com.task.temponewstask.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

open class BaseViewModel : ViewModel() {
    val progressVisibility = MutableLiveData<Boolean>()
    protected val parentJob = Job()
    protected val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            coroutineScope.launch(Dispatchers.Main) {
                Log.e("error", "error");
            }

            GlobalScope.launch {
                println("Caught $throwable")
                progressVisibility.postValue(false)
            }
        }

    protected val coroutineScope = CoroutineScope(
        Dispatchers.Main + parentJob +
                coroutineExceptionHandler
    )
}