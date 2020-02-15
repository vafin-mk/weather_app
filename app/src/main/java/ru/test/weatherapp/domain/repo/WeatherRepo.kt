package ru.test.weatherapp.domain.repo

import io.reactivex.Observable
import ru.test.weatherapp.domain.entity.MapPosition
import ru.test.weatherapp.domain.entity.WeatherData

interface WeatherRepo {

    fun weatherByPosition(position: MapPosition): Observable<WeatherData>
    fun weatherByAddress(address: String): Observable<WeatherData>
}