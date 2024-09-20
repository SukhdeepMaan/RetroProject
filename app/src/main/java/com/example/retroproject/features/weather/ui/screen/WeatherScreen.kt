package com.example.retroproject.features.weather.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.retroproject.base.UIState
import com.example.retroproject.features.weather.domain.model.WeatherRequest
import com.example.retroproject.features.weather.domain.model.WeatherResponse
import com.example.retroproject.features.weather.ui.viewModel.WeatherViewModel

@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    weatherViewModel: WeatherViewModel
) {
    val state by weatherViewModel.weatherState.collectAsState()

    LaunchedEffect(Unit) {
        weatherViewModel.getWeather(weatherRequest = WeatherRequest(city = "London"))
    }

    when(state) {
        is UIState.Loading -> {
            CircularProgressIndicator()
        }
        is UIState.Error -> {
            Text(text = (state as UIState.Error).message)
        }
        is UIState.Success -> {
            val data =  (state as UIState.Success<WeatherResponse>).data
            Column(modifier = modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(48.dp)) {
                Text(text = "Location: ${data.name}", fontSize = 24.sp)
                Text(text = "Temperature: ${data.main?.temp}°K", fontSize = 20.sp)
                Text(text = "Humidity: ${data.main?.humidity}%", fontSize = 20.sp)
                // Add more fields as necessary
            }
        }
    }



}