package com.guicarneiro.weathercleanapp.domain.usecases

import com.guicarneiro.weathercleanapp.data.common.Result
import com.guicarneiro.weathercleanapp.domain.entities.WeatherForecast
import com.guicarneiro.weathercleanapp.domain.repositories.WeatherRepository
import javax.inject.Inject

class GetWeather  @Inject constructor(
    private val weatherRepo: WeatherRepository
) {

    suspend fun invoke() : Result<WeatherForecast> {
        return weatherRepo.getWeather()
    }
}