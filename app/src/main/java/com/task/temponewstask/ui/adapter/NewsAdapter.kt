package com.task.temponewstask.ui.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.task.temponewstask.R
import com.task.temponewstask.model.dto.response.Articles
import com.task.temponewstask.ui.adapter.NewsAdapter.NewsViewHolder
import com.task.temponewstask.utils.TimeHelper.reformatBackEndDate

class NewsAdapter(
    private val articles: ArrayList<Articles>,
    var context: Context,
    var iNewsAction: INewsAction
) : RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_article, parent, false)
        return NewsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articles[position]
        holder.tv_article_title.text = article.title
        holder.tv_article_author.text = article.author
        holder.tv_article_date.text = reformatBackEndDate(article.publishedAt)
        holder.tv_article_description.text = article.description
        holder.tv_article_source_text.text = article.source.name
        holder.tv_article_title.text = article.title
        holder.itemView.setOnClickListener { view: View? ->
            iNewsAction.clicked(
                article
            )
        }
        Glide.with(context) //2
            .load(article.urlToImage) //3
            //                .centerCrop() //4
            //                .placeholder(R.drawable.ic_image_place_holder) //5
            //                .error(R.drawable.ic_broken_image) //6
            //                .fallback(R.drawable.ic_no_image) //7
            .into(holder.iv_article_image)
        holder.btn_source.setOnClickListener { view: View? ->
            val browse = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
            context.startActivity(browse)
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    fun addItems(articles: List<Articles>) {
        this.articles.addAll(articles)
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var tv_article_title: TextView
        var tv_article_date: TextView
        var tv_article_description: TextView
        var tv_article_source_text: TextView
        var tv_article_author: TextView
        var iv_article_image: ImageView
        var btn_source: Button

        init {
            tv_article_title = view.findViewById(R.id.tv_article_title)
            tv_article_date = view.findViewById(R.id.tv_article_date)
            tv_article_description = view.findViewById(R.id.tv_article_description)
            tv_article_source_text = view.findViewById(R.id.tv_article_source_text)
            tv_article_author = view.findViewById(R.id.tv_article_author)
            iv_article_image = view.findViewById(R.id.iv_article_image)
            btn_source = view.findViewById(R.id.btn_source)
        }
    }

}