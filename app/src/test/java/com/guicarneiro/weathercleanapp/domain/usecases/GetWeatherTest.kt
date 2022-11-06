package com.guicarneiro.weathercleanapp.domain.usecases

import com.guicarneiro.weathercleanapp.data.common.Result
import com.guicarneiro.weathercleanapp.domain.entities.Weather
import com.guicarneiro.weathercleanapp.domain.repositories.WeatherRepository
import com.guicarneiro.weathercleanapp.fake.WeatherFakeFactory
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetWeatherTest {
    @MockK lateinit var repository: WeatherRepository

    private lateinit var getWeather: GetWeather
    private lateinit var weather: Weather

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        getWeather = GetWeather(repository)
        weather = WeatherFakeFactory.createFakeWeather();
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `it should return a success result after calling the repository`() = runTest {
        val projects =
        coEvery { repository.getWeather() } returns Result.Success(weather)

        val testResult = getWeather.invoke()

        assert(testResult is Result.Success)
    }

}