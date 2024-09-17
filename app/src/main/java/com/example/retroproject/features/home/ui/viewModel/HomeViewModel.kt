package com.example.retroproject.features.home.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retroproject.base.UIState
import com.example.retroproject.features.home.domain.model.Dog
import com.example.retroproject.features.home.domain.retrofit.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {

    private val _dogState = MutableStateFlow<UIState<List<Dog>>>(UIState.Loading)
    var dogState: StateFlow<UIState<List<Dog>>> = _dogState
        private set

    fun getDogList(limit: Int, page: Int) {
        viewModelScope.launch {
            homeRepository.fetchDogList(limit,page).collect { uiState ->
                _dogState.value = uiState
            }
        }
    }
}