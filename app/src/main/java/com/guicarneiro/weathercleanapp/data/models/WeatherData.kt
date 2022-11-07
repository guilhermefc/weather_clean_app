package com.guicarneiro.weathercleanapp.data.models

data class WeatherData(
    val current_weather: CurrentWeather,
    val daily: Daily,
    val daily_units: DailyUnits,
    val elevation: Double,
    val generationtime_ms: Double,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezone_abbreviation: String,
    val utc_offset_seconds: Int
)

data class CurrentWeather(
    val temperature: Double,
    val time: String,
    val weathercode: Int,
    val winddirection: Double,
    val windspeed: Double
)

data class Daily(
    val temperature_2m_max: List<Double>,
    val temperature_2m_min: List<Double>,
    val time: List<String>,
    val weathercode: List<Int>
)

data class DailyUnits(
    val temperature_2m_max: String,
    val temperature_2m_min: String,
    val time: String,
    val weathercode: String
)