package ru.test.weatherapp.domain.entity

import java.util.*

data class WeatherData(
    val temperature: Double,
    val feelsLike: Double,
    val updateDate: Date,
    val pressure: Int,
    val humidity: Int,
    val windSpeed: Double,
    val windDirection: Int
)