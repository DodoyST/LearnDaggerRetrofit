package com.example.daggerbikinpusing.data.adapter.viewholder

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.daggerbikinpusing.data.model.Blog
import com.example.daggerbikinpusing.databinding.CardBlogBinding

class BlogViewHolder(itemView: View) :
  RecyclerView.ViewHolder(itemView) {
  
  private val binding = CardBlogBinding.bind(itemView)
  private var itemBlogId = ""
  private var itemBlogUrl = ""
  
  fun bind(blog: Blog) {
    itemBlogId = blog.id
    itemBlogUrl = blog.url
    binding.apply {
      tvCardBlogTitle.text = blog.title
      tvCardBlogAuthor.text = blog.author
    }
  }
  
  init {
    itemView.setOnClickListener {
      Toast.makeText(itemView.context, "${binding.tvCardBlogTitle.text}", Toast.LENGTH_SHORT).show()
    }
  }
}
