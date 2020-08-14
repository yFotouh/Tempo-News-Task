package com.task.temponewstask.ui

import android.os.Bundle
import com.task.temponewstask.R
import com.task.temponewstask.ui.base.BaseAppCompatActivity

class MainActivity : BaseAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        navigateToFragment(WeatherFragment.newInstance(), false)
    }
}
