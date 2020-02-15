package ru.test.weatherapp.data.network.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class WeatherDto(
    @JsonProperty(value = "coord") val coordinates: WeatherCoordDto?,
    @JsonProperty(value = "main") val temperatureInfo: WeatherInfoDto?,
    @JsonProperty(value = "wind") val windInfo: WindDto?,
    @JsonProperty(value = "dt") val updateDate: Long?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class WeatherCoordDto(
    @JsonProperty(value = "lon") val longitude: Double?,
    @JsonProperty(value = "lat") val latitude: Double?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class WeatherInfoDto(
    @JsonProperty(value = "temp") val temperature: Double?,
    @JsonProperty(value = "feels_like") val feelTemperature: Double?,
    @JsonProperty(value = "temp_min") val minTemperature: Double?,
    @JsonProperty(value = "temp_max") val maxTemperature: Double?,
    @JsonProperty(value = "pressure") val pressure: Int?,
    @JsonProperty(value = "humidity") val humidity: Int?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class WindDto(
    @JsonProperty("speed") val speed: Double?,
    @JsonProperty("deg") val degree: Int?
)



