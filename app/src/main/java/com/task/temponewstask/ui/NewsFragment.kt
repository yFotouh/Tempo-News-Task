package com.task.temponewstask.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.temponewstask.R
import com.task.temponewstask.model.dto.response.Articles
import com.task.temponewstask.ui.adapter.INewsAction
import com.task.temponewstask.ui.adapter.NewsAdapter
import com.task.temponewstask.ui.adapter.PaginationListener
import com.task.temponewstask.ui.base.BaseFragment
import com.task.temponewstask.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_news.*
import org.koin.androidx.viewmodel.ext.android.viewModel


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [NewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsFragment : BaseFragment() {


    val newsViewModel: NewsViewModel by viewModel()
    private lateinit var viewAdapter: NewsAdapter
    private var pageNumber: Int = 1
    private var articles = ArrayList<Articles>()
    private var currentQuery: String = ""
    private var lastPage: Boolean = true
    override fun getLayoutId(): Int {
        return R.layout.fragment_news
    }

    override fun doOnViewCreated(view: View, savedInstanceState: Bundle?) {
        observeProgressBar()
        refreshAdapter()
        setListeners()
    }

    private fun setListeners() {
        ed_search.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                handleSearch()
                return@OnEditorActionListener true
            }
            false
        })
        iv_search.setOnClickListener {
            handleSearch()
        }
    }

    private fun handleSearch() {
        currentQuery = ed_search.text.toString()
        if (currentQuery.isEmpty() == false) {
            articles.clear()
            pageNumber = 1
            hideKeyboard()
            callWebApi()
        } else {
            Toast.makeText(
                activity,
                getString(R.string.please_enter_search_query),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun hideKeyboard() {

        val view = this.activity?.currentFocus
        view?.let { v ->
            val imm =
                activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }

    private fun observeProgressBar() {
        newsViewModel.progressVisibility.observe(viewLifecycleOwner, Observer {
            toggleProgressBarState(it)
        })
    }


    private fun refreshAdapter() {

        viewAdapter = NewsAdapter(
            articles,
            activity,
            INewsAction {

                findNavController().navigate(
                    R.id.action_weatherFragment_to_newsDetailFragment,
                    getBundle(it)
                )


            })
        var linearLayoutManager = LinearLayoutManager(activity)
        rv_news.layoutManager = linearLayoutManager
        rv_news.adapter = viewAdapter
        rv_news.addOnScrollListener(object : PaginationListener(linearLayoutManager) {


            override fun isLastPage(): Boolean {
                return lastPage
            }

            override fun loadMoreItems() {
                pageNumber++
                callWebApi()
            }

            override fun isLoading(): Boolean {
                return newsViewModel.progressVisibility.value!!
            }
        })
    }

    private fun callWebApi() {

        newsViewModel.getNews(currentQuery, pageNumber).observe(viewLifecycleOwner, Observer {
            articles.addAll(it.articles)
            lastPage = articles.size == it.totalResults
            viewAdapter.addItems(it.articles)
        })
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param city Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WeatherFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            NewsFragment().apply {
            }
    }
}
