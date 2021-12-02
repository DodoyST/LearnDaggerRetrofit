package com.example.daggerbikinpusing.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.daggerbikinpusing.data.adapter.BlogAdapter
import com.example.daggerbikinpusing.data.repository.BlogRepositoryImpl
import com.example.daggerbikinpusing.data.viewmodel.BlogViewModel
import com.example.daggerbikinpusing.databinding.FragmentHomeBinding
import com.example.daggerbikinpusing.util.AppResource
import com.example.daggerbikinpusing.util.SessionManager
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class HomeFragment : DaggerFragment() {
  private var _binding: FragmentHomeBinding? = null
  private val binding get() = _binding!!
  
  private lateinit var blogViewModel: BlogViewModel
  
  @Inject
  lateinit var blogRepositoryImpl: BlogRepositoryImpl
  private lateinit var blogAdapter: BlogAdapter
  
  @Inject
  lateinit var sessionManager: SessionManager
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }
  
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    return binding.root
  }
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    
    blogViewModel = ViewModelProvider(requireActivity(), object : ViewModelProvider.Factory {
      override fun <T : ViewModel> create(modelClass: Class<T>) =
        BlogViewModel(blogRepositoryImpl) as T
    })[BlogViewModel::class.java]
    
    binding.tvHomeName.text = sessionManager.fetchAuthUsername()
    subscribe()
  }
  
  override fun onDestroy() {
    super.onDestroy()
    
    _binding = null
  }
  
  private fun subscribe() {
    blogViewModel.getAll().observe(viewLifecycleOwner, {
      when (it) {
        is AppResource.Success -> {
          blogAdapter = BlogAdapter(it.data!!, blogViewModel)
          binding.apply {
            rvHomeCardBlog.apply {
              layoutManager = LinearLayoutManager(requireContext())
              adapter = blogAdapter
            }
            pbHomeBlog.visibility = View.GONE
          }
        }
        is AppResource.Error -> binding.pbHomeBlog.visibility = View.GONE
        is AppResource.Loading -> binding.pbHomeBlog.visibility = View.VISIBLE
      }
    })
  }
}
