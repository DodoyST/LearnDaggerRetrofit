package com.example.daggerbikinpusing.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.daggerbikinpusing.R
import com.example.daggerbikinpusing.data.model.Login
import com.example.daggerbikinpusing.data.repository.LoginRepository
import com.example.daggerbikinpusing.data.viewmodel.LoginViewModel
import com.example.daggerbikinpusing.databinding.FragmentLoginBinding
import com.example.daggerbikinpusing.util.AppResource
import com.example.daggerbikinpusing.util.SessionManager
import com.example.daggerbikinpusing.util.appComponent
import com.example.daggerbikinpusing.util.hideKeyboard
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class LoginFragment : Fragment() {
  private var _binding: FragmentLoginBinding? = null
  private val binding get() = _binding!!
  
  lateinit var loginViewModel: LoginViewModel
  
  @Inject
  lateinit var loginRepository: LoginRepository
  
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
    _binding = FragmentLoginBinding.inflate(inflater, container, false)
    return binding.root
  }
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    
    requireActivity().appComponent.inject(this@LoginFragment)
    
    loginViewModel = ViewModelProvider(requireActivity(), object : ViewModelProvider.Factory {
      override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(loginRepository) as T
      }
    })[LoginViewModel::class.java]
    
    binding.apply {
      btnLoginSubmit.setOnClickListener {
        requireContext().hideKeyboard(requireView())
        doLogin()
      }
    }
  }
  
  override fun onDestroy() {
    super.onDestroy()
    
    _binding = null
  }
  
  private fun doLogin() {
    binding.apply {
      loginViewModel.login(Login(etLoginUsername.text.toString(), etLoginPassword.text.toString()))
        .observe(viewLifecycleOwner, {
          when (it) {
            is AppResource.Success -> {
              if (it.data != null) {
                isNotLoading()
                sessionManager.saveAuthToken(it.data.token, etLoginUsername.text.toString())
                formClear()
                findNavController().navigate(R.id.action_global_loginFragment_to_homeFragment)
              }
            }
            is AppResource.Error -> {
              isNotLoading()
              showErrorLog()
            }
            is AppResource.Loading -> isLoading()
          }
        })
    }
  }
  
  private fun isLoading() {
    binding.apply {
      etLoginUsername.isEnabled = false
      etLoginPassword.isEnabled = false
      btnLoginSubmit.visibility = View.GONE
    }
  }
  
  private fun isNotLoading() {
    binding.apply {
      etLoginUsername.isEnabled = true
      etLoginPassword.isEnabled = true
      btnLoginSubmit.visibility = View.VISIBLE
    }
  }
  
  private fun showErrorLog() {
    Snackbar.make(
      requireContext(),
      requireView(),
      "Something Wrong...",
      Snackbar.LENGTH_LONG
    ).show()
  }
  
  private fun formClear() {
    binding.apply {
      etLoginUsername.text?.clear()
      etLoginPassword.text?.clear()
    }
  }
}
