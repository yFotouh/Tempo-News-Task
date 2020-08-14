package com.task.temponewstask.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.task.temponewstask.R
import com.task.temponewstask.model.dto.response.Articles
import com.task.temponewstask.ui.base.BaseFragment
import com.task.temponewstask.utils.TimeHelper
import kotlinx.android.synthetic.main.fragment_news_detail.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewsDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsDetailFragment : BaseFragment() {
    private lateinit var article: Articles
    val ARTICLES_KEY = "Articles"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            article = it.getSerializable(ARTICLES_KEY) as Articles
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_news_detail
    }

    override fun doOnViewCreated(view: View, savedInstanceState: Bundle?) {
        super.doOnViewCreated(view, savedInstanceState)
        tv_article_title.setText(article.title)
        tv_article_author.setText(article.author)
        tv_article_date.setText(TimeHelper.reformatBackEndDate(article.publishedAt))
        tv_article_description.setText(article.description)
        tv_article_source_text.setText(article.source.name)
        tv_article_title.setText(article.title)

        Glide.with(this) //2
            .load(article.urlToImage) //3
            //                .centerCrop() //4
            //                .placeholder(R.drawable.ic_image_place_holder) //5
            //                .error(R.drawable.ic_broken_image) //6
            //                .fallback(R.drawable.ic_no_image) //7
            .into(iv_article_image)
        btn_source.setOnClickListener(View.OnClickListener { view: View? ->
            val browse = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
            this.startActivity(browse)
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NewsDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Articles) =
            NewsDetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARTICLES_KEY, param1)
                }
            }
    }
}