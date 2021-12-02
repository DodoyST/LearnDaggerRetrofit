package com.example.daggerbikinpusing.data.adapter.viewholder

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.daggerbikinpusing.R
import com.example.daggerbikinpusing.data.model.Blog
import com.example.daggerbikinpusing.data.viewmodel.BlogViewModel
import com.example.daggerbikinpusing.databinding.CardBlogBinding

class BlogViewHolder(itemView: View, private val blogViewModel: BlogViewModel) :
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
      it.findNavController().navigate(R.id.action_global_homeFragment_to_blogDetailFragment)
      blogViewModel.getById(itemBlogId)
    }
  }
}
