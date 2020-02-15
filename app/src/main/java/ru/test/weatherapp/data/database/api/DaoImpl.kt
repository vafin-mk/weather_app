package ru.test.weatherapp.data.database.api

import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers
import ru.test.weatherapp.data.database.dto.WeatherDbEntity
import ru.test.weatherapp.domain.entity.MapPosition

@androidx.room.Dao
abstract class DaoImpl: Dao {

    companion object {
        private const val SEARCH_POSITION_DIFF = 0.00001
    }

    override fun weatherByPosition(position: MapPosition): Maybe<WeatherDbEntity> {
        val fromLat = position.latitude - SEARCH_POSITION_DIFF
        val toLat = position.latitude + SEARCH_POSITION_DIFF
        val fromLon = position.longitude - SEARCH_POSITION_DIFF
        val toLon = position.longitude + SEARCH_POSITION_DIFF

        return findByPosition(fromLat, toLat, fromLon, toLon)
            .subscribeOn(Schedulers.io())
    }

    override fun weatherByAddress(address: String): Maybe<WeatherDbEntity> {
        return findBySearchInput(address)
            .subscribeOn(Schedulers.io())
    }

    override fun addWeatherData(weatherDbEntity: WeatherDbEntity): Completable {
        return addEntity(weatherDbEntity).subscribeOn(Schedulers.io())
    }

    @Query("SELECT * FROM WeatherData WHERE (latitude BETWEEN :fromLatitude AND :toLatitide) AND (longitude BETWEEN :fromLongitude AND :toLongitude) ORDER BY id DESC LIMIT 1")
    abstract fun findByPosition(fromLatitude: Double, toLatitide:Double, fromLongitude: Double, toLongitude: Double): Maybe<WeatherDbEntity>

    @Query("SELECT * FROM WeatherData WHERE searchInput LIKE :searchInput ORDER BY id DESC LIMIT 1")
    abstract fun findBySearchInput(searchInput: String): Maybe<WeatherDbEntity>

    @Insert
    abstract fun addEntity(entity: WeatherDbEntity): Completable
}