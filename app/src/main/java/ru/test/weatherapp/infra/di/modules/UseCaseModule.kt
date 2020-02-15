package ru.test.weatherapp.infra.di.modules

import dagger.Binds
import dagger.Module
import ru.test.weatherapp.domain.cases.WeatherByAddress
import ru.test.weatherapp.domain.cases.WeatherByPosition
import ru.test.weatherapp.domain.cases.impl.WeatherByAddressImpl
import ru.test.weatherapp.domain.cases.impl.WeatherByPositionImpl

@Module
interface UseCaseModule {

    @Binds
    fun bindWeatherByAddressCase(impl: WeatherByAddressImpl): WeatherByAddress

    @Binds
    fun bindWeatherByPositionCase(impl: WeatherByPositionImpl): WeatherByPosition
}