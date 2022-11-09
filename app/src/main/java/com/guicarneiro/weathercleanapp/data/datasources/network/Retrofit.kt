package com.guicarneiro.weathercleanapp.data.datasources.network

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.guicarneiro.weathercleanapp.BuildConfig
import com.guicarneiro.weathercleanapp.data.models.WeatherData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

interface RetrofitNetworkAPI {
    @GET("/v1/forecast?latitude=-34.6118&longitude=-58.4173&daily=weathercode,temperature_2m_max,temperature_2m_min&current_weather=true&timezone=auto")
    suspend fun getWeatherDataById(): WeatherData
}

private const val BaseURL = BuildConfig.API_BASE_URL

@Singleton
class RetrofitNetwork @Inject constructor(private val gson: GsonConverterFactory) : RemoteDataSource {
        private val networkApi = Retrofit.Builder()
            .baseUrl(BaseURL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        // TODO: Decide logging logic
                        HttpLoggingInterceptor().apply {
                            setLevel(HttpLoggingInterceptor.Level.BODY)
                        }
                    )
                    .build()
            )
            .addConverterFactory(gson)
            .build()
            .create(RetrofitNetworkAPI::class.java)

        override suspend fun getWeather(): WeatherData {
            return networkApi.getWeatherDataById()
        }

}