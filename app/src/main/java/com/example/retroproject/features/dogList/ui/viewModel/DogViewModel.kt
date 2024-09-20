package com.example.retroproject.features.dogList.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retroproject.base.UIState
import com.example.retroproject.features.dogList.domain.model.Dog
import com.example.retroproject.features.dogList.domain.model.DogRequest
import com.example.retroproject.data.repository.CommRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogViewModel @Inject constructor(private val commRepository: CommRepository) : ViewModel() {

    private val _dogState = MutableStateFlow<UIState<List<Dog>>>(UIState.Loading)
    var dogState: StateFlow<UIState<List<Dog>>> = _dogState
        private set

    init {
        getDogList(DogRequest(limit = 10, page = 1))
    }

    private var currentPage = 1 // Track the current page
    private val _justToSee = MutableStateFlow(currentPage) // Track the current page in a StateFlow
    val justToSee: StateFlow<Int> = _justToSee.asStateFlow()

    fun getDogList(dogRequest: DogRequest) {
        viewModelScope.launch {
            commRepository.fetchDogList(dogRequest).collect { uiState ->
                _dogState.value = uiState
            }
        }
    }

    fun loadMoreDogs() {
        currentPage++ // Increment the current page
        getDogList(DogRequest(limit = 10, page = currentPage)) // Fetch more dogs
    }
}