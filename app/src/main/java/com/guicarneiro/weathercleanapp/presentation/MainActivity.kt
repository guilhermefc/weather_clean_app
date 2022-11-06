package com.guicarneiro.weathercleanapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.guicarneiro.weathercleanapp.databinding.ActivityHomeBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}