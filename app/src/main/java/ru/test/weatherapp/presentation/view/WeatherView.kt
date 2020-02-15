package ru.test.weatherapp.presentation.view

import io.reactivex.Observable
import ru.test.weatherapp.presentation.model.WeatherModel

interface WeatherView {

    fun inputObservable(): Observable<String>
    fun updateWeatherInformation(model: WeatherModel)
}