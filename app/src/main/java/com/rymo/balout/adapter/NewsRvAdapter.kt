package com.rymo.balout.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rymo.balout.R
import com.rymo.balout.model.Articles
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_rv_news_item.view.*

class NewsRvAdapter (var context: Context,var list: ArrayList<Articles>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_rv_news_item,parent,false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val viewHolder = holder as ViewHolder

        viewHolder.tv_title.text = list[position].title
        viewHolder.tv_published_at.text = list[position].publishedAt
        viewHolder.tv_author.text = list[position].author

        Picasso.get().load(list[position].urlToImage).into(viewHolder.imv_news)

    }

    override fun getItemCount(): Int  = list.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imv_news = itemView.imv_news
        var tv_title = itemView.tv_title
        var tv_author = itemView.tv_author
        var tv_published_at = itemView.tv_published_at
    }
}