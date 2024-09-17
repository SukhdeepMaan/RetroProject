package com.example.retroproject.features.home.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.retroproject.features.home.ui.screen.HomeScreen
import kotlinx.serialization.Serializable

fun NavGraphBuilder.homeNavGraph() {
    composable<Home>{
        HomeScreen(homeViewModel = hiltViewModel())
    }
}

@Serializable
object Home