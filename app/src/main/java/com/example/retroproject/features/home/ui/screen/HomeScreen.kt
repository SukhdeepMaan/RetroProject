package com.example.retroproject.features.home.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.retroproject.base.UIState
import com.example.retroproject.features.home.domain.model.Dog
import com.example.retroproject.features.home.ui.viewModel.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel
) {
    val uiState by homeViewModel.dogState.collectAsState()
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        homeViewModel.getDogList(limit = 10, page = 1) // Example limit and page values
    }

    when(uiState) {
        is UIState.Loading -> {
            CircularProgressIndicator()
        }
        is UIState.Error -> {
            Text(text = (uiState as UIState.Error).message)

        }
        is UIState.Success -> {
            LazyColumn(
                state = listState,
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items((uiState as UIState.Success<List<Dog>>).data)
                { dog ->
                    DogItem(dog)
                }
            }
        }
    }
}


@Composable
fun DogItem(dog: Dog) {
    Column {
        Text(text = dog.id ?: "Unknown")
        Image(
            painter = rememberAsyncImagePainter(dog.url),
            contentDescription = "Dog Image",
            modifier = Modifier.size(150.dp)
        )
    }
}