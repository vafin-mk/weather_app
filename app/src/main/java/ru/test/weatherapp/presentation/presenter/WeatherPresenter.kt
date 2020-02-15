package ru.test.weatherapp.presentation.presenter

import ru.test.weatherapp.presentation.view.WeatherView

interface WeatherPresenter {

    fun bindView(view: WeatherView)
    fun unbindView()
}