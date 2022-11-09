package com.guicarneiro.weathercleanapp.presentation.home

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.javafaker.Weather
import com.google.android.gms.location.FusedLocationProviderClient
import com.guicarneiro.weathercleanapp.BuildConfig
import com.guicarneiro.weathercleanapp.R
import com.guicarneiro.weathercleanapp.databinding.FragmentHomeBinding
import com.guicarneiro.weathercleanapp.domain.entities.WeatherCondition
import com.guicarneiro.weathercleanapp.domain.entities.WeatherDaily
import com.guicarneiro.weathercleanapp.domain.entities.WeatherForecast
import com.guicarneiro.weathercleanapp.presentation.MyApplication
import com.guicarneiro.weathercleanapp.presentation.common.WeatherTypeToRes
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    private val homeViewModel : HomeViewModel by viewModels()
    private var nextWeatherList: ArrayList<WeatherDaily> = ArrayList()
    private var nextWeatherListAdapter: WeatherAdapter = WeatherAdapter(listOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
        setupObservables()
        homeViewModel.refreshWeatherData()
    }

    private fun setupObservables() {
        homeViewModel.uiState.observe(requireActivity()) {
            when(it) {
                is HomeViewModel.UiState.Loading -> showLoading()
                is HomeViewModel.UiState.Error -> showError(it.message)
                is HomeViewModel.UiState.Success -> showInfo(it.weather)
            }
        }
    }

    private fun showError(message: String) {
        binding.progressCircular.visibility = View.GONE
        binding.textviewError.text = message
    }

    private fun showInfo(weather: WeatherForecast) {
        binding.progressCircular.visibility = View.GONE
        if(weather.weatherList.isNotEmpty()) {
            val recentWeather = weather.weatherList.first();
            val dtWeather = SimpleDateFormat("MMM dd", Locale.US).format(recentWeather.date)
            binding.textviewCity.text = getString(R.string.city_date, weather.location.cityName)
            binding.textviewTodayDate.text = getString(R.string.today_date, dtWeather)
            binding.textviewTemperature.text = resources.getString(
                R.string.temperature,
                recentWeather.currentTemperature.toString(),
                recentWeather.tempScale
            )
            binding.textviewWeatherCondition.text =
                recentWeather.condition
            binding.textviewLowHigh.text = resources.getString(
                R.string.min_and_max_temp,
                recentWeather.minTemperature.toString(),
                recentWeather.maxTemperature.toString()
            )

            binding.imageView.setImageResource(WeatherTypeToRes.parseWeatherTypeToResource(recentWeather.conditionType))
            binding.root.transitionToEnd()


            if(weather.weatherList.size > 1) {
                populateRecyclerView(weather)
            }
        }
    }


    private fun populateRecyclerView(weather: WeatherForecast) {
        nextWeatherList = ArrayList(weather.weatherList.subList(1, weather.weatherList.size))
        nextWeatherListAdapter = WeatherAdapter(nextWeatherList);
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.listview.layoutManager = linearLayoutManager
        binding.listview.itemAnimator = DefaultItemAnimator()
        binding.listview.adapter = nextWeatherListAdapter
    }

    private fun showLoading() {
        binding.progressCircular.visibility = View.VISIBLE
    }

    private fun clearView() {
        binding.root.transitionToStart()
        binding.textviewError.text = null
        binding.imageView.setImageResource(0)
        binding.textviewCity.text = null
        binding.textviewTodayDate.text = null
        binding.textviewTemperature.text = null
        binding.textviewWeatherCondition.text = null
        binding.textviewLowHigh.text = null

        nextWeatherList.clear()
        nextWeatherListAdapter.notifyDataSetChanged()
    }

    private fun setupClickListeners() {
        binding.fab.setOnClickListener {
            clearView()
            homeViewModel.refreshWeatherData()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}