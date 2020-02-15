package ru.test.weatherapp.domain.cases.impl

import io.reactivex.Single
import ru.test.weatherapp.domain.cases.WeatherByPosition
import ru.test.weatherapp.domain.entity.MapPosition
import ru.test.weatherapp.domain.entity.WeatherData
import ru.test.weatherapp.domain.repo.WeatherRepo
import javax.inject.Inject

class WeatherByPositionImpl @Inject constructor(
    private val repo: WeatherRepo
): WeatherByPosition {

    override fun getWeather(position: MapPosition): Single<WeatherData> {
        return repo.weatherByPosition(position)
    }
}