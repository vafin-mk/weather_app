package ru.test.weatherapp.data.database.api

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.test.weatherapp.data.database.dto.WeatherDbEntity

@Database(entities = [WeatherDbEntity::class], version = 1)
abstract class WeatherDb: RoomDatabase() {

    abstract fun dao(): DaoImpl
}