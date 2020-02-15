package ru.test.weatherapp.infra.di.modules

import dagger.Binds
import dagger.Module
import ru.test.weatherapp.presentation.presenter.WeatherPresenter
import ru.test.weatherapp.presentation.presenter.WeatherPresenterImpl

@Module
interface PresentationModule {

    @Binds
    fun bindPresenter(impl: WeatherPresenterImpl): WeatherPresenter
}