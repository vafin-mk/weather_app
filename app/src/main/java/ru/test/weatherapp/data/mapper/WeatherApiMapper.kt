package ru.test.weatherapp.data.mapper

import ru.test.weatherapp.data.network.dto.WeatherDto
import ru.test.weatherapp.domain.entity.WeatherData
import ru.test.weatherapp.infra.Mapper
import java.sql.Date
import javax.inject.Inject

class WeatherApiMapper @Inject constructor(

): Mapper<WeatherDto, WeatherData> {

    override fun map(from: WeatherDto): WeatherData {
        val milliSeconds = from.updateDate?.let { it * 1000L } ?: System.currentTimeMillis()
        return WeatherData(
            temperature = from.temperatureInfo?.temperature ?: Double.MIN_VALUE,
            feelsLike = from.temperatureInfo?.feelTemperature ?: Double.MIN_VALUE,
            updateDate = Date(milliSeconds),
            pressure = from.temperatureInfo?.pressure ?: Int.MIN_VALUE,
            humidity = from.temperatureInfo?.humidity ?: Int.MIN_VALUE,
            windSpeed = from.windInfo?.speed ?: Double.MIN_VALUE,
            windDirection = from.windInfo?.degree ?: Int.MIN_VALUE
        )
    }
}