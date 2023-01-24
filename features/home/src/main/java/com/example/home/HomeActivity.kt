package com.example.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.home.databinding.ActivityHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel by viewModel<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getMovie()
        setupUI()
    }

    private fun setupUI() = with(binding) {
        dropDownPickGenre.setOnClickListener {  }
    }
}