package com.example.retroproject.features.weather.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.retroproject.features.weather.ui.screen.WeatherScreen
import kotlinx.serialization.Serializable

fun NavGraphBuilder.weatherNavGraph() {

    composable<Weather> {
        WeatherScreen(weatherViewModel = hiltViewModel())
    }
}

@Serializable
object Weather