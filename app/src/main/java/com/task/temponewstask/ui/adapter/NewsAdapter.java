package com.task.temponewstask.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.task.temponewstask.R;
import com.task.temponewstask.model.dto.response.Articles;
import com.task.temponewstask.utils.TimeHelper;

import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<Articles> articles;
    Context context;
    INewsAction iNewsAction;

    public NewsAdapter(List<Articles> articles, Context context, INewsAction iNewsAction) {
        this.articles = articles;
        this.context = context;
        this.iNewsAction = iNewsAction;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_article, parent, false);

        return new NewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Articles article = this.articles.get(position);
        holder.tv_article_title.setText(article.getTitle());
        holder.tv_article_author.setText(article.getAuthor());
        holder.tv_article_date.setText(TimeHelper.reformatBackEndDate(article.getPublishedAt()));
        holder.tv_article_description.setText(article.getDescription());
        holder.tv_article_source_text.setText(article.getSource().getName());
        holder.tv_article_title.setText(article.getTitle());
        holder.itemView.setOnClickListener(view -> {
            iNewsAction.clicked(article);
        });
        Glide.with(context)  //2
                .load(article.getUrlToImage()) //3
//                .centerCrop() //4
//                .placeholder(R.drawable.ic_image_place_holder) //5
//                .error(R.drawable.ic_broken_image) //6
//                .fallback(R.drawable.ic_no_image) //7
                .into(holder.iv_article_image);
        holder.btn_source.setOnClickListener((view) -> {
            Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getUrl()));
            context.startActivity(browse);

        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void addItems(List<Articles> articles) {
        articles.addAll(articles);
        notifyDataSetChanged();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_article_title;
        public TextView tv_article_date;
        public TextView tv_article_description;
        public TextView tv_article_source_text;
        public TextView tv_article_author;
        public ImageView iv_article_image;
        public Button btn_source;

        public NewsViewHolder(View view) {
            super(view);
            tv_article_title = view.findViewById(R.id.tv_article_title);
            tv_article_date = view.findViewById(R.id.tv_article_date);
            tv_article_description = view.findViewById(R.id.tv_article_description);
            tv_article_source_text = view.findViewById(R.id.tv_article_source_text);
            tv_article_author = view.findViewById(R.id.tv_article_author);
            iv_article_image = view.findViewById(R.id.iv_article_image);
            btn_source = view.findViewById(R.id.btn_source);
        }

    }
}