package com.example.retroproject.features.dogList.domain.model

data class DogRequest(
    val limit : Int = 10,
    val page : Int = 1
)