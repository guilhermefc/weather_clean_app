package com.guicarneiro.weathercleanapp.domain.entities

data class WeatherForecast(
    val location: Location,
    val weatherList: List<WeatherDaily>
)
