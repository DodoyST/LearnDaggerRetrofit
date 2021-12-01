package com.example.daggerbikinpusing.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.daggerbikinpusing.databinding.ActivityMainBinding
import dagger.android.AndroidInjection

class MainActivity : AppCompatActivity() {
  
  private lateinit var binding: ActivityMainBinding
  
  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this@MainActivity)
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
  }
}
