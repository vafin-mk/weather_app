package ru.test.weatherapp.data.database.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WeatherData")
data class WeatherDbEntity(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "searchInput") var searchInput: String? = null,
    @ColumnInfo(name = "latitude") var latitude: Double? = null,
    @ColumnInfo(name = "longitude") var longitude: Double? = null,
    @ColumnInfo(name = "temperature") var temperature: Double,
    @ColumnInfo(name = "feelsLike") var feelsLike: Double,
    @ColumnInfo(name = "updateDate") var updateDate: Long,
    @ColumnInfo(name = "pressure") var pressure: Int,
    @ColumnInfo(name = "humidity") var humidity: Int,
    @ColumnInfo(name = "windSpeed") var windSpeed: Double,
    @ColumnInfo(name = "windDirection") var windDirection: Int
)