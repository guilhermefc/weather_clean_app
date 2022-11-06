package com.guicarneiro.weathercleanapp.domain.entities

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

data class ConsolidatedWeather(
    val id: String,
    val weatherStateName: String,
    val weatherStateAbbr: String,
    val windDirectionCompass: String,
    val created: Date,
    val applicableDate : Date,
    val minTemp : Double,
    val maxTemp: Double,
    val theTemp: Double,
    val windSpeed: Double,
    val windDirection: Double,
    val airPressure: Double,
    val humidity: Double,
    val visibility: Double,
    val predictability: Double,
) {
    fun getMaxTemp() :String {
       return maxTemp.roundToInt().toString()
    }
    fun getMinTemp() :String {
        return minTemp.roundToInt().toString()
    }

    fun getTempNow(): String {
        return theTemp.roundToInt().toString()
    }
}