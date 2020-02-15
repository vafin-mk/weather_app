package ru.test.weatherapp.data.network.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.test.weatherapp.data.network.dto.WeatherDto

interface RestApi {

    @GET("weather")
    fun weatherByPosition(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String,
        @Query("units") measureUnits: String
    ): Single<WeatherDto>

    @GET("weather")
    fun weatherByAddress(
        @Query("q") query: String,
        @Query("appid") apiKey: String,
        @Query("units") measureUnits: String
    ): Single<WeatherDto>
}