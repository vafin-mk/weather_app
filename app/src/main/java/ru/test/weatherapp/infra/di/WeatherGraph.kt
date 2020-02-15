package ru.test.weatherapp.infra.di

import dagger.Component
import ru.test.weatherapp.infra.di.modules.*
import ru.test.weatherapp.presentation.ui.fragment.WeatherFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [
    PresentationModule::class,
    MapperModule::class,
    UseCaseModule::class,
    NetworkModule::class,
    AppModule::class,
    RepositoryModule::class
])
interface WeatherGraph {


    fun inject(fragment: WeatherFragment)
}