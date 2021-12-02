package com.example.daggerbikinpusing.ui.screen

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.daggerbikinpusing.R
import com.example.daggerbikinpusing.data.repository.BlogRepositoryImpl
import com.example.daggerbikinpusing.data.viewmodel.BlogViewModel
import com.example.daggerbikinpusing.databinding.FragmentBlogDetailBinding
import com.example.daggerbikinpusing.util.AppResource
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class BlogDetailFragment : DaggerFragment() {
  private var _binding: FragmentBlogDetailBinding? = null
  private val binding get() = _binding!!
  
  private var id = ""
  private var url = ""
  
  private lateinit var blogViewModel: BlogViewModel
  
  @Inject
  lateinit var blogRepositoryImpl: BlogRepositoryImpl
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }
  
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    _binding = FragmentBlogDetailBinding.inflate(inflater, container, false)
    return binding.root
  }
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    
    blogViewModel = ViewModelProvider(requireActivity(), object : ViewModelProvider.Factory {
      override fun <T : ViewModel> create(modelClass: Class<T>): T =
        BlogViewModel(blogRepositoryImpl) as T
    })[BlogViewModel::class.java]
    
    subscribe()
  }
  
  override fun onDestroy() {
    super.onDestroy()
    
    _binding = null
  }
  
  private fun subscribe() {
    blogViewModel.blog.observe(viewLifecycleOwner, {
      when (it) {
        is AppResource.Success -> {
          if (it.data != null) {
            val response = it.data
            binding.apply {
              id = response.id
              tvBlogDetailTitle.text = response.title
              tvBlogDetailContent.text = response.content
              tvBlogDetailAuthor.text = response.author
              url = response.url
            }
          }
          isNotLoading()
        }
        is AppResource.Error -> {
          showError()
          isNotLoading()
        }
        is AppResource.Loading -> isLoading()
      }
    })
  }
  
  private fun isNotLoading() {
    binding.apply {
      pbBlogDetail.visibility = View.GONE
      ibtnBlogDetailBack.visibility = View.VISIBLE
      ibtnBlogDetailUpdate.visibility = View.VISIBLE
      ibtnBlogDetailDelete.visibility = View.VISIBLE
    }
  }
  
  private fun isLoading() {
    binding.apply {
      pbBlogDetail.visibility = View.VISIBLE
      ibtnBlogDetailBack.visibility = View.INVISIBLE
      ibtnBlogDetailUpdate.visibility = View.INVISIBLE
      ibtnBlogDetailDelete.visibility = View.INVISIBLE
    }
  }
  
  private fun showError() {
    AlertDialog.Builder(requireContext()).setMessage(R.string.something_wrong)
      .setPositiveButton(R.string.back_text) { dialog, _ ->
        dialog.dismiss()
        findNavController().navigate(R.id.action_global_blogDetailFragment_to_homeFragment)
      }.show()
  }
}
