package ru.test.weatherapp.infra.di

import ru.test.weatherapp.presentation.ui.fragment.WeatherFragment

object Injector {

    private val graph = DaggerWeatherGraph.builder().build()

    fun inject(fragment: WeatherFragment) = graph.inject(fragment)
}