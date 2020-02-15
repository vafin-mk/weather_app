package ru.test.weatherapp.infra.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.test.weatherapp.R
import ru.test.weatherapp.infra.di.ApiKey
import ru.test.weatherapp.presentation.ui.App

@Module
class AppModule {

    @Provides
    fun provideContext(): Context = App.app

    @Provides
    @ApiKey
    fun provideApiKey(context: Context) = context.getString(R.string.api_key)
}