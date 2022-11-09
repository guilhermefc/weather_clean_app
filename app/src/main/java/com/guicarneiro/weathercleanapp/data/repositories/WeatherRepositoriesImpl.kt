package com.guicarneiro.weathercleanapp.data.repositories

import android.util.Log
import com.guicarneiro.weathercleanapp.data.common.Result
import com.guicarneiro.weathercleanapp.data.datasources.network.RemoteDataSource
import com.guicarneiro.weathercleanapp.data.models.WeatherData
import com.guicarneiro.weathercleanapp.domain.entities.Location
import com.guicarneiro.weathercleanapp.domain.entities.WeatherCondition
import com.guicarneiro.weathercleanapp.domain.entities.WeatherDaily
import com.guicarneiro.weathercleanapp.domain.entities.WeatherForecast
import com.guicarneiro.weathercleanapp.domain.repositories.WeatherRepository
import retrofit2.HttpException
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class WeatherRepositoriesImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
): WeatherRepository {

    override suspend fun getWeather(): Result<WeatherForecast> {
        return try {
                val result = remoteDataSource.getWeather()

            return Result.Success(parse(result))
        } catch (e: IOException) {
            Log.e("RepoImpl", e.message, e)
            Result.Error("Please check your network connection")
        } catch (e: HttpException) {
            Log.e("RepoImpl", e.message, e)
            Result.Error("Bad server, not able to load weather :(")
        } catch (e: Exception) {
            Log.e("RepoImpl", e.message, e)
            Result.Error("Something happened, not able to load weather :(")
        }
    }

    fun parse(data: WeatherData) : WeatherForecast {
        val location = Location(data.latitude, data.longitude, "Buenos Aires")
        val weatherDailyList = ArrayList<WeatherDaily>()

        val size = data.daily.time.size
        for(i in data.daily.time.indices) {
            val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            val date = dateFormatter.parse(data.daily.time[i])
            val todayFormatted = dateFormatter.format(Date())
            var currentTemp = 0.0

            if(data.daily.time[i] == todayFormatted) {
                currentTemp = data.current_weather.temperature
            }

            val weatherCondition = parseWeatherCondition(data.daily.weathercode[i])
            val weatherConditionType = parseWeatherType(data.daily.weathercode[i])

            weatherDailyList.add(
              WeatherDaily(date, data.daily.temperature_2m_min[i].toInt(), data.daily.temperature_2m_max[i].toInt(),
                  currentTemp.toInt(),
                  data.daily_units.temperature_2m_max.lowercase(Locale.US), weatherCondition, weatherConditionType)
            )
        }

        return WeatherForecast(location, weatherDailyList)
    }

    private fun parseWeatherType(conditionCode: Int): WeatherCondition {
        return when (conditionCode) {
            0 -> WeatherCondition.SUNNY
            1, 2 -> WeatherCondition.PARTIALLY_CLOUDY
            3 -> WeatherCondition.CLOUDY
            in 45 .. 65 -> WeatherCondition.RAIN
            in 66 .. 77 -> WeatherCondition.SNOW
            in 78 .. 94 -> WeatherCondition.DRIZZLE
            in 95 .. 99 -> WeatherCondition.THUNDERSTORM
            else -> WeatherCondition.SUNNY
        }
    }

    private fun parseWeatherCondition(conditionCode: Int): String {
        // Docs from: https://open-meteo.com/en/docs
        return when (conditionCode) {
            0 -> "Clear sky"
            1 -> "Mainly clear"
            2 -> "Partly cloudy"
            3 -> "Overcast"
            45 -> "Fog"
            48 -> "Depositing rime fog"
            51 -> "Drizzle light"
            53 -> "Drizzle moderate"
            55 -> "Drizzle dense"
            56 -> "Freezing drizzle light"
            57 -> "Freezing drizzle dense"
            61 -> "Rain slight"
            63 -> "Rain moderate"
            65 -> "Rain heavy"
            66 -> "Freezing Rain Light"
            67 -> "Freezing Rain heavy"
            71 -> "Snow fall Slight"
            73 -> "Snow fall moderate"
            75 -> "Snow fall heavy"
            77 -> "Snow grains"
            80 -> "Rain showers Slight"
            81 -> "Rain showers moderate"
            82 -> "Rain showers violent"
            85, 86 -> "Snow showers"
            95 -> "Slight or moderate Thunderstorm"
            96, 99 -> "Thunderstorm with slight and heavy hail"
            else -> ""
        }
    }
}