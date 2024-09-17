package com.example.retroproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.retroproject.features.home.ui.navigation.Home
import com.example.retroproject.features.home.ui.navigation.homeNavGraph


@Composable
fun AppNavigation() {
    NavHost(
        navController = rememberNavController(),
        startDestination = Home
    ) {
        homeNavGraph()
    }
}