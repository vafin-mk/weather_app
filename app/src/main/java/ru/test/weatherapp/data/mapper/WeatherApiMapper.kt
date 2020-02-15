package ru.test.weatherapp.data.mapper

import ru.test.weatherapp.data.database.dto.WeatherDto
import ru.test.weatherapp.domain.entity.WeatherData
import ru.test.weatherapp.infra.Mapper
import javax.inject.Inject

class WeatherApiMapper @Inject constructor(

): Mapper<WeatherDto, WeatherData> {

    override fun map(from: WeatherDto): WeatherData {
        return WeatherData(from.temperatureInfo.temperature ?: 0.0)
    }
}