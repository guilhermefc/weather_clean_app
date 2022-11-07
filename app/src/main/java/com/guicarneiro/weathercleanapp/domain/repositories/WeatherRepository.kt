package com.guicarneiro.weathercleanapp.domain.repositories

import com.guicarneiro.weathercleanapp.data.common.Result
import com.guicarneiro.weathercleanapp.domain.entities.WeatherForecast

interface WeatherRepository {
    suspend fun getWeather(): Result<WeatherForecast>
}