package ru.test.weatherapp.presentation.mapper

import ru.test.weatherapp.domain.entity.WeatherData
import ru.test.weatherapp.infra.Mapper
import ru.test.weatherapp.presentation.model.WeatherModel
import javax.inject.Inject

class WeatherModelMapper @Inject constructor(

): Mapper<WeatherData, WeatherModel> {

    override fun map(from: WeatherData): WeatherModel {
        return WeatherModel(from.temperature)
    }
}