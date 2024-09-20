package com.example.retroproject.data.network

import com.example.retroproject.features.dogList.domain.model.Dog
import com.example.retroproject.features.weather.domain.model.WeatherResponse
import com.example.retroproject.utils.EndPoint
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {

    @GET(EndPoint.IMAGE_SEARCH)
    suspend fun getDogList(
        @QueryMap options: Map<String, String>
    ): Response<List<Dog>>

    @GET(EndPoint.WEATHER)
    suspend fun getWeather(
        @QueryMap options: Map<String, String>
    ) : Response<WeatherResponse>
}