package ru.test.weatherapp.domain.repo

import io.reactivex.Single
import ru.test.weatherapp.domain.entity.MapPosition
import ru.test.weatherapp.domain.entity.WeatherData

interface WeatherRepo {

    fun weatherByPosition(position: MapPosition): Single<WeatherData>
    fun weatherByAddress(address: String): Single<WeatherData>
}