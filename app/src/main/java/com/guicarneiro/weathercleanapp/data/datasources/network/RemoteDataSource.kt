package com.guicarneiro.weathercleanapp.data.datasources.network

import com.guicarneiro.weathercleanapp.data.models.WeatherData
import retrofit2.http.GET


interface RemoteDataSource {
    suspend fun getWeather(): WeatherData
}
