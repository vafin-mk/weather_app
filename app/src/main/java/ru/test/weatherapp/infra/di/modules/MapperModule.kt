package ru.test.weatherapp.infra.di.modules

import dagger.Binds
import dagger.Module
import ru.test.weatherapp.data.network.dto.WeatherDto
import ru.test.weatherapp.data.mapper.WeatherApiMapper
import ru.test.weatherapp.domain.entity.WeatherData
import ru.test.weatherapp.infra.Mapper
import ru.test.weatherapp.presentation.mapper.WeatherModelMapper
import ru.test.weatherapp.presentation.model.WeatherModel

@Module
interface MapperModule {

    @Binds
    fun bindWeatherModelMapper(impl: WeatherModelMapper): Mapper<WeatherData, WeatherModel>

    @Binds
    fun bindWeatherApiMapper(impl: WeatherApiMapper): Mapper<WeatherDto, WeatherData>
}