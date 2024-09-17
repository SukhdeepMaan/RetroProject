package com.example.retroproject.data.network

import com.example.retroproject.features.home.domain.model.Dog
import com.example.retroproject.utils.EndPoint
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(EndPoint.imageSearch)
    suspend fun getDogList(
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): Response<List<Dog>>
}