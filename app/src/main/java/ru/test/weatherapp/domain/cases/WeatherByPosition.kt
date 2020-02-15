package ru.test.weatherapp.domain.cases

import io.reactivex.Observable
import ru.test.weatherapp.domain.entity.MapPosition
import ru.test.weatherapp.domain.entity.WeatherData

interface WeatherByPosition {

    fun getWeather(position: MapPosition): Observable<WeatherData>
}