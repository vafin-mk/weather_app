package ru.test.weatherapp.presentation.model

data class WeatherModel (
    val updateDate: String,
    val temperature: String,
    val feelsLikeTemperature: String,
    val pressure: String,
    val humidity: String,
    val wind: String
)