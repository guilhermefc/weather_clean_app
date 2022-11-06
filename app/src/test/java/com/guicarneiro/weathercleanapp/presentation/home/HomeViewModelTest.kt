package com.guicarneiro.weathercleanapp.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.guicarneiro.weathercleanapp.data.common.Result
import com.guicarneiro.weathercleanapp.domain.usecases.GetWeather
import com.guicarneiro.weathercleanapp.fake.WeatherFakeFactory
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class HomeViewModelTest {

    @get:Rule val rule = InstantTaskExecutorRule()
    @ExperimentalCoroutinesApi @get:Rule val mainDispatcherRule = MainDispatcherRule()
    @MockK private lateinit var getWeather: GetWeather
    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        homeViewModel = HomeViewModel(getWeather)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `given a success return of use case it should emit success state result`() = runTest {
        val fakeWeather = WeatherFakeFactory.createFakeWeather()

        coEvery { getWeather.invoke() } returns Result.Success(fakeWeather)

        homeViewModel.refreshWeatherData()
        advanceUntilIdle()

        val expected = HomeViewModel.UiState.Success(fakeWeather)

        assertThat(homeViewModel.uiState.value, `is`(expected))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `given an error return of use case it should emit Error state result`() = runTest {
        val errorMessage = "error msg"

        coEvery { getWeather.invoke() } returns Result.Error(errorMessage)

        homeViewModel.refreshWeatherData()
        advanceUntilIdle()

        val expected = HomeViewModel.UiState.Error(errorMessage)

        assertThat(homeViewModel.uiState.value, `is`(expected))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `given a fresh view model it should emit loading view state`() = runTest {
        val expected = HomeViewModel.UiState.Loading

        assertThat(homeViewModel.uiState.value, `is`(expected))
    }
}

@ExperimentalCoroutinesApi
class MainDispatcherRule(private val dispatcher: TestDispatcher = StandardTestDispatcher()): TestWatcher() {
    override fun starting(description: Description) = Dispatchers.setMain(dispatcher)
    override fun finished(description: Description) = Dispatchers.resetMain()
}