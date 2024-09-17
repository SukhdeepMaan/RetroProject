package com.example.retroproject.features.home.domain.model

data class HomeRequest(
    val limit : Int = 10,
    val page : Int = 1
)