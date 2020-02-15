package ru.test.weatherapp.infra.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.test.weatherapp.data.database.api.Dao
import ru.test.weatherapp.data.database.api.WeatherDb

@Module
class DatabaseModule {

    @Provides
    fun provideDb(context: Context): WeatherDb {
        return Room.databaseBuilder(context, WeatherDb::class.java, "weather-db").build()
    }

    @Provides
    fun provideDao(db: WeatherDb): Dao {
        return db.dao()
    }
}