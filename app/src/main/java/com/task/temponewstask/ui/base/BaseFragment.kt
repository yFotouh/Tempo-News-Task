package com.task.temponewstask.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.task.temponewstask.R
import java.io.Serializable

open class BaseFragment : Fragment() {
    lateinit var currentView: View
    var progressBar: ProgressBar? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        currentView = inflater.inflate(getLayoutId(), container, false)
        return currentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = currentView.findViewById<ProgressBar>(R.id.pBar)?.apply { }
        doOnViewCreated(view, savedInstanceState)
    }

    open protected fun doOnViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    open protected fun getBundle(data: Serializable): Bundle {
        var bundle = Bundle()
        bundle.putSerializable(data.javaClass.simpleName, data)
        return bundle
    }


    open protected fun getLayoutId(): Int {
        return 0
    }

    open protected fun toggleProgressBarState(state: Boolean) {
        if (state)
            progressBar?.visibility = View.VISIBLE
        else
            progressBar?.visibility = View.GONE

    }

}