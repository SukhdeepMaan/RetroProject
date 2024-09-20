package com.example.retroproject.features.dogList.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.retroproject.features.dogList.ui.screen.DogListScreen
import kotlinx.serialization.Serializable

fun NavGraphBuilder.dogListNavGraph() {
    composable<DogList>{
        DogListScreen(dogViewModel = hiltViewModel())
    }
}

@Serializable
object DogList