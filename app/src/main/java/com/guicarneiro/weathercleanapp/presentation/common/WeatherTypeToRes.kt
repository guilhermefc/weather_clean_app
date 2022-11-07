package com.guicarneiro.weathercleanapp.presentation.common

import com.guicarneiro.weathercleanapp.R
import com.guicarneiro.weathercleanapp.domain.entities.WeatherCondition

abstract class WeatherTypeToRes {
    companion object {
        fun parseWeatherTypeToResource(weatherCondition: WeatherCondition) : Int {
            return when (weatherCondition) {
                WeatherCondition.SUNNY -> R.drawable.ic_weather_sunny
                WeatherCondition.CLOUDY -> R.drawable.ic_weather_cloudy
                WeatherCondition.PARTIALLY_CLOUDY -> R.drawable.ic_weather_partially_cloudy
                WeatherCondition.RAIN -> R.drawable.ic_weather_rain
                WeatherCondition.SNOW -> R.drawable.ic_weather_snow
                WeatherCondition.THUNDERSTORM -> R.drawable.ic_weather_thunderstorm
                WeatherCondition.DRIZZLE -> R.drawable.ic_weather_drizzle
            }
        }
    }
}