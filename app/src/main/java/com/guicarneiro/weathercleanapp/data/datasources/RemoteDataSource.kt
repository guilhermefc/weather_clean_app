package com.guicarneiro.weathercleanapp.data.datasources

import com.guicarneiro.weathercleanapp.data.models.WeatherData
import retrofit2.http.GET

interface RemoteDataSource {
    @GET("/v1/forecast?latitude=-34.6118&longitude=-58.4173&daily=weathercode,temperature_2m_max,temperature_2m_min&current_weather=true&timezone=auto")
    suspend fun getWeatherDataById(): WeatherData
}