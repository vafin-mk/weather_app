package ru.test.weatherapp.data.mapper

import ru.test.weatherapp.data.database.dto.WeatherDbEntity
import ru.test.weatherapp.domain.entity.WeatherData
import ru.test.weatherapp.infra.Mapper
import java.sql.Date
import javax.inject.Inject

class WeatherDbToDataMapper @Inject constructor(

): Mapper<WeatherDbEntity, WeatherData> {

    override fun map(from: WeatherDbEntity): WeatherData {
        return WeatherData(
            temperature = from.temperature,
            feelsLike = from.feelsLike,
            updateDate = Date(from.updateDate),
            pressure = from.pressure,
            humidity = from.humidity,
            windSpeed = from.windSpeed,
            windDirection = from.windDirection
        )
    }
}