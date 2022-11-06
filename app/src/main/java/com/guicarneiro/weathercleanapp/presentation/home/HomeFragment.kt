package com.guicarneiro.weathercleanapp.presentation.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.guicarneiro.weathercleanapp.BuildConfig
import com.guicarneiro.weathercleanapp.R
import com.guicarneiro.weathercleanapp.databinding.FragmentHomeBinding
import com.guicarneiro.weathercleanapp.domain.entities.ConsolidatedWeather
import com.guicarneiro.weathercleanapp.domain.entities.Weather
import com.guicarneiro.weathercleanapp.presentation.MyApplication
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import okhttp3.internal.notify
import java.lang.Exception


class HomeFragment : Fragment(), Callback {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    private lateinit var homeViewModel : HomeViewModel
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var nextWeatherList: ArrayList<ConsolidatedWeather>
    private lateinit var nextWeatherListAdapter: WeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val appContainer = (activity?.application as MyApplication).appContainer
        homeViewModel = HomeViewModel(getWeatherUseCase = appContainer.getWeather)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root


    }
//
//    @SuppressLint("MissingPermission")
//    @RequiresApi(Build.VERSION_CODES.N)
//    private fun getLocation(): Boolean {
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
//
//        val locationPermissionRequest = registerForActivityResult(
//            ActivityResultContracts.RequestMultiplePermissions()
//        ) { permissions ->
//            when {
//                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
//                    // Precise location access granted.
//                }
//                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
//                    // Only approximate location access granted.
//                } else -> {
//                // No location access granted.
//                }
//            }
//        }
//
//        locationPermissionRequest.launch(arrayOf(
//            Manifest.permission.ACCESS_COARSE_LOCATION))
//
//        fusedLocationClient.lastLocation
//            .addOnSuccessListener { location: Location? ->
//                Log.e("loc", "olha a loc: " + location.toString())
//
//
//                // Got last known location. In some rare situations this can be null.
//            }
//        return false
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
        setupObservables()
        homeViewModel.refreshWeatherData()

//        getLocation()
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

    private fun showInfo(weather: Weather) {
        binding.progressCircular.visibility = View.GONE
        if(weather.consolidatedWeather.isNotEmpty()) {
            val recentWeather = weather.consolidatedWeather.first();
            val dtWeather = DateFormat.getDateFormat(context).format(recentWeather.applicableDate)
            binding.textviewCity.text = getString(R.string.city_date, weather.title, dtWeather)
            binding.textviewTemperature.text = resources.getString(
                R.string.temperature,
                recentWeather.getTempNow()
            )
            binding.textviewWeatherCondition.text =
                recentWeather.weatherStateName
            binding.textviewLowHigh.text = resources.getString(
                R.string.min_and_max_temp,
                recentWeather.getMinTemp(),
                recentWeather.getMaxTemp()
            )
            val picassoURL = BuildConfig.API_BASE_URL_IMAGES +
                    recentWeather.weatherStateAbbr + BuildConfig.API_IMAGES_FORMAT
            Picasso.get().load(picassoURL).into(binding.imageView, this)


            if(weather.consolidatedWeather.size > 1) {
                populateRecyclerView(weather)
            }
        }
    }

    private fun populateRecyclerView(weather: Weather) {
        nextWeatherList = ArrayList(weather.consolidatedWeather.subList(1, weather.consolidatedWeather.size))
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
        binding.textviewError.text = null
        binding.imageView.setImageResource(0)
        binding.textviewCity.text = null
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
//            getLocation()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSuccess() {
        binding.root.transitionToEnd()
    }

    override fun onError(e: Exception?) {}
}