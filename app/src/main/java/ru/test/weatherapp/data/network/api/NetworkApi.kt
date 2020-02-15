package ru.test.weatherapp.data.network.api

import io.reactivex.Single
import ru.test.weatherapp.data.database.dto.WeatherDto
import ru.test.weatherapp.domain.entity.MapPosition

interface NetworkApi {

    fun weatherByPosition(position: MapPosition): Single<WeatherDto>
    fun weatherByAddress(address: String): Single<WeatherDto>
}