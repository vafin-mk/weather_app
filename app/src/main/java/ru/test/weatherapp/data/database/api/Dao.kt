package ru.test.weatherapp.data.database.api

import io.reactivex.Completable
import io.reactivex.Maybe
import ru.test.weatherapp.data.database.dto.WeatherDbEntity
import ru.test.weatherapp.domain.entity.MapPosition

interface Dao {

    fun weatherByPosition(position: MapPosition): Maybe<WeatherDbEntity>
    fun weatherByAddress(address: String): Maybe<WeatherDbEntity>
    fun addWeatherData(weatherDbEntity: WeatherDbEntity): Completable
}