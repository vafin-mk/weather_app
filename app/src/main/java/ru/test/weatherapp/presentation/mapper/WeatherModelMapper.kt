package ru.test.weatherapp.presentation.mapper

import android.content.Context
import android.util.Log
import ru.test.weatherapp.R
import ru.test.weatherapp.domain.entity.WeatherData
import ru.test.weatherapp.infra.Mapper
import ru.test.weatherapp.presentation.model.WeatherModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class WeatherModelMapper @Inject constructor(
    private val context: Context
): Mapper<WeatherData, WeatherModel> {

    private val dateFormat = SimpleDateFormat("YYYY, MMM dd, hh:mm", Locale.getDefault())

    override fun map(from: WeatherData): WeatherModel {
        return WeatherModel(
            updateDate = dateFormat.format(from.updateDate),
            temperature = parseTemperature(from.temperature),
            feelsLikeTemperature = parseTemperature(from.feelsLike),
            pressure = parsePressure(from.pressure),
            humidity = parseHumidity(from.humidity),
            wind = parseWind(from.windSpeed, from.windDirection)
        )
    }

    private fun parseTemperature(value: Double): String {
        return if (value == Double.MIN_VALUE) {
            context.getString(R.string.unknown)
        } else {
            "$value \u2103"
        }
    }

    private fun parsePressure(value: Int): String {
        return if (value == Int.MIN_VALUE) {
            context.getString(R.string.unknown)
        } else {
            "$value hPa"
        }
    }

    private fun parseHumidity(value: Int): String {
        return if (value == Int.MIN_VALUE) {
            context.getString(R.string.unknown)
        } else {
            "$value %"
        }
    }

    private fun parseWind(speedValue: Double, direction: Int): String {
        if (speedValue == Double.MIN_VALUE) return context.getString(R.string.unknown)
        val speedText = "$speedValue meter/sec"
        if (direction < 0 || direction > 360) return speedText
        val directionText = degreeToDirection(direction)
        return "$speedText; $directionText"
    }

    //http://snowfence.umn.edu/Components/winddirectionanddegrees.htm
    private fun degreeToDirection(direction: Int): String {
        return when(direction) {
            in 11..33 -> "NNE"
            in 33..56 -> "NE"
            in 56..78 -> "ENE"
            in 78..101 -> "E"
            in 101..123 -> "ESE"
            in 123..146 -> "SE"
            in 146..168 -> "SSE"
            in 168..191 -> "S"
            in 191..213 -> "SSW"
            in 213..236 -> "SW"
            in 236..258 -> "WSW"
            in 258..281 -> "W"
            in 281..303 -> "WNW"
            in 303..326 -> "NW"
            in 326..348 -> "NNW"
            else -> "N"
        }
    }
}