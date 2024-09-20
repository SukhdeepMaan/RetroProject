package com.example.retroproject.features.weather.domain.model

import com.squareup.moshi.Json

data class WeatherRequest(
    @Json(name = "q")
    val city : String,
    val appid : String = "a45bda185288cef6b03035dd614f61b1"
)