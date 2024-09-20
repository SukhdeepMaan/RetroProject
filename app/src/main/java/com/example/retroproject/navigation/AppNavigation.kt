package com.example.retroproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.retroproject.features.dogList.ui.navigation.DogList
import com.example.retroproject.features.dogList.ui.navigation.dogListNavGraph
import com.example.retroproject.features.weather.ui.navigation.Weather
import com.example.retroproject.features.weather.ui.navigation.weatherNavGraph


@Composable
fun AppNavigation() {
    NavHost(
        navController = rememberNavController(),
        startDestination =
        DogList
        //Weather
    ) {
        dogListNavGraph()
        weatherNavGraph()
    }
}