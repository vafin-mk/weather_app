package ru.test.weatherapp.infra.di.modules

import dagger.Binds
import dagger.Module
import ru.test.weatherapp.data.repo.WeatherRepository
import ru.test.weatherapp.domain.repo.WeatherRepo

@Module
interface RepositoryModule {

    @Binds
    fun bindRepository(impl: WeatherRepository): WeatherRepo
}