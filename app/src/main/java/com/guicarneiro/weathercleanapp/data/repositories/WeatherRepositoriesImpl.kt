package com.guicarneiro.weathercleanapp.data.repositories

import com.guicarneiro.weathercleanapp.data.common.NetworkService
import com.guicarneiro.weathercleanapp.data.datasources.RemoteDataSource
import com.guicarneiro.weathercleanapp.data.common.Result
import com.guicarneiro.weathercleanapp.domain.entities.Weather
import com.guicarneiro.weathercleanapp.domain.repositories.WeatherRepository
import retrofit2.HttpException
import retrofit2.create
import java.io.IOException

class WeatherRepositoriesImpl(
    private val networkService: NetworkService,
): WeatherRepository {

    override suspend fun getWeather(): Result<Weather> {
        return try {
            val retrofit = networkService.provideRetrofit()
            val service = retrofit.create<RemoteDataSource>()
            val result = service.getWeatherDataById("4418")
            Result.Success(result)
        } catch (e: IOException) {
            Result.Error("Please check your network connection")
        } catch (e: HttpException) {
            Result.Error("Bad server, not able to load weather :(")
        } catch (e: Exception) {
            Result.Error("Something happened, not able to load weather :(")
        }
    }
}