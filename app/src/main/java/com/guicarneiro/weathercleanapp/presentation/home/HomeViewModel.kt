package com.guicarneiro.weathercleanapp.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guicarneiro.weathercleanapp.data.common.Result
import com.guicarneiro.weathercleanapp.domain.entities.WeatherForecast
import com.guicarneiro.weathercleanapp.domain.usecases.GetWeather
import kotlinx.coroutines.launch

class HomeViewModel(val getWeatherUseCase: GetWeather): ViewModel() {

    val uiState = MutableLiveData<UiState>(UiState.Loading)

    sealed class UiState {
        object Loading: UiState()
        data class Error(val message: String): UiState()
        data class Success(val weather: WeatherForecast): UiState()
    }

    fun refreshWeatherData() {
        uiState.value = UiState.Loading
        loadWeatherData()
    }

    private fun loadWeatherData() {
        viewModelScope.launch {
            uiState.value = when(val result = getWeatherUseCase.invoke()) {
                is Result.Success -> UiState.Success(result.data)
                is Result.Error -> UiState.Error(result.message)
            }
        }
    }
}
