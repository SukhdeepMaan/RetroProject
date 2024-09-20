package com.example.retroproject.data.repository

import com.example.retroproject.base.UIState
import com.example.retroproject.data.network.ApiService
import com.example.retroproject.features.dogList.domain.model.Dog
import com.example.retroproject.features.dogList.domain.model.DogRequest
import com.example.retroproject.features.weather.domain.model.WeatherRequest
import com.example.retroproject.features.weather.domain.model.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CommRepository @Inject constructor(private val apiService: ApiService) {

    fun fetchDogList(dogRequest: DogRequest) : Flow<UIState<List<Dog>>> = flow {
        //emit(UIState.Loading)
        try {
            val dogMap = mapOf(
                "limit" to dogRequest.limit.toString(),
                "page" to dogRequest.page.toString()
            )
            val response = apiService.getDogList(dogMap)
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

    fun fetchWeather(weatherRequest: WeatherRequest) : Flow<UIState<WeatherResponse>> = flow {
        emit(UIState.Loading)
        try {
            val wMap = mapOf(
                "q" to weatherRequest.city,
                "appid" to weatherRequest.appid
            )
            val response = apiService.getWeather(wMap)

            if (response.isSuccessful) {
                response.body()?.let { weatherResponse ->
                    emit(UIState.Success(weatherResponse))
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