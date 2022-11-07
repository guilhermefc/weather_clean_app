package com.guicarneiro.weathercleanapp.fake

import com.github.javafaker.Faker
import com.guicarneiro.weathercleanapp.data.models.WeatherData
import com.guicarneiro.weathercleanapp.domain.entities.Location
import com.guicarneiro.weathercleanapp.domain.entities.WeatherForecast


abstract class WeatherFakeFactory {
    companion object {
        fun createFakeWeather() : WeatherForecast {
            return WeatherForecast(location = Location(0.0,0.0, Faker.instance().country().capital()), weatherList = listOf())
        }

        fun provideFakeWeatherJson(): String {
            return "{\n" +
                    "  \"latitude\": -34.75,\n" +
                    "  \"longitude\": -58.25,\n" +
                    "  \"generationtime_ms\": 0.6461143493652344,\n" +
                    "  \"utc_offset_seconds\": -10800,\n" +
                    "  \"timezone\": \"America/Argentina/Buenos_Aires\",\n" +
                    "  \"timezone_abbreviation\": \"-03\",\n" +
                    "  \"elevation\": 0.0,\n" +
                    "  \"daily_units\": {\n" +
                    "    \"time\": \"iso8601\",\n" +
                    "    \"weathercode\": \"wmo code\",\n" +
                    "    \"temperature_2m_max\": \"°C\",\n" +
                    "    \"temperature_2m_min\": \"°C\"\n" +
                    "  },\n" +
                    "  \"daily\": {\n" +
                    "    \"time\": [\n" +
                    "      \"2022-11-07\",\n" +
                    "      \"2022-11-08\",\n" +
                    "      \"2022-11-09\",\n" +
                    "      \"2022-11-10\",\n" +
                    "      \"2022-11-11\",\n" +
                    "      \"2022-11-12\",\n" +
                    "      \"2022-11-13\"\n" +
                    "    ],\n" +
                    "    \"weathercode\": [\n" +
                    "      2,\n" +
                    "      1,\n" +
                    "      3,\n" +
                    "      3,\n" +
                    "      3,\n" +
                    "      95,\n" +
                    "      3\n" +
                    "    ],\n" +
                    "    \"temperature_2m_max\": [\n" +
                    "      22.0,\n" +
                    "      22.4,\n" +
                    "      23.1,\n" +
                    "      24.9,\n" +
                    "      24.5,\n" +
                    "      22.9,\n" +
                    "      25.8\n" +
                    "    ],\n" +
                    "    \"temperature_2m_min\": [\n" +
                    "      16.5,\n" +
                    "      16.8,\n" +
                    "      17.6,\n" +
                    "      18.6,\n" +
                    "      20.4,\n" +
                    "      20.3,\n" +
                    "      16.4\n" +
                    "    ]\n" +
                    "  }\n" +
                    "}"
        }

    }
}