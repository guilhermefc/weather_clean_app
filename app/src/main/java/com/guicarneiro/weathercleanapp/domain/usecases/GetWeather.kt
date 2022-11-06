package com.guicarneiro.weathercleanapp.domain.usecases

import com.guicarneiro.weathercleanapp.data.common.Result
import com.guicarneiro.weathercleanapp.domain.entities.Weather
import com.guicarneiro.weathercleanapp.domain.repositories.WeatherRepository

class GetWeather(
    private val weatherRepo: WeatherRepository
) {

    suspend fun invoke() : Result<Weather> {
        return weatherRepo.getWeather()
    }
}