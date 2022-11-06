package com.guicarneiro.weathercleanapp.data.repositories

import com.guicarneiro.weathercleanapp.data.common.NetworkService
import com.guicarneiro.weathercleanapp.data.common.Result
import com.guicarneiro.weathercleanapp.domain.entities.Weather
import com.guicarneiro.weathercleanapp.domain.repositories.WeatherRepository
import com.guicarneiro.weathercleanapp.domain.usecases.GetWeather
import com.guicarneiro.weathercleanapp.fake.WeatherFakeFactory
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class WeatherRepositoriesImplTest {

    @MockK
    private lateinit var networkService: NetworkService
    private lateinit var weatherRepository: WeatherRepository
    private lateinit var weather: Weather

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        weatherRepository = WeatherRepositoriesImpl(networkService)
        weather = WeatherFakeFactory.createFakeWeather();

    }

    @Test
    fun `It should return a success result when the api response 200 on a valid json`() = runBlocking{
        val mockWebServer = MockWebServer()
        mockWebServer.start()

        val api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(NetworkService().provideGson()))
            .client(NetworkService().provideOkHttp())
            .build()

        every { networkService.provideRetrofit() } returns api
        every { networkService.provideRetrofit() } returns api


        mockWebServer.enqueue(MockResponse().setBody(WeatherFakeFactory.provideFakeWeatherJson()))
//        mockWebServer.enqueue(MockResponse().setHttp2ErrorCode(400))

        val result = weatherRepository.getWeather()

        mockWebServer.shutdown()

        assert(result is Result.Success)
    }

    @Test
    fun `It should return a error result when api response 400`() = runBlocking{
        val mockWebServer = MockWebServer()
        mockWebServer.start()

        val api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(NetworkService().provideGson()))
            .client(NetworkService().provideOkHttp())
            .build()

        every { networkService.provideRetrofit() } returns api
        every { networkService.provideRetrofit() } returns api

        mockWebServer.enqueue(MockResponse().setHttp2ErrorCode(400))

        val result = weatherRepository.getWeather()

        mockWebServer.shutdown()

        assert(result is Result.Error)
    }
}