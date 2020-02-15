package ru.test.weatherapp.domain.cases

import io.reactivex.Single
import ru.test.weatherapp.domain.entity.WeatherData

interface WeatherByAddress {

    fun getWeather(address: String): Single<WeatherData>
}