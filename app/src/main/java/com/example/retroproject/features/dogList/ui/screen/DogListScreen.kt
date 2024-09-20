package com.example.retroproject.features.dogList.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.retroproject.base.UIState
import com.example.retroproject.features.dogList.domain.model.Dog
import com.example.retroproject.features.dogList.domain.model.DogRequest
import com.example.retroproject.features.dogList.ui.viewModel.DogViewModel

@Composable
fun DogListScreen(
    modifier: Modifier = Modifier,
    dogViewModel: DogViewModel
) {
    val uiState by dogViewModel.dogState.collectAsState()
    val justToSee by dogViewModel.justToSee.collectAsState()
    val listState = rememberLazyListState()


    // Observe the last visible item index using snapshotFlow
    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisibleItemIndex ->
                // Check if we are near the end of the list (second last item)
                if (lastVisibleItemIndex == (uiState as? UIState.Success<List<Dog>>)?.data?.size?.minus(2)) {
                    dogViewModel.loadMoreDogs() // Load more dogs
                }
            }
    }

    when (uiState) {
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
                itemsIndexed((uiState as UIState.Success<List<Dog>>).data)
                { item, dog ->
                    Text("page number is $justToSee")
                    Text("${item+1}")
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