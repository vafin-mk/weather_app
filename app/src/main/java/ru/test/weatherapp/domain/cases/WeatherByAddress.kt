package ru.test.weatherapp.domain.cases

import io.reactivex.Observable
import ru.test.weatherapp.domain.entity.WeatherData

interface WeatherByAddress {

    fun getWeather(address: String): Observable<WeatherData>
}