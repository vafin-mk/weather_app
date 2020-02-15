package ru.test.weatherapp.data.mapper

import ru.test.weatherapp.data.database.dto.WeatherDbEntity
import ru.test.weatherapp.domain.entity.WeatherData
import ru.test.weatherapp.infra.Mapper
import javax.inject.Inject

class WeatherDataToDatabaseMapper @Inject constructor(

): Mapper<WeatherData, WeatherDbEntity> {

    override fun map(from: WeatherData): WeatherDbEntity {
        return WeatherDbEntity(
            temperature = from.temperature,
            feelsLike = from.feelsLike,
            updateDate = from.updateDate.time,
            pressure = from.pressure,
            humidity = from.humidity,
            windSpeed = from.windSpeed,
            windDirection = from.windDirection
        )
    }
}