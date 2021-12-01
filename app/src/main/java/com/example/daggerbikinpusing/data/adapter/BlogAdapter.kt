package com.example.daggerbikinpusing.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.daggerbikinpusing.R
import com.example.daggerbikinpusing.data.adapter.viewholder.BlogViewHolder
import com.example.daggerbikinpusing.data.model.Blog

class BlogAdapter(private val blogList: List<Blog>) :
  RecyclerView.Adapter<BlogViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.card_blog, parent, false)
    return BlogViewHolder(view)
  }
  
  override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
    holder.bind(blogList[position])
  }
  
  override fun getItemCount() = blogList.size
}
