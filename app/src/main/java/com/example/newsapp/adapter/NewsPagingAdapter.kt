package com.example.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.databinding.ListItemBinding
import com.example.newsapp.retrofits.response.Article
import kotlinx.android.synthetic.main.list_item.view.*
import java.util.*

class NewsPagingAdapter(val adapterClickListeners: AdapterClickListeners) :
        PagingDataAdapter<Article, NewsPagingAdapter.MyViewHolder>(DIFF_UTIL) {

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.title == newItem.title
            }

        }
    }

    inner class MyViewHolder(val viewDataBinding: ViewDataBinding) :
            RecyclerView.ViewHolder(viewDataBinding.root)

    override fun onBindViewHolder(holder: NewsPagingAdapter.MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.viewDataBinding.setVariable(BR.article, item)
        Glide.with(holder.viewDataBinding.root).load(item!!.urlToImage).into(holder.viewDataBinding.root.image_list_item)

        holder.viewDataBinding.root.list_item_root.setOnClickListener {
            adapterClickListeners.clickListeners(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsPagingAdapter.MyViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
}