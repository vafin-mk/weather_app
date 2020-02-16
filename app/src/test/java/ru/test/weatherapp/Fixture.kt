package ru.test.weatherapp

import ru.test.weatherapp.data.database.dto.WeatherDbEntity
import ru.test.weatherapp.domain.entity.WeatherData
import ru.test.weatherapp.presentation.model.WeatherModel
import java.text.SimpleDateFormat
import java.util.*

object Fixture {

    private val dateFormat = SimpleDateFormat("YYYY, MMM dd, hh:mm", Locale.getDefault())

    fun generateWeatherModel() = WeatherModel(
        dateFormat.format(Date()),"0.0 \u2103","0.0 \u2103",
        "0 hPa","0 %","0.0 meter/sec; N"
    )

    fun generateWeatherData() = WeatherData(
        0.0, 0.0, Date(),
        0, 0, 0.0,
        0
    )

    fun generateWeatherModelWithUnknownValues(): WeatherModel {
        return WeatherModel(
            dateFormat.format(Date()),
            "Unknown",
            "Unknown",
            "Unknown",
            "Unknown",
            "Unknown"
        )
    }

    fun generateWeatherDataWithUnknownValues(): WeatherData {
        return WeatherData(
            Double.MIN_VALUE,
            Double.MIN_VALUE,
            Date(),
            Int.MIN_VALUE,
            Int.MIN_VALUE,
            Double.MIN_VALUE,
            Int.MIN_VALUE
        )
    }

    fun generateDbEntity(): WeatherDbEntity {
        return WeatherDbEntity(
            null, null, null, null,
            0.0,0.0, 0L, 0, 0, 0.0, 0
        )
    }
}