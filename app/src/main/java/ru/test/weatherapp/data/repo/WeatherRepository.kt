package ru.test.weatherapp.data.repo

import io.reactivex.Single
import ru.test.weatherapp.data.network.dto.WeatherDto
import ru.test.weatherapp.data.network.api.NetworkApi
import ru.test.weatherapp.domain.entity.MapPosition
import ru.test.weatherapp.domain.entity.WeatherData
import ru.test.weatherapp.domain.repo.WeatherRepo
import ru.test.weatherapp.infra.Mapper
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val networkApi: NetworkApi,
    private val weatherApiMapper: Mapper<WeatherDto, WeatherData>
): WeatherRepo {

    override fun weatherByPosition(position: MapPosition): Single<WeatherData> {
        return networkApi.weatherByPosition(position)
            .map { weatherApiMapper.map(it) }
    }

    override fun weatherByAddress(address: String): Single<WeatherData> {
        return networkApi.weatherByAddress(address)
            .map { weatherApiMapper.map(it) }
    }
}