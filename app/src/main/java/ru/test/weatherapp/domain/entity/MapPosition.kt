package ru.test.weatherapp.domain.entity

import java.lang.NumberFormatException

data class MapPosition(val latitude: Double, val longitude: Double) {

    companion object {
        fun fromString(input: String): MapPosition? {
            return try {
                val split = input.split(',')
                MapPosition(split[0].toDouble(), split[1].toDouble())
            } catch (exc: NumberFormatException) {
                //not a valid positions
                null
            }
        }
    }
}