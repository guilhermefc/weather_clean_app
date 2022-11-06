package com.guicarneiro.weathercleanapp.domain.entities

import java.text.SimpleDateFormat

data class Weather  (
    val consolidatedWeather: List<ConsolidatedWeather>,
    val time: String,
    val sunRise: String,
    val sunSet: String,
    val timezoneName: String,
    val parent: Parent,
    val sources: List<Sources>,
    val title: String,
    val locationType: String,
    val woeid: Int,
    val lattLong: String,
    val timezone: String,
)

