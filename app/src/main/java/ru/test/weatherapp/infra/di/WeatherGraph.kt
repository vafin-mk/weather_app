package ru.test.weatherapp.infra.di

import dagger.Component
import ru.test.weatherapp.infra.di.modules.PresentationModule
import ru.test.weatherapp.presentation.ui.fragment.WeatherFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [PresentationModule::class])
interface WeatherGraph {


    fun inject(fragment: WeatherFragment)
}