package ru.test.weatherapp.data.network.api

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.test.weatherapp.data.database.dto.WeatherDto
import ru.test.weatherapp.domain.entity.MapPosition
import ru.test.weatherapp.infra.di.ApiKey
import javax.inject.Inject

class NetworkApiImpl @Inject constructor(
    private val restClient: RestApi,
    @ApiKey private val apiKey: String
): NetworkApi {

    override fun weatherByPosition(position: MapPosition): Single<WeatherDto> {
        return restClient.weatherByPosition(position.latitude, position.longitude, apiKey)
            .subscribeOn(Schedulers.io())
    }

    override fun weatherByAddress(address: String): Single<WeatherDto> {
        return restClient.weatherByAddress(address, apiKey)
            .subscribeOn(Schedulers.io())
    }
}