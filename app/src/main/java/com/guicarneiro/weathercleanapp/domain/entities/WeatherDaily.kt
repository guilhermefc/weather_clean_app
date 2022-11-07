package com.guicarneiro.weathercleanapp.domain.entities

import java.util.Date

data class WeatherDaily (
    val date: Date,
    val minTemperature: Int,
    val maxTemperature: Int,
    val currentTemperature: Int,
    val tempScale: String,
    val condition: String,
    val conditionType: WeatherCondition,
)