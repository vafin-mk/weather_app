package ru.test.weatherapp.domain.cases.impl

import io.reactivex.Single
import ru.test.weatherapp.domain.cases.WeatherByAddress
import ru.test.weatherapp.domain.entity.WeatherData
import ru.test.weatherapp.domain.repo.WeatherRepo
import javax.inject.Inject

class WeatherByAddressImpl @Inject constructor(
    private val repo: WeatherRepo
): WeatherByAddress {

    override fun getWeather(address: String): Single<WeatherData> {
        return repo.weatherByAddress(address)
    }
}