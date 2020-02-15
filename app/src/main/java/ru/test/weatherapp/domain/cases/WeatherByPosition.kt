package ru.test.weatherapp.domain.cases

import io.reactivex.Single
import ru.test.weatherapp.domain.entity.MapPosition
import ru.test.weatherapp.domain.entity.WeatherData

interface WeatherByPosition {

    fun getWeather(position: MapPosition): Single<WeatherData>
}