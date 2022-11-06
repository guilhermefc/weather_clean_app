package com.guicarneiro.weathercleanapp.presentation

import com.guicarneiro.weathercleanapp.data.common.NetworkService
import com.guicarneiro.weathercleanapp.data.repositories.WeatherRepositoriesImpl
import com.guicarneiro.weathercleanapp.domain.usecases.GetWeather

class AppContainer {
    private val networkService = NetworkService()
    private val weatherRepositories = WeatherRepositoriesImpl(networkService)
    val getWeather = GetWeather(weatherRepositories)
}