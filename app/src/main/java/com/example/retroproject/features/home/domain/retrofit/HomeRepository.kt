package com.example.retroproject.features.home.domain.retrofit

import com.example.retroproject.base.UIState
import com.example.retroproject.data.network.ApiService
import com.example.retroproject.features.home.domain.model.Dog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HomeRepository @Inject constructor(private val apiService: ApiService) {

    fun fetchDogList(limit: Int, page: Int) : Flow<UIState<List<Dog>>> = flow {
        emit(UIState.Loading)
        try {
            val response = apiService.getDogList(limit, page)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(UIState.Success(it))
                } ?: emit(UIState.Error("Empty response"))
            }
            else {
                emit(UIState.Error("Error: ${response.code()}")) // Emit error state with status code
            }
        }
        catch (e : Exception) {
            emit(UIState.Error("Network error: ${e.message}"))
        }
    }.flowOn(Dispatchers.IO)
}