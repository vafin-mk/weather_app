package ru.test.weatherapp.data.repo

import io.reactivex.Observable
import io.reactivex.Single
import ru.test.weatherapp.data.database.api.Dao
import ru.test.weatherapp.data.database.dto.WeatherDbEntity
import ru.test.weatherapp.data.network.api.NetworkApi
import ru.test.weatherapp.data.network.dto.WeatherDto
import ru.test.weatherapp.domain.entity.MapPosition
import ru.test.weatherapp.domain.entity.WeatherData
import ru.test.weatherapp.domain.repo.WeatherRepo
import ru.test.weatherapp.infra.Mapper
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val networkApi: NetworkApi,
    private val dao: Dao,
    private val weatherApiMapper: Mapper<WeatherDto, WeatherData>,
    private val weatherFromDbMapper: Mapper<WeatherDbEntity, WeatherData>,
    private val weatherToDbMapper: Mapper<WeatherData, WeatherDbEntity>
): WeatherRepo {

    override fun weatherByPosition(position: MapPosition): Observable<WeatherData> {
        val networkRequest = networkApi.weatherByPosition(position)
            .map { weatherApiMapper.map(it) }
            .flatMap { data ->
                dao.addWeatherData(weatherToDbMapper.map(data).copy(latitude = position.latitude, longitude = position.longitude))
                    .andThen(Single.just(data))
            }

        val cacheRequest = dao.weatherByPosition(position)
            .map { weatherFromDbMapper.map(it) }

        return cacheRequest.concatWith(networkRequest.toMaybe()).toObservable()
    }

    override fun weatherByAddress(address: String): Observable<WeatherData> {
        val networkRequest = networkApi.weatherByAddress(address)
            .map { weatherApiMapper.map(it) }
            .flatMap { data ->
                dao.addWeatherData(weatherToDbMapper.map(data).copy(searchInput = address))
                    .andThen(Single.just(data))
            }

        val cacheRequest = dao.weatherByAddress(address)
            .map { weatherFromDbMapper.map(it) }

        return cacheRequest.concatWith(networkRequest.toMaybe()).toObservable()
    }
}