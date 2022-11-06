package com.guicarneiro.weathercleanapp.data.datasources

import com.guicarneiro.weathercleanapp.domain.entities.Weather
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteDataSource {
    @GET("static/mobile-take-home/{id}.json")
    suspend fun getWeatherDataById(@Path("id") id: String): Weather
}