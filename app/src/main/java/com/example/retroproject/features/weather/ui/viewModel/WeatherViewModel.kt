package com.example.retroproject.features.weather.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retroproject.base.UIState
import com.example.retroproject.data.repository.CommRepository
import com.example.retroproject.features.weather.domain.model.WeatherRequest
import com.example.retroproject.features.weather.domain.model.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val commRepository: CommRepository) : ViewModel() {

    private val _weatherState = MutableStateFlow<UIState<WeatherResponse>>(UIState.Loading)
    var weatherState = _weatherState.asStateFlow()
        private set

    fun getWeather(weatherRequest: WeatherRequest) {
        viewModelScope.launch {
            commRepository.fetchWeather(weatherRequest).collect{
                _weatherState.value = it
            }
        }
    }
}