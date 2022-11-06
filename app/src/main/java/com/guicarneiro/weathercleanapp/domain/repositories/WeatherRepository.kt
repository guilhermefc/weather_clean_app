package com.guicarneiro.weathercleanapp.domain.repositories

import com.guicarneiro.weathercleanapp.domain.entities.Weather
import com.guicarneiro.weathercleanapp.data.common.Result

interface WeatherRepository {
    suspend fun getWeather(): Result<Weather>
}